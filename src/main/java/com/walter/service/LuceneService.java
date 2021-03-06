package com.walter.service;

import com.walter.model.LuceneIndexVO;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.ParseException;

import java.io.IOException;
import java.util.List;

/**
 * Created by yhwang131 on 2017-06-29.
 */
public interface LuceneService {
	void createIndex(List<? extends LuceneIndexVO> list) throws IOException;
	void addIndex(LuceneIndexVO luceneIndexVO) throws IOException, ParseException;
	List searchDataList(Class<? extends LuceneIndexVO> itemType, String searchText) throws IOException, ParseException;
	void updateIndex(LuceneIndexVO luceneIndexVO) throws IOException, ParseException;
	void removeIndex(String seq, Class<? extends LuceneIndexVO> itemType) throws IOException, ParseException;
	int indexLength(LuceneIndexVO luceneIndexVO) throws IOException;
}
