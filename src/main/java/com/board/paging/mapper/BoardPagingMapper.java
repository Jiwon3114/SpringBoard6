package com.board.paging.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardDTO;
import com.board.menus.domain.MenuDTO;

@Mapper
public interface BoardPagingMapper {

	int count(MenuDTO menuDTO);

	List<BoardDTO> getBoardPagingList(String menu_id, int offset, int recordSize);

	void insertBoard(BoardDTO boardDTO);

	BoardDTO getBoard(BoardDTO boardDTO);

	BoardDTO getBoardByIdx(int idx);

	void incHit(int idx);

	void deleteBoard(String idx);

	void updateBoard(BoardDTO boardDTO);





	
	
}
