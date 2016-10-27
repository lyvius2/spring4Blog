package com.walter.service;

import com.walter.model.PostVO;
import org.springframework.ui.Model;

/**
 * Created by yhwang131 on 2016-10-27.
 */
public interface PostService {
	Model setInputForm(Model model);
	int setPost(PostVO postVO);
	PostVO getPost(int post_cd);
	//int updPost(PostVO postVO);
	//int delPost(int post_cd);
}
