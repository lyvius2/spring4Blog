package com.walter.repository;

import com.walter.model.CommentVO;
import com.walter.model.ReplyVO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * Created by yhwang131 on 2016-11-03.
 */
@Repository
public interface CommentRepository extends MongoRepository<CommentVO, String> {

	List<CommentVO> findByPostCd(int postCd);

	default CommentVO insertReply(MongoOperations mongoOps, String _id, ReplyVO replyVO) {
		return mongoOps.findAndModify(query(where("_id").is(new ObjectId(_id))),
				new Update().push("replys", replyVO),
				new FindAndModifyOptions().returnNew(true),
				CommentVO.class);
	}
}
