package com.walter.repository;

import com.walter.model.CommentVO;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Post - Comment Repository (for MongoDB)
 * Created by yhwang131 on 2016-11-03.
 */
@Repository
public interface CommentRepository extends MongoRepository<CommentVO, String> {
	List<CommentVO> findByPostCd(int postCd, Sort sort);
}
