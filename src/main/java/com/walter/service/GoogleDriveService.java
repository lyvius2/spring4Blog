package com.walter.service;

import com.google.api.services.drive.model.File;

import java.io.IOException;

/**
 * Created by yhwang131 on 2016-10-26.
 */
public interface GoogleDriveService {
	File createFile(java.io.File file) throws IOException;
	byte[] openFile(String fileId) throws IOException;
}
