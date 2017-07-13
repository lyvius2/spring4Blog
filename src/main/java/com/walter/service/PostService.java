package com.walter.service;

import com.walter.model.*;
import com.walter.config.code.DataProcessing;
import com.walter.config.code.Message;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2016-10-27.
 */
public interface PostService {
	int setPost(PostVO postVO, DataProcessing dataProcessing) throws IOException, ParseException;
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
