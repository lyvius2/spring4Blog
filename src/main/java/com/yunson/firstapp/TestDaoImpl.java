package com.yunson.firstapp;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

/**
 * Created by yhwang131 on 2016-08-04.
 */
@Repository
public class TestDaoImpl implements TestDao {

	@Autowired
	private SqlSession query;

	@Override
	public String getTest() throws SQLException {
		return query.selectOne("query.getTest");
	}
}
