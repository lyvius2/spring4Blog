package com.walter.controller;

import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import com.walter.config.drive.GoogleDriveAccessHandler;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yhwang131 on 2016-10-20.
 */
@Controller
public class GoogleApiController extends BaseController {

	@Autowired
	private MediaHttpUploaderProgressListener uploaderProgress;

	@Autowired
	private GoogleDriveAccessHandler driveHandler;

	@RequestMapping(value = "/googleDrive", method = RequestMethod.GET)
	public String driveUploadForm(Model model) throws IOException {
		return "post/fileUpload";
	}

	@RequestMapping(value = "/googleDrive", method = RequestMethod.POST)
	public String main(Model model) throws IOException {
		try {
			logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>> API CONTROLLER");
			Drive service = driveHandler.getDriveInstance();
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
			request.getMediaHttpUploader().setProgressListener(uploaderProgress);
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
		Drive service = driveHandler.getDriveInstance();
		InputStream inputStream = service.files().get(fileId).executeMediaAsInputStream();
		return IOUtils.toByteArray(inputStream);
	}

}
