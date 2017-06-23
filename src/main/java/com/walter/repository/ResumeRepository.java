package com.walter.repository;

import com.walter.model.ResumeVO;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yhwang131 on 2017-06-20.
 */
@Repository
public interface ResumeRepository extends MongoRepository<ResumeVO, String> {

	ResumeVO findBy_id(String _id);

	ResumeVO insert(ResumeVO resumeVO);

	default ResumeVO findOneByLast_saved_date(MongoOperations mongoOps) {
		Query query = new Query();
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "last_saved_date"));
		return mongoOps.findOne(query, ResumeVO.class);
	}

}
