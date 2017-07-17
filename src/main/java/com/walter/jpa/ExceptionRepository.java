package com.walter.jpa;

import com.walter.model.ExceptionVO;
import jdk.nashorn.internal.objects.annotations.Where;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Exception_Log Repository (for JPA)
 * Created by yhwang131 on 2017-07-17.
 */
@Repository
public interface ExceptionRepository extends CrudRepository<ExceptionVO, Long> {

	ExceptionVO save(ExceptionVO exceptionVO);

	long count();

	long countByException(String exception);
}
