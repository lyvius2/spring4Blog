package com.walter.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by yhwang131 on 2016-10-11.
 */
@Data
@RequiredArgsConstructor
public class PostVO implements LuceneIndexVO {

	private int post_cd;

	@NotNull(message = "제목은 반드시 입력하세요.")
	@Size(max = 100, message = "50글자 이하로 입력하십시오.")
	private String title;

	private String content;
	private int category_cd;
	private String category_name;
	private Boolean use_yn;
	private Boolean comment_yn;
	private Date reg_dt;
	private String df_reg_dt;
	private String reg_id;
	private Date mod_dt;
	private String df_mod_dt;
	private String mod_id;
	private String delegate_img;
	private MultipartFile delegate_img_file;
	private String post_theme_cd;

	public PostVO(Boolean use_yn, Boolean comment_yn) {
		this.use_yn = use_yn;
		this.comment_yn = comment_yn;
	}

	@Override
	public String getSeq() {
		return Integer.toString(this.post_cd);
	}

	@Override
	public void setSeq(String seq) {
		this.post_cd = Integer.parseInt(seq);
	}
}
