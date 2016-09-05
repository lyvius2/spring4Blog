package com.walter.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Created by yhwang131 on 2016-08-11.
 */
public class DataSourceConfiguration extends DriverManagerDataSource {

	private Cryptography cryptography;

	public void setCryptography(Cryptography cryptography) {
		this.cryptography = cryptography;
	}

	@Override
	public void setUrl(String url) {
		super.setUrl(cryptography.decrypt(url));
	}
}
