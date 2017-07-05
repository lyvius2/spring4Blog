package com.walter.controller;

import com.walter.model.CodeVO;
import com.walter.model.LuceneIndexVO;
import com.walter.model.PostSearchVO;
import com.walter.model.PostVO;
import com.walter.service.ConfigService;
import com.walter.service.LuceneService;
import com.walter.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
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
	private PostService postService;

	@Autowired
	private LuceneService luceneService;

	@RequestMapping(value = "/codeList", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getCodeList(@ModelAttribute CodeVO codeVO) {
		return gson.toJson(configService.getCodeList(codeVO));
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
}
