package com.board.menus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo {

	@NonNull
	private  String  menu_id;	
	private  String  menu_name;
	private  int     menu_seq;
	public String getMenu_id() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public int getMenu_seq() {
		return menu_seq;
	}
	public void setMenu_seq(int menu_seq) {
		this.menu_seq = menu_seq;
	}
		
}
