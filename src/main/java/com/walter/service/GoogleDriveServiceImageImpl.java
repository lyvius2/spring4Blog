package com.walter.service;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.walter.config.drive.GoogleDriveAccessHandler;
import com.walter.config.drive.GoogleDriveUploaderProgress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by yhwang131 on 2016-10-26.
 */
@Service("googleDriveServiceImage")
public class GoogleDriveServiceImageImpl implements GoogleDriveService {

	private static String UPLOAD_FOLDER_ID;
	private static Drive DRIVE;

	@Autowired
	private GoogleDriveUploaderProgress uploaderProgress;

	@Autowired
	private GoogleDriveAccessHandler driveHandler;

	@Override
	public File createFile(MultipartFile multipartFile) throws IOException {
		String uploadPath = uploaderProgress.getUploadFolderName();
		Drive service = this.getDriveService();

		if(UPLOAD_FOLDER_ID == null){
			UPLOAD_FOLDER_ID = this.getUploadFolderId(service, uploadPath);
		}
		/*
		String mimeType = Files.probeContentType(Paths.get(file.getPath()));
		InputStreamContent mediaContent = new InputStreamContent(mimeType, new BufferedInputStream(new FileInputStream(file)));
		mediaContent.setLength(file.length());
		*/
		InputStreamContent mediaContent = new InputStreamContent(multipartFile.getContentType(), multipartFile.getInputStream());
		mediaContent.setLength(multipartFile.getSize());

		File targetFile = new File();
		targetFile.setName(multipartFile.getOriginalFilename());
		targetFile.setParents(Collections.singletonList(UPLOAD_FOLDER_ID));

		Drive.Files.Create request = service.files().create(targetFile, mediaContent);
		request.getMediaHttpUploader().setProgressListener(uploaderProgress);
		return request.execute();
	}

	@Override
	public HashMap<String, Object> openFile(String fileId) throws IOException {
		Drive service = this.getDriveService();
		Drive.Files.Get get = service.files().get(fileId);

		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("mimeType", get.execute().getMimeType());
		resultMap.put("data", get.executeMediaAsInputStream());
		return resultMap;
	}

	@Override
	public void removeFile(String fileId) throws IOException {
		Drive service = this.getDriveService();
		service.files().delete(fileId);
	}

	private String getUploadFolderId(Drive service, String uploadPath) throws IOException {
		String result;
		FileList test = service.files()
				.list()
				.setQ("name = '" + uploadPath + "' and mimeType = 'application/vnd.google-apps.folder'")
				.execute();

		if(test.getFiles().size() == 0) {
			File fileMetadata = new File();
			fileMetadata.setName(uploadPath);
			fileMetadata.setMimeType("application/vnd.google-apps.folder");

			result = service.files().create(fileMetadata).setFields("id").execute().getId();
		} else {
			result = test.getFiles().get(0).getId();
		}
		return result;
	}

	private Drive getDriveService() throws IOException {
		if(DRIVE == null) {
			DRIVE = driveHandler.getDriveInstance();
		}
		return DRIVE;
	}
}
