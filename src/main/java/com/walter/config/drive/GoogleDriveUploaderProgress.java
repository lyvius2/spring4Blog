package com.walter.config.drive;

import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by yhwang131 on 2016-10-20.
 */
@Component
public class GoogleDriveUploaderProgress implements MediaHttpUploaderProgressListener {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void progressChanged(MediaHttpUploader mediaHttpUploader) throws IOException {
		switch (mediaHttpUploader.getUploadState()) {
			case INITIATION_STARTED:
				logger.debug("Initiation has started!");
				break;
			case INITIATION_COMPLETE:
				logger.debug("Initiation is complete!");
				break;
			case MEDIA_IN_PROGRESS:
				logger.info(String.valueOf(mediaHttpUploader.getProgress()));
				break;
			case MEDIA_COMPLETE:
				logger.info("Upload is complete!");
		}
	}
}
