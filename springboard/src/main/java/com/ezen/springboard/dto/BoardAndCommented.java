package com.ezen.springboard.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class BoardAndCommented {
	private Integer board_id;
	private String write_title;
	private String write_content;
	private String writer_id;
	private String writer_pw;
	private Date write_date;
	private Integer write_view;
	private Integer write_recommend;
	private Integer write_not_recommend;
	private Integer count;
	private Integer comment_no;
	private String comment_id;
	private String comment_pw;
	private String comment_view;
	private Date comment_date;
	
	// 모든 인스턴스에서 같이 사용하기 때문에 메모리 절약을 위해...
		private static SimpleDateFormat dayFormat = new SimpleDateFormat("yy.MM.dd");
		private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
		// Getter를 만들어 놓으면 JSP의 EL에서는 필드가 있는것 처럼 사용할 수 있다.
		public String getCreationDateTime() {
			// Date 타입을 날짜 비교하기 편하도록 LocalDate 타입으로 변환

			// (1) DTO에는 java.util.Date 타입으로 설정 되어 있음
			// (2) 우리가 rs.getDate()로 꺼내면 java.sql.Date 타입으로 꺼내짐
			//     (java.sql.Date -> java.util.Date로 업캐스팅 발생)
			// (3) java.sql.Date의 toLocalDate()를 사용하기 위해서는 다운캐스팅이 필요
			LocalDate creationDate = ((java.sql.Date)comment_date).toLocalDate();

			LocalDate today = LocalDate.now();


			return creationDate.isEqual(today) ? 
					"오늘 " + timeFormat.format(comment_date)
					: dayFormat.format(comment_date);
		}
	
}
