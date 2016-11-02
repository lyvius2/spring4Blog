package com.walter.service;

import com.walter.model.CommentVO;
import com.walter.model.PostCommentVO;
import com.walter.model.PostSearchVO;
import com.walter.model.PostVO;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-27.
 */
public interface PostService {
	Model setInputForm(Model model);
	int setPost(PostVO postVO);
	PostVO getPost(int post_cd);
	HashMap<String, Object> getPostList(PostSearchVO postSearchVO);
	//int updPost(PostVO postVO);
	//int delPost(int post_cd);

	void setComment(CommentVO commentVO);
	CommentVO setReply(String _id, CommentVO commentVO);
	CommentVO getCommentById(String _id);
	List<CommentVO> getComments(int parentPostId);

}
