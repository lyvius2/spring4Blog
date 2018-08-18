package com.walter.config.drive;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.walter.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-24.
 */
@Component
public class GoogleDriveAccessHandler {

	static final Logger logger = LoggerFactory.getLogger(GoogleDriveAccessHandler.class);

	private static final String APPLICATION_NAME = "Google Drive API 4 Spring Blog";
	private static final java.io.File DATA_STORE_DIR = new java.io.File(FileUtil.REAL_CLASS_PATH, "WEB-INF/classes");
	private static FileDataStoreFactory DATA_STORE_FACTORY;
	private static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static HttpTransport HTTP_TRANSFORT;
	private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE);

	@Autowired
	private GoogleDriveClientSecret googleDriveClientSecret;

	static {
		try {
			HTTP_TRANSFORT = GoogleNetHttpTransport.newTrustedTransport();
			DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	public Credential authorize() throws IOException {
		InputStream in = new ByteArrayInputStream(googleDriveClientSecret.getJsonStringify().getBytes());
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
				HTTP_TRANSFORT, JSON_FACTORY, clientSecrets, SCOPES)
				.setDataStoreFactory(DATA_STORE_FACTORY)
				.setAccessType("offline")
				.build();
		Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		logger.info("Credentials saved to : " + DATA_STORE_DIR.getAbsolutePath());
		return credential;
	}

	public Drive getDriveInstance() throws IOException {
		Credential credential = authorize();
		return new Drive.Builder(HTTP_TRANSFORT, JSON_FACTORY, credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}
}
