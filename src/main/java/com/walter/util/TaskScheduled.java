package com.walter.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * Created by Walter on 2017-07-26.
 */
@Slf4j
@PropertySource("classpath:/application.properties")
@Component
public class TaskScheduled {

	@Value("${home.url}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	@Scheduled(fixedDelay = 900000)
	public void healthChk() {
		HashMap<String, Object> result = restTemplate.postForObject(url, null, HashMap.class);
		log.info("Health Check ---> {}", result.toString());
	}
}
