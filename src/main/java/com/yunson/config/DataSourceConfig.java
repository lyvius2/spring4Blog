package com.yunson.config;

import org.apache.commons.dbcp.BasicDataSource;

/**
 * Created by yhwang131 on 2016-08-08.
 */
public class DataSourceConfig extends BasicDataSource {

	@Override
	public synchronized void setUrl(String url) {
		try {
			url = new Cryptography().decrypt(url);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		} finally {
			super.setUrl(url);
		}
	}
}
