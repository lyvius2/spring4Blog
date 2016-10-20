package com.walter.config.drive;

import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.InputStreamContent;
import com.google.api.services.drive.Drive;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by yhwang131 on 2016-10-20.
 */
@Component("driveUploader")
public class GoogleDriveUploader implements MediaHttpUploaderProgressListener {

	@Override
	public void progressChanged(MediaHttpUploader mediaHttpUploader) throws IOException {
		switch (mediaHttpUploader.getUploadState()) {
			case INITIATION_STARTED:
				System.out.println("Initiation has started!");
				break;
			case INITIATION_COMPLETE:
				System.out.println("Initiation is complete!");
				break;
			case MEDIA_IN_PROGRESS:
				System.out.println(mediaHttpUploader.getProgress());
				break;
			case MEDIA_COMPLETE:
				System.out.println("Upload is complete!");
		}
	}
}
