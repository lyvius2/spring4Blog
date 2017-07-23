package com.walter.controller;

import com.walter.model.CodeVO;
import com.walter.model.LuceneIndexVO;
import com.walter.model.PostSearchVO;
import com.walter.model.PostVO;
import com.walter.service.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yhwang131 on 2016-08-24.
 */
@Controller
@RequestMapping("/api")
public class ApiController extends BaseController {

	@Autowired
	private ConfigService configService;

	@Autowired
	private LogService logService;

	@Autowired
	private PostService postService;

	@Autowired
	private LuceneService luceneService;

	@Autowired
	private GoogleDriveService googleDriveService;

	@RequestMapping(value = "/codeList")
	public ResponseEntity getCodeList(@ModelAttribute CodeVO codeVO) {
		return super.createResEntity(configService.getCodeList(codeVO));
	}

	@RequestMapping(value = "/postList")
	public ResponseEntity getPostList(@ModelAttribute PostSearchVO postSearchVO) throws IOException, ParseException {
		postSearchVO.setUse_yn(true);
		String searchText = postSearchVO.getSearchText();

		List<PostVO> postList = new ArrayList<>();
		if (StringUtils.isNotEmpty(searchText)) {
			List<LuceneIndexVO> resultFromLucene = luceneService.searchDataList(PostVO.class, searchText);
			if (resultFromLucene.size() > 0) {
				int limit = postSearchVO.getOffset() + postSearchVO.getRowsPerPage();
				if(limit > resultFromLucene.size()) limit = resultFromLucene.size();
				resultFromLucene = resultFromLucene.subList(postSearchVO.getOffset(), limit);
				postList = postService.getPostListByLucene(resultFromLucene);
			}
		} else {
			postList = postService.getPostList(postSearchVO);
		}
		return super.createResEntity(postList);
	}

	@RequestMapping(value = "/exceptionList")
	public ResponseEntity getExceptionList(@RequestParam(value = "currPageNo", required = false)int currPageNo,
	                                       @RequestParam(value = "exception", required = false)String exception) {
		return super.createResEntity(logService.getExceptionList(exception, currPageNo));
	}

	@RequestMapping(value = "/accessLogList")
	public ResponseEntity getAccessLogList(@RequestParam(value = "currPageNo", required = false)int currPageNo,
	                                       @RequestParam(value = "method", required = false)String method) {
		return super.createResEntity(logService.getAccessLogList(method, currPageNo));
	}

	@RequestMapping(value = "/resumeAccessLogList")
	public ResponseEntity getResumeAccessLogList(@RequestParam(value = "_id", required = false)String _id) {
		return super.createResEntity(logService.getResumeAccessLogList(_id));
	}

	@RequestMapping(value = "/image/{file_id}", produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] imgView(@PathVariable String file_id) throws IOException {
		HashMap<String, Object> hashMap = googleDriveService.openFile(file_id);
		return IOUtils.toByteArray((InputStream)hashMap.get("data"));
	}
}
