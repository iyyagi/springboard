package com.ezen.springboard.dto;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class Commented {
	
	private Integer board_id;
	private Integer comment_no;
	private String comment_id;
	private String comment_pw;
	private String comment_view;
	private Date comment_date;
}
