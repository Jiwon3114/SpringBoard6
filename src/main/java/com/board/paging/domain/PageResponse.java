package com.board.paging.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PageResponse<T> {
	// 현재 페이지에 보여줄 db 자료 : select 결과
	private List<T>       list        = new ArrayList<>();
	
    private Pagination    pagination;

	public PageResponse(List<T> list, Pagination pagination) {
		this.list = list;
		this.pagination = pagination;
	}
	
	

}
