package com.board.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {
	private  int      bno;
	private  String   menu_id;
	private  String   title;
	private  String   content;
	private  String   writer;
	private  String   regdate;
	private  int      hit;
	public String getMenu_id() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getWriter() {
		// TODO Auto-generated method stub
		return null;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
}




