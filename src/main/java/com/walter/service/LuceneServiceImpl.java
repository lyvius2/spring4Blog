package com.walter.service;

import static com.walter.config.lambda.LambdaUtilForException.reThrowsConsumer;
import static com.walter.config.lambda.LambdaUtilForException.reThrowsFunction;

import com.walter.config.CustomStringUtils;
import com.walter.model.LuceneIndexVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by yhwang131 on 2017-06-29.
 */
@Slf4j
@Service
public class LuceneServiceImpl implements LuceneService {
	private static final String LUCENE_INDEX_DIR = System.getProperty("user.home") + "/.lucene/data";
	private static final String FIELD = "SEARCH_ALL";
	private static final String KEY_FIELD = "KEY";
	private static final int DEFAULT_LIMIT_COUNT = 100000;

	@Autowired
	private Analyzer analyzer;

	/**
	 * List 형태의 데이터를 받아 Lucene을 이용한 인덱싱 생성
	 * @param list
	 * @throws IOException
	 */
	@Override
	public void createIndex(List<? extends LuceneIndexVO> list) throws IOException {
		if (list.size() > 0) {
			File file = new File(LUCENE_INDEX_DIR, list.get(0).getClass().getSimpleName());
			if (!file.exists()) file.mkdirs();
			FSDirectory fsDirectory = FSDirectory.open(Paths.get(file.getPath()));

			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
			indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

			IndexWriter indexWriter = new IndexWriter(fsDirectory, indexWriterConfig);

			FieldType fieldType = new FieldType();
			fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
			fieldType.setStored(true);
			fieldType.setTokenized(true);
			fieldType.freeze();

			indexWriter.deleteAll();
			list.stream().filter(i -> StringUtils.isNotEmpty(i.getSeq())).forEach(reThrowsConsumer(i -> {
				Document document = new Document();
				document.add(new Field("SEQ", i.getSeq(), fieldType));
				document.add(new Field("TITLE", i.getTitle(), fieldType));
				document.add(new Field("CONTENT", i.getContent(), fieldType));

				document.add(new Field(KEY_FIELD, StringUtils.stripToEmpty(i.getSeq()), fieldType));
				document.add(new Field(FIELD, StringUtils.stripToEmpty(i.getTitle()), fieldType));
				document.add(new Field(FIELD, StringUtils.stripToEmpty(i.getContent()), fieldType));
				indexWriter.addDocument(document);
			}));

			indexWriter.commit();
			indexWriter.close();
		}
	}

	/**
	 * Lucene 인덱스 추가
	 * @param luceneIndexVO
	 * @throws IOException
	 * @throws ParseException
	 */
	@Override
	public void addIndex(LuceneIndexVO luceneIndexVO) throws IOException, ParseException {
		IndexWriter indexWriter = this.getIndexWriter(
				new File(LUCENE_INDEX_DIR, luceneIndexVO.getClass().getSimpleName()),
				IndexWriterConfig.OpenMode.CREATE_OR_APPEND
		);
		FieldType fieldType = new FieldType();
		fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
		fieldType.setStored(true);
		fieldType.setTokenized(true);
		fieldType.freeze();

		Document document = new Document();
		document.add(new Field("SEQ", luceneIndexVO.getSeq(), fieldType));
		document.add(new Field("TITLE", luceneIndexVO.getTitle(), fieldType));
		document.add(new Field("CONTENT", luceneIndexVO.getContent(), fieldType));

		document.add(new Field(KEY_FIELD, StringUtils.stripToEmpty(luceneIndexVO.getSeq()), fieldType));
		document.add(new Field(FIELD, StringUtils.stripToEmpty(luceneIndexVO.getTitle()), fieldType));
		document.add(new Field(FIELD, StringUtils.stripToEmpty(luceneIndexVO.getContent()), fieldType));
		indexWriter.addDocument(document);

		indexWriter.commit();
		indexWriter.close();
	}

