package com.walter.service;

import com.walter.util.CustomStringUtils;
import com.walter.jpa.AccessUserRepository;
import com.walter.model.ResumeVO;
import com.walter.repository.ResumeRepository;
import com.walter.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by yhwang131 on 2017-06-23.
 */
@Service
public class ResumeServiceImpl implements ResumeService {

	@Autowired
	private ResumeRepository repository;

	@Autowired
	private AccessUserRepository userRepository;

	@Autowired
	private MongoOperations mongoOps;

	@Autowired
	private FileUtil fileUtil;

	@Override
	public ResumeVO getResume(String _id) {
		ResumeVO resume;
		if (CustomStringUtils.isEmpty(_id)) {
			Query query = new Query();
			query.limit(1);
			query.with(new Sort(Sort.Direction.DESC, "last_saved_date"));
			resume = mongoOps.findOne(query, ResumeVO.class);
		} else {
			resume = repository.findBy_id(_id);
		}
		if (resume != null) {
			if (!new File(fileUtil.REAL_CLASS_PATH, fileUtil.PROFILE_IMAGE_PATH + resume.getImage_url()).exists()) {
				resume.setImage_url(null);
			}
		}
		return resume == null ? new ResumeVO():resume;
	}

	@Override
	public List<ResumeVO> getResumeList() {
		return repository.findAllByOrderByLastSavedDateDesc();
	}

	@Override
	public void setResume(ResumeVO resumeVO, MultipartFile file) throws IOException {
		if (file.getSize() > 0) {
			String fileName = fileUtil.getRandomUniqueFileName(new File(fileUtil.REAL_CLASS_PATH, fileUtil.PROFILE_IMAGE_PATH));
			file.transferTo(new File(fileUtil.REAL_CLASS_PATH, fileUtil.PROFILE_IMAGE_PATH + fileName));
			resumeVO.setImage_url(fileName);
		}
		resumeVO.setLastSavedDate(new Date());
		repository.insert(resumeVO);
	}

	@Transactional(value = "jpaTransactionManager")
	@Override
	public Long removeResume(String _id) {
		userRepository.deleteBy_id(_id);
		return repository.deleteBy_id(_id);
	}
}
