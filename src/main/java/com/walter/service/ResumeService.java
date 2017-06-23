package com.walter.service;

import com.walter.model.ResumeVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by yhwang131 on 2017-06-23.
 */
public interface ResumeService {
	ResumeVO getDefaultResume(String _id);
	void registerResume(ResumeVO resumeVO, MultipartFile file, String savePath) throws IOException;
}
