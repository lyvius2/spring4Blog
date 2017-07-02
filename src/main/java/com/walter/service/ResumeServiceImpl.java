package com.walter.service;

import com.walter.config.CustomStringUtils;
import com.walter.model.ResumeVO;
import com.walter.repository.ResumeRepository;
import com.walter.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by yhwang131 on 2017-06-23.
 */
@Service
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeRepository repository;

	@Autowired
	private MongoOperations mongoOps;

	@Autowired
	private FileUtil fileUtil;

	@Override
	public ResumeVO getDefaultResume(String _id) {
		ResumeVO resume;
		if (CustomStringUtils.isEmpty(_id)) resume = repository.findOneByLast_saved_date(mongoOps);
		else resume = repository.findBy_id(_id);
		if (resume != null) {
			if (!new File(fileUtil.REAL_CLASS_PATH, fileUtil.PROFILE_IMAGE_PATH + resume.getImage_url()).exists()) {
				resume.setImage_url(null);
			}
		}
		return resume == null ? new ResumeVO():resume;
	}

	@Override
	public void registerResume(ResumeVO resumeVO, MultipartFile file) throws IOException {
		if (file.getSize() > 0) {
			String fileName = fileUtil.getRandomUniqueFileName(new File(fileUtil.REAL_CLASS_PATH, fileUtil.PROFILE_IMAGE_PATH));
			file.transferTo(new File(fileUtil.REAL_CLASS_PATH, fileUtil.PROFILE_IMAGE_PATH + fileName));
			resumeVO.setImage_url(fileName);
		}
		resumeVO.setLast_saved_date(new Date());
		repository.insert(resumeVO);
	}
}
