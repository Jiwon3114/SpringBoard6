package com.board.paging.controller;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

import javax.crypto.SealedObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardDTO;
import com.board.menus.domain.MenuDTO;
import com.board.menus.mapper.MenuMapper;
import com.board.paging.domain.PageResponse;
import com.board.paging.domain.Pagination;
import com.board.paging.domain.SearchDTO;
import com.board.paging.mapper.BoardPagingMapper;

@Controller
public class BoardPagingController {
		
	@Autowired
	private  MenuMapper          menuMapper;
	
	@Autowired
	private  BoardPagingMapper   boardPagingMapper;
	
	// http://localhost:9090/BoardPaging/List?nowpage=1&menu_id=MENU01
	@RequestMapping("/BoardPaging/List")
	public  ModelAndView   list(int nowpage, MenuDTO menuDTO) {
		
		// 메뉴 목록 조회
		List<MenuDTO>  menuList = menuMapper.getMenuList();
		
		// 게시물 목록 조회
		// menu_id = MENU01
		// nowpage : 2
		//   줄번호 11 ~ 20 번까지 자려를 조회
		// 해당 menu_id 의 총 자료수 구하기
		int count = boardPagingMapper.count( menuDTO );  // menu_id
		System.out.println( "자료수" + count );
		
		// page로 조회한 결과를 담아놓을 객체
		PageResponse<BoardDTO> response = null;
		if( count < 1) {  // Menu_id 의 자료가 없다면
			response = new PageResponse<>(
					Collections.emptyList(), null
				); // 생성자를 이용해서 초기화 하겠다
				// Collections.emptyList() : 자료가 없은 빈 리스트를 만들어서 채운다
		}
		
		// 페이징을 위한 초기설정
		SearchDTO   searchDTO =  new SearchDTO();
		searchDTO.setPage(nowpage);   // 현재 페이지 정보
		searchDTO.setRecordSize(2);  // 페이지당 10 rows 가지고 온다
		searchDTO.setPageSize(10);    // paging.jsp 출력할 페이지번호수
		
		// Pagination 설정
		Pagination    pagination  =  new Pagination(count, searchDTO);
		searchDTO.setPagination( pagination );
		
		// ---------------------------------------------------
		int  offset     = searchDTO.getOffset();      // 30  
		int  recordSize = searchDTO.getRecordSize();  // 10;	   	
		String menu_id  = menuDTO.getMenu_id();
		
		List<BoardDTO> list = boardPagingMapper.getBoardPagingList(
			     menu_id, offset, recordSize	
			);
		System.out.println("0:" +  list );
		
		response  =  new PageResponse<>(list, pagination);
		
		menuDTO   =  menuMapper.getMenu( menuDTO );
		System.out.println("menuDTO:" + menuDTO);
				
		ModelAndView   mv  =  new  ModelAndView();
		mv.addObject("menuList",  menuList);
		mv.addObject("menu_id",   menu_id);
		mv.addObject("menuDTO",   menuDTO);
		mv.addObject("searchDTO", searchDTO);
		mv.addObject("nowpage",   nowpage);
		
		mv.addObject("response", response);
		
		mv.setViewName("boardpaging/list");
		
		return  mv;
	}
	
	// http://localhost:9090/BoardPaging/WriteForm?nowpage=1&menu_id=MENU01
	@RequestMapping("/BoardPaging/WriteForm")
	public  ModelAndView  writeForm(int nowpage, String menu_id) {
		
		List<MenuDTO>  menuList  =  menuMapper.getMenuList();
		
		MenuDTO        menuDTO   =  menuMapper.getMenuName(menu_id);
		
		ModelAndView   mv   =  new  ModelAndView();
		mv.addObject("menuList",   menuList );
		mv.addObject("nowpage",    nowpage  );
		mv.addObject("menuDTO",    menuDTO  );
		mv.setViewName("boardpaging/write");
		return         mv;
		
	}
	
