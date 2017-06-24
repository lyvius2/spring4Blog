package com.walter.util;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.UUID;

/**
 * Created by Walter on 2017-06-25.
 */
@Component
public class FileUtil {
	final public static String REAL_CLASS_PATH = (FileUtil.class).getProtectionDomain().getCodeSource().getLocation().getPath() + "../../";
	final public static String PROFILE_IMAGE_PATH = "resources/images/profile/";

	public String getRandomUniqueFileName(File file) {
		String randomFileName = new String();
		boolean isMakingFileName = true;
		while(isMakingFileName) {
			randomFileName = UUID.randomUUID().toString();
			isMakingFileName = new File(file, randomFileName).exists();
		}
		return randomFileName;
	}
}
