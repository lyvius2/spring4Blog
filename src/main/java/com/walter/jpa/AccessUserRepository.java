package com.walter.jpa;

import com.walter.model.AccessUserVO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Access_User Repository (for JPA)
 * Created by Walter on 2017-07-22.
 */
public interface AccessUserRepository extends CrudRepository<AccessUserVO, Long>, PagingAndSortingRepository<AccessUserVO, Long> {
}
