package com.walter.service;

import com.walter.jpa.AccessRepository;
import com.walter.jpa.AccessUserRepository;
import com.walter.jpa.ExceptionRepository;
import com.walter.model.AccessUserVO;
import com.walter.model.AccessVO;
import com.walter.model.ExceptionVO;
import com.walter.model.PagingVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;

/**
 * Log Service (Exception, Access)
 * Created by yhwang131 on 2017-07-18.
 */
@Service
@Transactional(value = "jpaTransactionManager")
public class LogServiceImpl implements LogService {

	@Autowired
	private ExceptionRepository expRepository;

	@Autowired
	private AccessRepository accRepository;

	@Autowired
	private AccessUserRepository userRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public ExceptionVO setException(ExceptionVO exceptionVO) {
		return expRepository.save(exceptionVO);
	}

	@Override
	public HashMap<String, Object> getExceptionList(String exception, int currPageNo) {
		boolean isFilter = StringUtils.isNotEmpty(exception);
		PageRequest pageRequest = new PageRequest(currPageNo - 1, 10);
		Page<ExceptionVO> voPage = isFilter ?
				expRepository.findByExceptionOrderByRegDtDesc(exception, pageRequest):expRepository.findAllByOrderByRegDtDesc(pageRequest);
		long count = isFilter ? expRepository.countByException(exception):expRepository.count();

		return rtnPagingDataMap(voPage, currPageNo, count);
	}

	@Override
	public List<String> getExceptionOptions() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ExceptionVO> query = builder.createQuery(ExceptionVO.class);
		Root<ExceptionVO> exceptionOps = query.from(ExceptionVO.class);
		query.groupBy(exceptionOps.get("exception"));
		List result = entityManager.createQuery(query.select(exceptionOps.get("exception"))).getResultList();
		return result;
	}

	@Override
	public void setAccessLog(AccessVO accessVO, AccessUserVO accessUserVO) {
		AccessVO result = accRepository.save(accessVO);
		if (accessUserVO != null) {
			accessUserVO.setSeq(result.getSeq());
			userRepository.save(accessUserVO);
		}
	}

	@Override
	public HashMap<String, Object> getAccessLogList(String method, int currPageNo) {
		boolean isFilter = StringUtils.isNotEmpty(method);
		PageRequest pageRequest = new PageRequest(currPageNo - 1, 10);
		Page<AccessVO> voPage = isFilter ?
				accRepository.findByMethodOrderByBeginTimeDesc(method, pageRequest):accRepository.findAllByOrderByBeginTimeDesc(pageRequest);
		long count = isFilter ? accRepository.countByMethod(method):accRepository.count();

		return rtnPagingDataMap(voPage, currPageNo, count);
	}

	@Override
	public List<String> getAccessLogOptions() {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<AccessVO> query = builder.createQuery(AccessVO.class);
		Root<AccessVO> accessLogOps = query.from(AccessVO.class);
		query.groupBy(accessLogOps.get("method"));
		query.orderBy(builder.asc(accessLogOps.get("method")));
		List result = entityManager.createQuery(query.select(accessLogOps.get("method"))).getResultList();
		return result;
	}

	@Override
	public List getResumeAccessLogList(String _id) {
		return userRepository.findAllBy_idOrderBySeqDesc(_id);
	}

	private HashMap<String, Object> rtnPagingDataMap(Page page, int currPageNo, long count) {
		PagingVO pagingVO = new PagingVO(currPageNo, 10);
		pagingVO.setNumberOfRows((int) count);
		pagingVO.Paging();

		HashMap<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", page.getContent());
		resultMap.put("paging", pagingVO);
		return resultMap;
	}
}