	@RequestMapping("/BoardPaging/Write")
	public  ModelAndView   write( int nowpage, BoardDTO boardDTO ) {
		
		// 넘어온 글 저장
		boardPagingMapper.insertBoard(  boardDTO  );
		
		ModelAndView   mv   =  new ModelAndView();
		String         fmt  =  
				"redirect:/BoardPaging/List?menu_id=%s&nowpage=%d";
		String         loc  = String.format(fmt, 
				boardDTO.getMenu_id(), nowpage);
		mv.setViewName( loc );
		return         mv;
	}
	
	// http://localhost:9090/BoardPaging/View?idx=21&nowpage=1&menu_id=MENU01
	@RequestMapping("/BoardPaging/View")
	public  ModelAndView  view( int idx, int nowpage, String menu_id ) {
		
		List<MenuDTO>  menuList  =  menuMapper.getMenuList();
		
		// 해당게시물의 조회수를 증가한다
		boardPagingMapper.incHit( idx  );
		
		// 보여줄 게시물의 정보 조회 (idx)
		BoardDTO       board     =  boardPagingMapper.getBoardByIdx( idx );
		
		
		// board Content \n -> <br>
		String         content   =  board.getContent();
		content                  =  content.replace("\n", "<br>");
		board.setContent( content );
		
		MenuDTO        menuDTO   =  menuMapper.getMenuName( menu_id );		
		
		ModelAndView   mv  =  new ModelAndView();
		mv.addObject("menuList", menuList  );
		mv.addObject("menuDTO",  menuDTO   );
		mv.addObject("board",    board     );
		mv.addObject("nowpage",  nowpage   );
		mv.setViewName( "boardpaging/view" );
		return         mv;
		
	}
	
	// http://localhost:9090/BoardPaging/Delete?idx=22&menu_id=MENU01&nowpage=1
	@RequestMapping("/BoardPaging/Delete")
	public  ModelAndView   delete(String idx, String menu_id, int nowpage ) {
		
		// 넘어온 idx 로 게시글을 삭제한다
		boardPagingMapper.deleteBoard( idx );
		
		ModelAndView   mv   =  new  ModelAndView();
		String         fmt  =  "redirect:/BoardPaging/List?menu_id={0}&nowpage={1}";
		String         loc  =  MessageFormat.format(fmt, menu_id, nowpage );
		mv.setViewName( loc );
		return         mv;
		
	}
	
	// Get 방식 전송 :  <a href="">, location.href="", redirect:
	// query string 
	// http://localhost:9090/BoardPaging/UpdateForm?idx=20&menu_id=MENU01&nowpage=1
	@RequestMapping("/BoardPaging/UpdateForm")
	public  ModelAndView   updateForm(int idx, String menu_id, int nowpage) {
		
		List<MenuDTO>  menuList   =  menuMapper.getMenuList(); 
		
		// 넘겨줄 현재 메뉴의 정보
		MenuDTO       menuDTO     =  menuMapper.getMenuName(menu_id);   
		
		// 넘어온 글번호로 수정할 게시글을 조회
		BoardDTO      board       =  boardPagingMapper.getBoardByIdx(idx);		
		
		ModelAndView   mv         =  new   ModelAndView();
		mv.addObject("menuList",  menuList );		
		mv.addObject("menuDTO",   menuDTO  );		
		mv.addObject("board",     board    );		
		mv.addObject("nowpage",   nowpage  );		
		mv.setViewName("boardpaging/update");
		return         mv;
	}
	
	// http://localhost:9090/BoardPaging/Update
	// POST (FORM data) : id, menu_id, title, content, nowpage 
	@RequestMapping("/BoardPaging/Update")
	public  ModelAndView   update(int nowpage, BoardDTO boardDTO ) {
		
		// 넘어온 정보로 idx 번의 Board data 를 수정한다
		boardPagingMapper.updateBoard( boardDTO  );
		
		String         menu_id =  boardDTO.getMenu_id(); 
		
		ModelAndView   mv      =  new ModelAndView();
		String         fmt     =  "redirect:/BoardPaging/List?menu_id={0}&nowpage={1}";
		String         loc     =  MessageFormat.format(fmt,
				menu_id, nowpage);
		mv.setViewName( loc );
		return         mv;		
	}
	
}













