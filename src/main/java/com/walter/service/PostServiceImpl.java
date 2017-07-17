package com.walter.service;

import com.walter.config.CustomStringUtils;
import com.walter.dao.PostDao;
import com.walter.model.*;
import com.walter.repository.CommentRepository;
import com.walter.util.CommonUtil;
import com.walter.config.code.Message;
import com.walter.config.code.DataProcessing;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-27.
 */
@Slf4j
@Service
@Transactional(value = "transactionManager")
public class PostServiceImpl implements PostService {

	@Autowired
	private PostDao dao;

	@Autowired
	private CommentRepository repository;

	@Autowired
	private MongoOperations mongoOps;

	@Autowired
	private LuceneService luceneService;

	private static String DATE_FORMAT = "yyyy-MM-dd HH:mm";

	@Override
	public int setPost(PostVO postVO, DataProcessing dataProcessing) throws IOException, ParseException {
		int result = 0;
		switch(dataProcessing) {
			case CREATE:
				result = dao.insPost(postVO);
				// 인덱싱 존재 여부 확인 뒤 없으면 새로 생성하고 있으면 추가만 진행
				if (luceneService.indexLength(postVO) > 0) luceneService.addIndex(postVO);
				else luceneService.createIndex(dao.getPostList(new PostSearchVO()));
				break;
			case UPDATE:
				result = dao.modPost(postVO);
				luceneService.updateIndex(postVO);
				break;
			case DELETE:
				result = dao.delPost(postVO.getPost_cd());
				luceneService.removeIndex(postVO.getSeq(), PostVO.class);
				break;
		}
		return result;
	}

	@Override
	public PostVO getPost(int post_cd) {
		PostVO postVO = dao.getPost(post_cd);
		postVO.setDf_reg_dt(CustomStringUtils.dateToString(postVO.getReg_dt(), DATE_FORMAT));
		if (postVO.getMod_dt() != null) {
			postVO.setDf_mod_dt(CustomStringUtils.dateToString(postVO.getMod_dt(), DATE_FORMAT));
		}
		return postVO;
	}

	@Override
	public List<PostVO> getPostList(PostSearchVO postSearchVO) {
		return dao.getPostList(postSearchVO);
	}

	@Override
	public List<PostVO> getPostListByLucene(List<LuceneIndexVO> list) {
		return dao.getPostListByLucene(list);
	}

	@Override
	public HashMap<String, Object> getPostListByPaging(PostSearchVO postSearchVO) throws IndexOutOfBoundsException {
		PagingVO pagingVO = new PagingVO(postSearchVO.getCurrPageNo(), postSearchVO.getRowsPerPage());
		int offset = (pagingVO.getCurrPageNo() - 1) * pagingVO.getRowsPerPage();
		postSearchVO.setOffset(offset);
		List<PostVO> postVOList = dao.getPostList(postSearchVO);
		List<PostVO> resultList = new ArrayList<>();

		if(postVOList.size() > 0) {
			int limit = offset + pagingVO.getRowsPerPage();
			if(limit > postVOList.size()) limit = postVOList.size();
			resultList = postVOList.subList(offset, limit);
			resultList.stream().forEach(p -> p.setDf_reg_dt(CustomStringUtils.dateToString(p.getReg_dt(), DATE_FORMAT)));
		}
		pagingVO.setNumberOfRows(postVOList.size());
		pagingVO.Paging();

		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("postList", resultList);
		hashMap.put("paging", pagingVO);
		return hashMap;
	}

	@Override
	public Message setComment(CommentVO commentVO) {
		if (CustomStringUtils.isNotEmpty(commentVO.getComment())) {
			commentVO.setUserData(CommonUtil.getLoginUserInfo());
			repository.insert(commentVO);
			return null;
		} else {
			return Message.ERROR_COMMENT_NOT_EXIST;
		}
	}

	@Override
	public CommentVO getCommentById(String _id) {
		return repository.findOne(_id);
	}

	@Override
	public List<CommentVO> getComments(int postCd) {
		return repository.findByPostCd(postCd, new Sort(Sort.Direction.ASC, "_id"));
	}

	@Override
	public Message removeComment(String _id) {
		CommentVO commentVO = repository.findOne(_id);
		Message message = removeValidation(commentVO, commentVO.getLink());
		if (message != null) return message;
		if (commentVO.getReplys().size() > 0) return Message.ERROR_REMOVE_HAVE_REPLY;
		repository.delete(_id);
		return null;
	}

	@Override
	public Message setReply(String _id, ReplyVO replyVO) {
		if(CustomStringUtils.isNotEmpty(replyVO.getComment())) {
			replyVO.setUserData(CommonUtil.getLoginUserInfo());
			repository.insertReply(mongoOps, _id, replyVO);
			return null;
		} else {
			return Message.ERROR_COMMENT_NOT_EXIST;
		}
	}

	@Override
	public Message removeReply(String _id, int index) {
		CommentVO commentVO = repository.findOne(_id);
		Message message = removeValidation(commentVO, commentVO.getReplys().get(index).getLink());
		if (message != null) return message;
		commentVO.getReplys().remove(index);
		repository.removeReply(mongoOps, _id, commentVO.getReplys());
		return null;
	}

	/**
	 * 삭제 대상이 없는 경우, 삭제 권한 유무 체크
	 * @param commentVO
	 * @param link
	 * @return
	 */
	private Message removeValidation(CommentVO commentVO, String link) {
		if (commentVO == null) return Message.ERROR_REMOVE_NOT_EXIST;
		if (CommonUtil.getLoginUserInfo() == null ||
				!link.equals(CommonUtil.getLoginUserInfo().getLink())) {
			return Message.ERROR_REMOVE_PERMISSION;
		}
		return null;
	}
}
