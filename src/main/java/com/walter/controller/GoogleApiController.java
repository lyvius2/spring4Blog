package com.walter.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yhwang131 on 2016-10-20.
 */
@Controller
public class GoogleApiController extends BaseController {

	@Resource(name = "driveUploader")
	private MediaHttpUploaderProgressListener driveUploader;

	private static final String APPLICATION_NAME = "Drive API Java Quick Start";
	private static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/drive-java-quickstart");
	private static FileDataStoreFactory DATA_STORE_FACTORY;
	private static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static HttpTransport HTTP_TRANSFORT;
	private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE_METADATA_READONLY);

	static {
		try {
			HTTP_TRANSFORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	public static Credential authorize() throws IOException {
		InputStream in = GoogleApiController.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSFORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline")
				.build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		System.out.println("Credentials saved to : " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	public static Drive getDriveService() throws IOException {
		Credential credential = authorize();
		return new Drive.Builder(
				HTTP_TRANSFORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}

	@RequestMapping("/googleDrive")
	public String main(Model model) throws IOException {
		try {
			Drive service = getDriveService();

			//this.uploadTest(service);

			FileList result = service.files().list()
					//.setPageSize(100)
					.setFields("nextPageToken, files(id, name)")
					.execute();
			List<File> files = result.getFiles();

			files.stream().forEach(f -> logger.debug("finale : " + f.getName() + " / " + f.getId() + " / " + f.getPermissions()));

			List<String> photoDicIdList = files.stream()
					.filter(f -> f.getName().equals("Google 포토"))
					.map(file -> file.getId()).collect(Collectors.toList());
			String PHOTO_FOLDER_ID = photoDicIdList!=null?photoDicIdList.get(0):"";

			logger.info("Start set permission!");
			/*Permission permission = new Permission()
					.setType("user")
					.setRole("writer")
					.setEmailAddress("yhwang131@gmail.com");*/
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> setPermission 1 >>>");
			Permission permit = service.permissions().get(PHOTO_FOLDER_ID, "user").execute();
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> setPermission 2 >>>");
			permit.setRole("writer");
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> setPermission 3 >>>");
			service.permissions().update(PHOTO_FOLDER_ID, permit.getId(), permit).execute();
			logger.info("End of setting");

			File fileMetadata = new File();
			fileMetadata.setName("testPhoto.jpg");
			fileMetadata.setParents(Collections.singletonList(PHOTO_FOLDER_ID));
			FileContent mediaContent = new FileContent("image/jpeg", new java.io.File("C:\\Users\\yhwang131\\20080913_321166.jpg"));
			File file = service.files().create(fileMetadata, mediaContent)
					.setFields("id, parents")
					.execute();
			logger.info("File ID : " + file.getId() + " / " + file.getName());



			if(files == null || files.size() == 0) {
				logger.info("No files found.");
			} else {
				logger.info("Files : ");
				//files.stream().forEach(f -> logger.info("File Name : " + f.getName() + " / File ID : " + f.getId()));
			}
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return "config/test";
	}

	private void uploadTest(Drive drive) throws IOException {
		try {
			logger.debug("welcome!");
			java.io.File mediaFile = new java.io.File("C:\\Users\\yhwang131\\20080913_321166.jpg");
			logger.debug("file info : " + mediaFile.getPath() + " / " + mediaFile.getName());
			InputStreamContent mediaContent = new InputStreamContent("image/jpeg", new BufferedInputStream(new FileInputStream(mediaFile)));
			mediaContent.setLength(mediaFile.length());

			File testFile = new File();

			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> setPermission1");
			Permission permission = new Permission();
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> setPermission2");
			permission.setType("user");
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> setPermission3");
			permission.setRole("owner");
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> setPermission4 : " + testFile.getId());
			//drive.permissions().create()
			//logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> end of setting5");

			Drive.Files.Create request = drive.files().create(testFile, mediaContent);
			request.getMediaHttpUploader().setProgressListener(driveUploader);
			request.execute();
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	}
}
