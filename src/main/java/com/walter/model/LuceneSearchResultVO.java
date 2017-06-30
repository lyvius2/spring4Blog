package com.walter.model;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;

/**
 * Created by yhwang131 on 2017-06-30.
 */
@ToString
@Slf4j
@RequiredArgsConstructor
public class LuceneSearchResultVO {
	@NonNull private IndexSearcher indexSearcher;
	@NonNull private ScoreDoc[] scoreDocs;

	public int getSize() {
		return scoreDocs.length;
	}

	public Document getDocument(int i) {
		try {
			return indexSearcher.doc(scoreDocs[i].doc);
		} catch(Exception e) {
			log.error(e.toString());
			return null;
		}
	}
}
