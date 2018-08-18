package com.walter.config.drive;

import com.google.gson.Gson;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

@Data
@RequiredArgsConstructor
public class GoogleDriveClientSecret {

	@Autowired
	private Gson gson;

	private String client_id;
	private String project_id;
	private String auth_uri;
	private String token_uri;
	private String auth_provider_x509_cert_url;
	private String client_secret;
	private String redirect_uris;

	public String getJsonStringify() {
		String[] redirect_uris = this.redirect_uris.split(",");
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("client_id", this.client_id);
		hashMap.put("project_id", this.project_id);
		hashMap.put("auth_uri", this.auth_uri);
		hashMap.put("token_uri", this.token_uri);
		hashMap.put("auth_provider_x509_cert_url", this.auth_provider_x509_cert_url);
		hashMap.put("client_secret", this.client_secret);
		hashMap.put("redirect_uris", redirect_uris);

		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("installed", hashMap);
		return gson.toJson(resultMap);
	}
}
