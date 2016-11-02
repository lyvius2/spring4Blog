package com.walter.dao;

import com.walter.model.PostSearchVO;
import com.walter.model.PostVO;

import java.util.List;

/**
 * Created by yhwang131 on 2016-10-12.
 */
public interface PostDao {
	int setPost(PostVO postVO);
	int delPost(int post_cd);
	PostVO getPost(int post_cd);
	List<PostVO> getPostList(PostSearchVO postSearchVO);
}