package com.walter.repository;

import com.walter.model.ResumeVO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yhwang131 on 2017-06-20.
 */
@Repository
public interface ResumeRepository extends MongoRepository<ResumeVO, String> {

	ResumeVO findBy_id(String _id);

}
