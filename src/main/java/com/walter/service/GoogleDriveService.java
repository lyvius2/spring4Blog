package com.walter.service;

import com.google.api.services.drive.model.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by yhwang131 on 2016-10-26.
 */
public interface GoogleDriveService {
	File createFile(MultipartFile multipartFile) throws IOException;
	HashMap<String, Object> openFile(String fileId) throws IOException;
}
