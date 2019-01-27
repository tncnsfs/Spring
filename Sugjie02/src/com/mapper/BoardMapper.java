package com.mapper;

import java.util.List;

import com.model.Board;

public interface BoardMapper {
	int insertBoard(Board board);
	int updateBoard(Board board);
	int deleteBoard(Board board);
	
	List<Board> listBoard();
	Board getBoard(int seq);
}







