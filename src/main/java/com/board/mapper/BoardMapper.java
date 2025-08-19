package com.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.domain.BoardDTO;
import com.board.menus.domain.MenuDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> getBoardList( MenuDTO menuDTO );

	void insertBoard(BoardDTO boardDTO);

	BoardDTO getBoard(BoardDTO boardDTO);

	void incHit(BoardDTO boardDTO);

	void deleteBoard(BoardDTO boardDTO);

	void updateBoard(BoardDTO boardDTO);

}





