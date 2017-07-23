package com.walter.jpa;

import com.walter.model.AccessVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Access_Log Repository (for JPA)
 * Created by Walter on 2017-07-22.
 */
public interface AccessRepository extends CrudRepository<AccessVO, Long>, PagingAndSortingRepository<AccessVO, Long> {
	long countByMethod(String method);
	Page<AccessVO> findAllByOrderByBeginTimeDesc(Pageable pageable);
	Page<AccessVO> findByMethodOrderByBeginTimeDesc(String method, Pageable pageable);
}
