package com.walter.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Access_Log Entity (for JPA)
 * Created by Walter on 2017-07-22.
 */
@Data
@Table(name = "access_log")
@Entity
public class AccessVO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int seq;

	private String target_class;
	private String request_path;
	private String ip;
	private String agent;
	private String method;

	@Column(name = "begin_time")
	private Date beginTime;

	@Column(name = "end_time")
	private Date endTime;

	private long proceed_time;
}