	/**
	 * 검색어를 받아 Lucene 엔진을 통해 인덱싱에서 찾아 검색 결과를 List 형식으로 반환
	 * @param itemType
	 * @param searchText
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	@Override
	public List searchDataList(Class<? extends LuceneIndexVO> itemType, String searchText) throws IOException, ParseException {
		File file = new File(LUCENE_INDEX_DIR, itemType.getSimpleName());
		IndexReader indexReader = DirectoryReader.open(FSDirectory.open(Paths.get(file.getPath())));
		IndexSearcher indexSearcher = new IndexSearcher(indexReader);

		QueryParser parser = new QueryParser(FIELD, analyzer);

		List result = new ArrayList<>();
		if (CustomStringUtils.isNotEmpty(searchText)) {
			Query query = parser.parse(StringUtils.stripToEmpty(searchText));
			log.info("### Searching for : {}", query.toString());
			SortField sortField = new SortField("SEQ", SortField.Type.DOC, true);
			Sort sort = new Sort(sortField);

			TopDocs results = indexSearcher.search(query, DEFAULT_LIMIT_COUNT, sort);
			//ScoreDoc[] hits = results.scoreDocs;

			Stream<ScoreDoc> scoreDocStream = Arrays.asList(results.scoreDocs).stream();
			scoreDocStream.map(reThrowsFunction(d -> indexSearcher.doc(d.doc))).forEach(reThrowsConsumer(d -> {
				LuceneIndexVO idx = itemType.newInstance();
				log.debug("d.toString() : {}", d.toString());
				idx.setSeq(d.get("SEQ"));
				idx.setTitle(d.get("TITLE"));
				idx.setContent(d.get("CONTENT"));
				log.debug("IDX : {}", idx.toString());
				result.add(idx);
			}));
		}
		log.debug("Index Length : {}", result.size());
		return result;
	}

	/**
	 * Lucene 인덱스 내용을 업데이트
	 * @param luceneIndexVO
	 * @throws IOException
	 * @throws ParseException
	 */
	@Override
	public void updateIndex(LuceneIndexVO luceneIndexVO) throws IOException, ParseException {
		this.removeIndex(luceneIndexVO.getSeq(), luceneIndexVO.getClass());
		this.addIndex(luceneIndexVO);
	}

	/**
	 * Lucene 인덱스 삭제
	 * @param seq
	 * @param itemType
	 * @throws IOException
	 * @throws ParseException
	 */
	@Override
	public void removeIndex(String seq, Class<? extends LuceneIndexVO> itemType) throws IOException, ParseException {
		File file = new File(LUCENE_INDEX_DIR, itemType.getSimpleName());
		if (file.exists()) {
			IndexWriter indexWriter = this.getIndexWriter(file, IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
			log.info("### Index Length : {}", indexWriter.numDocs());
			Query query = new QueryParser(KEY_FIELD, analyzer).parse(seq);
			indexWriter.deleteDocuments(query);
			indexWriter.commit();
			log.info("### Remainder in Index : {}", indexWriter.numDocs());
			indexWriter.close();
		}
	}

	/**
	 * Lucene Index Length 반환
	 * @param luceneIndexVO
	 * @return
	 * @throws IOException
	 */
	@Override
	public int indexLength(LuceneIndexVO luceneIndexVO) throws IOException {
		File file = new File(LUCENE_INDEX_DIR, luceneIndexVO.getClass().getSimpleName());
		if (file.exists()) {
			IndexWriter indexWriter = this.getIndexWriter(file, IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
			int result = indexWriter.numDocs();
			indexWriter.close();
			return result;
		}
		return 0;
	}

	/**
	 * 사용할 수 있는 IndexWriter 객체를 반환
	 * @param file
	 * @param openMode
	 * @return
	 * @throws IOException
	 */
	private IndexWriter getIndexWriter(File file, IndexWriterConfig.OpenMode openMode) throws IOException {
		FSDirectory fsDirectory = FSDirectory.open(Paths.get(file.getPath()));
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
		indexWriterConfig.setOpenMode(openMode);
		return new IndexWriter(fsDirectory, indexWriterConfig);
	}
}
