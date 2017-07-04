package com.walter.service;

import com.walter.model.*;
import com.walter.util.CRUD;
import com.walter.util.Message;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-27.
 */
public interface PostService {
	int setPost(PostVO postVO, CRUD crud);
	PostVO getPost(int post_cd);
	List<PostVO> getPostList(PostSearchVO postSearchVO);
	List<PostVO> getPostListByLucene(List<LuceneIndexVO> list);
	HashMap<String, Object> getPostListByPaging(PostSearchVO postSearchVO);

	Message setComment(CommentVO commentVO);
	CommentVO getCommentById(String _id);
	List<CommentVO> getComments(int postId);
	Message removeComment(String _id);

	Message setReply(String _id, ReplyVO replyVO);
	Message removeReply(String _id, int index);
}
