package com.walter.service;

import com.walter.model.ResumeVO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by yhwang131 on 2017-06-23.
 */
public interface ResumeService {
	ResumeVO getResume(String _id);
	List<ResumeVO> getResumeList();
	void setResume(ResumeVO resumeVO, MultipartFile file) throws IOException;
}
