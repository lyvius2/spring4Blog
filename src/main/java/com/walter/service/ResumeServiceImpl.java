package com.walter.service;

import com.walter.config.CustomStringUtils;
import com.walter.model.ResumeVO;
import com.walter.repository.ResumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by yhwang131 on 2017-06-23.
 */
@Service
public class ResumeServiceImpl implements ResumeService {
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	final static String PROFILE_IMAGE_PATH = "resources/images/profile/";

	@Autowired
	private ResumeRepository repository;

	@Autowired
	private MongoOperations mongoOps;

	@Override
	public ResumeVO getDefaultResume(String _id) {
		ResumeVO resume;
		if (CustomStringUtils.isEmpty(_id)) {
			resume = repository.findOneByLast_saved_date(mongoOps);
		} else {
			resume = repository.findBy_id(_id);
		}
		return resume == null ? new ResumeVO():resume;
	}

	@Override
	public void registerResume(ResumeVO resumeVO, MultipartFile file, String savePath) throws IOException {
		if (file.getSize() > 0) {
			String fileName = getRandomUniqueFileName(new File(savePath, PROFILE_IMAGE_PATH));
			file.transferTo(new File(savePath, PROFILE_IMAGE_PATH + fileName));
			resumeVO.setImage_url(fileName);
		}
		resumeVO.setLast_saved_date(new Date());
		repository.insert(resumeVO);
	}

	private String getRandomUniqueFileName(File file) {
		String randomFileName = new String();
		boolean isMakingFileName = true;
		while(isMakingFileName) {
			randomFileName = UUID.randomUUID().toString();
			isMakingFileName = new File(file, randomFileName).exists();
		}
		return randomFileName;
	}
}
