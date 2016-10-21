package com.walter.controller;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
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
	private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE);
	//private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE_METADATA_READONLY);

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
			List<File> files = service.files().list()
					//.setPageSize(100)
					.setFields("nextPageToken, files(id, name)")
					.execute()
					.getFiles();

			//files.stream().forEach(f -> {logger.debug(f.getName() + " : " + f.getId());});

			List<String> photoDicIdList = files.stream()
					.filter(f -> f.getName().equals("Google 포토"))
					.map(file -> file.getId()).collect(Collectors.toList());
			String PHOTO_FOLDER_ID = photoDicIdList!=null?photoDicIdList.get(0):"";

			HashMap<String, String> fileUpload = this.uploadTest(service, PHOTO_FOLDER_ID);
			model.addAttribute("fileUpload", fileUpload);
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return "config/test";
	}

	private HashMap<String, String> uploadTest(Drive drive, String parentFolder) throws IOException {
		HashMap<String, String> fileData = new HashMap<>();
		try {
			java.io.File mediaFile = new java.io.File("C:\\Users\\yhwang131\\20080913_321166.jpg");
			String mineType = Files.probeContentType(Paths.get(mediaFile.getPath()));

			//InputStreamContent mediaContent = new InputStreamContent("image/jpeg", new BufferedInputStream(new FileInputStream(mediaFile)));
			InputStreamContent mediaContent = new InputStreamContent(mineType, new BufferedInputStream(new FileInputStream(mediaFile)));
			mediaContent.setLength(mediaFile.length());

			File testFile = new File();
			testFile.setName(mediaFile.getName());
			testFile.setParents(Collections.singletonList(parentFolder));

			Drive.Files.Create request = drive.files().create(testFile, mediaContent);
			request.getMediaHttpUploader().setProgressListener(driveUploader);
			File uploadResult = request.execute();

			fileData.put("fileId", uploadResult.getId());
			fileData.put("fileName", uploadResult.getName());
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return fileData;
	}

	@RequestMapping(value = "/googleapi/{fileId}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] viewImageFromGoogleDrive(@PathVariable String fileId) throws IOException {
		Drive service = getDriveService();
		InputStream inputStream = service.files().get(fileId).executeMediaAsInputStream();
		return IOUtils.toByteArray(inputStream);
	}

}
