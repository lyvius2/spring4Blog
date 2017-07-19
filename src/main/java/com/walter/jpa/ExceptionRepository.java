package com.walter.jpa;

import com.walter.model.ExceptionVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Exception_Log Repository (for JPA)
 * Created by yhwang131 on 2017-07-17.
 */
@Repository
public interface ExceptionRepository extends CrudRepository<ExceptionVO, Long>, PagingAndSortingRepository<ExceptionVO, Long> {

	ExceptionVO save(ExceptionVO exceptionVO);

	long count();

	long countByException(String exception);

	Page<ExceptionVO> findAllByOrderByRegDtDesc(Pageable pageable);

	Page<ExceptionVO> findByExceptionOrderByRegDtDesc(String exception, Pageable pageable);
}
