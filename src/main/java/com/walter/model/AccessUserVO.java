package com.walter.model;

import lombok.Data;

import javax.persistence.*;

/**
 * Access_User Entity (for JPA)
 * Created by Walter on 2017-07-22.
 */
@Data
@Table(name = "access_user")
@Entity
public class AccessUserVO {

	@Id
	private int seq;

	private String username;
	private String userlink;
	private String _id;

	@OneToOne(targetEntity = AccessVO.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "seq")
	private AccessVO accessVO;
}
