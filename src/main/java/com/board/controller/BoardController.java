package com.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardDTO;
import com.board.mapper.BoardMapper;
import com.board.menus.domain.MenuDTO;
import com.board.menus.mapper.MenuMapper;

@Controller
public class BoardController {
	
	@Autowired
	private MenuMapper  menuMapper;
	
	@Autowired
	private BoardMapper boardMapper;

	// http://localhost:9090/Board/List?menu_id=MENU01
	@RequestMapping("/Board/List")
	public ModelAndView list( MenuDTO menuDTO ) {
		// 메뉴 리스트
		List<MenuDTO>  menuList  =  menuMapper.getMenuList();
		
		// 게시물 목록처리
		List<BoardDTO> boardList =  boardMapper.getBoardList( menuDTO ); 
		// System.out.println( boardList );
	
		// 넘어온 menuDTO안에 있는 menu_id 로 메누검색 getMenu()
		menuDTO                  =  menuMapper.getMenu( menuDTO );
		
		//-----------------------------------------
		
		ModelAndView  mv  = new ModelAndView();
		mv.addObject("menuList",  menuList);		
		//mv.addObject("menu_id",   menu_id );		
		mv.addObject("menuDTO",   menuDTO );		
		mv.addObject("boardList", boardList );
		mv.setViewName( "board/list" );
		return  mv;
	}
	
	// http://localhost:9090/Board/WriteForm?menu_id=MENU01
	@RequestMapping("/Board/WriteForm")
	public  ModelAndView  writeForm( MenuDTO  menuDTO  ) {
		
		// 메뉴 목록을 조회
		List<MenuDTO>  menuList = menuMapper.getMenuList();
		
		menuDTO                 = menuMapper.getMenu( menuDTO );
		
		ModelAndView  mv  =  new ModelAndView();
		mv.addObject("menuList", menuList );
		mv.addObject("menuDTO",  menuDTO);
		mv.setViewName( "board/write" );
		return        mv;
		
	}
	
	/*
	menu_id   :  MENU01
    title     :  css 에 대해 공부해보자
    writer    :  css
    content   :  scss
                 가 기본적용되는가?
	*/
	@RequestMapping("/Board/Write")
	public  ModelAndView  write( BoardDTO boardDTO ) {
		
		boardMapper.insertBoard( boardDTO  );
		
		String menu_id = boardDTO.getMenu_id();
		
		ModelAndView  mv   = new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return  mv;
		
	}
	
	// http://localhost:9090/Board/View?idx=16
	@RequestMapping("/Board/View")
	public  ModelAndView   view(  BoardDTO boardDTO   ) {
		
		//  메뉴 목록 조회
		List<MenuDTO>  menuList = menuMapper.getMenuList(); 
	
		// board 의 idx 에 해당하는 글번호 조회수 1 증가
		boardMapper.incHit( boardDTO );
		
		// view.jsp 에 출력할 내용 검색
		BoardDTO  board    =  boardMapper.getBoard( boardDTO  );

		// menu_id 로  메뉴 조회	
		String    menu_id  = board.getMenu_id();
		MenuDTO   menuDTO  = new MenuDTO(menu_id, null, 0);  
		menuDTO            = menuMapper.getMenu( menuDTO ); 
				
		ModelAndView   mv   =  new ModelAndView();
		mv.addObject("menuList", menuList );
		mv.addObject("menuDTO",  menuDTO);
		mv.addObject("board",    board    );
		mv.setViewName("board/view");
		return  mv;
		
	}
	
	// http://localhost:9090/Board/Delete?idx=16&menu_id=MENU01
	@RequestMapping("/Board/Delete")
	public   ModelAndView   delete( BoardDTO  boardDTO ) {
		
		// BoardDTO ( idx ) 로 삭제
		boardMapper.deleteBoard( boardDTO  );
				
		String  menu_id   =  boardDTO.getMenu_id(); 
		// 삭제 후 이동		
		ModelAndView  mv  =  new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return  mv;
		
	}
	
	// http://localhost:9090/Board/UpdateForm?idx=16&menu_id=MENU01
	@RequestMapping("/Board/UpdateForm")
	public  ModelAndView   updateForm(  BoardDTO boardDTO  ) {
		
		// 메뉴 목록 조회
		List<MenuDTO>  menuList  =  menuMapper.getMenuList();
		
		// 메뉴이름 조회
		String        menu_id    =  boardDTO.getMenu_id();
		MenuDTO       menuDTO    =  new  MenuDTO( menu_id, null, 0 );
 		menuDTO                  =  menuMapper.getMenu( menuDTO );
				
		// 수정할 게시글 정보를 idx 로 조회
		BoardDTO       board     =  boardMapper.getBoard( boardDTO );
			
		ModelAndView   mv  =  new ModelAndView();
		mv.addObject("menuList", menuList );
		mv.addObject("menuDTO",  menuDTO  );
		mv.addObject("board",    board    );
		mv.setViewName("board/update");
		
		return         mv;
		
	}
	
	// http://localhost:9090/Board/Update
	// 수정할 정보를 받아서 form tag post 방식으로
	@RequestMapping("/Board/Update")
	public  ModelAndView   update(  BoardDTO  boardDTO   ) {
		
		// 받은 정보롤 수정
		boardMapper.updateBoard( boardDTO  );	
		
		String  menu_id  = boardDTO.getMenu_id();
		
		// 돌아가기
		ModelAndView   mv   =   new ModelAndView();
		mv.setViewName("redirect:/Board/List?menu_id=" + menu_id);
		return         mv;
		
	}
	
	
	 
}










