package com.walter.service;

import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.walter.config.drive.GoogleDriveAccessHandler;
import com.walter.config.drive.GoogleDriveUploaderProgress;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

/**
 * Created by yhwang131 on 2016-10-26.
 */
@Service
public class GoogleDriveServiceImageImpl implements GoogleDriveService {

	private static String UPLOAD_FOLDER_ID;

	@Autowired
	private GoogleDriveUploaderProgress uploaderProgress;

	@Autowired
	private GoogleDriveAccessHandler driveHandler;

	@Override
	public File createFile(java.io.File file) throws IOException {
		String uploadPath = uploaderProgress.getUploadFolderName();
		Drive service = driveHandler.getDriveInstance();

		if(UPLOAD_FOLDER_ID.equals(null)){
			UPLOAD_FOLDER_ID = this.getUploadFolderId(service, uploadPath);
		}

		String mimeType = Files.probeContentType(Paths.get(file.getPath()));
		InputStreamContent mediaContent = new InputStreamContent(mimeType, new BufferedInputStream(new FileInputStream(file)));
		mediaContent.setLength(file.length());

		File targetFile = new File();
		targetFile.setName(file.getName());
		targetFile.setParents(Collections.singletonList(UPLOAD_FOLDER_ID));

		Drive.Files.Create request = service.files().create(targetFile, mediaContent);
		request.getMediaHttpUploader().setProgressListener(uploaderProgress);
		return request.execute();
	}

	@Override
	public byte[] openFile(String fileId) throws IOException {
		Drive service = driveHandler.getDriveInstance();
		InputStream inputStream = service.files().get(fileId).executeMediaAsInputStream();
		return IOUtils.toByteArray(inputStream);
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
}
