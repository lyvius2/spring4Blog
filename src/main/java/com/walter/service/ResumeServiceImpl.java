package com.walter.service;

import com.walter.config.CustomStringUtils;
import com.walter.model.ResumeVO;
import com.walter.repository.ResumeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yhwang131 on 2017-06-23.
 */
@Service
public class ResumeServiceImpl implements ResumeService {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ResumeRepository repository;

	@Override
	public ResumeVO getDefaultResume(String _id) {
		ResumeVO resume = new ResumeVO();
		if (CustomStringUtils.isEmpty(_id)) {
			List<ResumeVO> resumeList = repository.findAll();
			if (resumeList.size() > 0) resume = resumeList.get(0);
		} else {
			resume = repository.findBy_id(_id);
		}
		return resume;
	}
}
