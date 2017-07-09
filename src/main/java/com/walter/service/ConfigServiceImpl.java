package com.walter.service;

import com.walter.dao.CategoryDao;
import com.walter.dao.CodeDao;
import com.walter.dao.MemberDao;
import com.walter.model.CategoryVO;
import com.walter.model.CodeVO;
import com.walter.config.code.Message;
import com.walter.model.MemberVO;
import com.walter.util.CommonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by yhwang131 on 2017-05-23.
 */
@Service
public class ConfigServiceImpl implements ConfigService {
	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private CodeDao codeDao;

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private ShaPasswordEncoder encoder;

	@Override
	public Message insAdmin(MemberVO memberVO, boolean isCreate) {
		if(StringUtils.isNotEmpty(memberVO.getPassword())) memberVO.setPassword(encoder.encodePassword(memberVO.getPassword(), null));
		if(isCreate) {
			HashMap<String, Object> paramsMap = new HashMap<>();
			paramsMap.put("username", memberVO.getUsername());
			if(memberDao.getMember(paramsMap) != null) {
				return Message.DUPLE_MEMBER_ID;
			}
			memberVO.setReg_id(CommonUtil.getLoginUserInfo().getUsername());
			memberDao.insMember(memberVO);
		} else {
			memberVO.setMod_id(CommonUtil.getLoginUserInfo().getUsername());
			memberDao.modMember(memberVO);
		}
		return Message.SAVED;
	}

	@Override
	public List<MemberVO> getMemberList() {
		return memberDao.getMemberList();
	}

	@Override
	@Cacheable(value = "categoryCache")
	public List<CategoryVO> getCategoryList() {
		return categoryDao.getCategoryList();
	}

	@Override
	public CategoryVO getCategoryItemByCd(int category_cd) {
		return categoryDao.getCategoryItemByCd(category_cd);
	}

	@Override
	@CacheEvict(value = "categoryCache", allEntries = true)
	public HashMap insCategoryItem(CategoryVO categoryVO) {
		HashMap resultMap = new HashMap();
		try {
			if (categoryDao.getCategoryCountByName(categoryVO.getCategory_name()) == 0) {
				int result = categoryDao.insCategoryItem(categoryVO);
				resultMap.put("success", result == 1 ? true:false);
			} else {
				resultMap.put("success", false);
				resultMap.put("message", Message.DUPLE_CATEGORY.getText());
			}
		} catch (Exception e) {
			logger.error(e.toString());
			resultMap.put("success", false);
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}

	@Override
	@CacheEvict(value = "categoryCache", allEntries = true)
	public HashMap modCategoryItem(CategoryVO categoryVO, String targetAttribute) {
		HashMap paramsMap = new HashMap(), resultMap = new HashMap();
		try {
			paramsMap.put("mod_id", categoryVO.getMod_id());
			paramsMap.put("category_cd", categoryVO.getCategory_cd());
			switch (targetAttribute) {
				case "Usage" :
					paramsMap.put("use_yn", categoryVO.isUse_yn());
					break;
				case "Order" :
					paramsMap.put("order_no", categoryVO.getOrder_no());
					break;
				case "Name" :
					paramsMap.put("category_name", categoryVO.getCategory_name());
					break;
			}
			int result = categoryDao.modCategoryItem(paramsMap);
			resultMap.put("success", result == 1 ? true:false);
		} catch (Exception e) {
			logger.error(e.toString());
			resultMap.put("success", false);
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}

	@Override
	@CacheEvict(value = "categoryCache", allEntries = true)
	public HashMap delCategoryItem(int category_cd) {
		HashMap resultMap = new HashMap();
		try {
			int result = categoryDao.delCategoryItem(category_cd);
			resultMap.put("success", result == 1 ? true:false);
		} catch (Exception e) {
			logger.error(e.toString());
			resultMap.put("success", false);
			resultMap.put("message", e.getMessage());
		}
		return resultMap;
	}

	@Override
	public List<CodeVO> getCodeList(CodeVO codeVO) {
		return codeDao.getCodeList(codeVO);
	}
}
