package com.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVo {

    private int page;             // 현재 페이지 번호
    private int recordSize;       // 페이지당 출력할 데이터 개수
    private int pageSize;         // 화면 하단에 출력할 페이지 사이즈
    private String keyword;       // 검색 키워드
    private String searchType;    // 검색 유형
    
    private Pagination pagination;    // pagination 정보

    public SearchVo() {
        this.page = 1;
        this.recordSize = 10;
        this.pageSize = 10;
    }

    public int getOffset() {
        return (page - 1) * recordSize;
    }

	public void setPage(int nowpage) {
		// TODO Auto-generated method stub
		
	}

	public void setPageSize(int i) {
		// TODO Auto-generated method stub
		
	}

	public void setPagination(Pagination pagination2) {
		// TODO Auto-generated method stub
		
	}

	public int getPageSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getRecordSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getPage() {
		// TODO Auto-generated method stub
		return 0;
	}

}

