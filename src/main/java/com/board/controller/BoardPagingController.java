package com.board.controller;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.board.domain.BoardPagingVo;
import com.board.domain.BoardVo;
import com.board.domain.Pagination;
import com.board.domain.PagingResponse;
import com.board.domain.SearchVo;
import com.board.mapper.BoardPagingMapper;
import com.board.menus.domain.MenuVo;
import com.board.menus.mapper.MenuMapper;

import ch.qos.logback.classic.Logger;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/BoardPaging")
public class BoardPagingController {
	
	@Autowired
	private   MenuMapper          menuMapper;
	
	@Autowired
	private   BoardPagingMapper   boardPagingMapper;
	
//  /BoardPaging/List?nowpage=1&menu_id=MENU01&title=&writer=
	@RequestMapping("/List")
	public   ModelAndView   list(int nowpage, BoardVo  boardPagingVo) {
		
		Logger log = (Logger) LoggerFactory.getLogger(BoardController.class);
		log.info("boardPagingVo : {}", boardPagingVo );
		
		// 메뉴 목록
		List<MenuVo>  menuList   =  menuMapper.getMenuList();
		
		
		// 게시물 목록  페이징
		// 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
		// count : 데이터 자료수를 알려줌 - 페이지번호 출력 위해
		// menu_id=MENU-1&title=&writer=  (title, writer는  검색 기능 필요)
        int count = boardPagingMapper.count( boardPagingVo );
        PagingResponse<BoardPagingVo> response = null;
        if (count < 1) {
        	response =  new PagingResponse<>(Collections.emptyList(), null); // null = vo 자료x
        } // 값이 없으면 가리키는 data가 없음 --> Stack안의 response 가리키는 heap의 list(array)와 nation이(null x)
          // array를 쓰려면 미리 new를 해서 emptyList 사용.
          // 
        // paging을 위한 초기 설정값
        SearchVo    searchVo  =  new SearchVo(); 
        searchVo.setPage(nowpage);
        searchVo.setPageSize(20); // 한 페이지당 20개 (기본 10개)
        
        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, searchVo);
        searchVo.setPagination(pagination);
        
        String      menu_id   =  boardPagingVo.getMenu_id();
        String      title     =  boardPagingVo.getTitle();
        String      writer    =  boardPagingVo.getWriter();        
        int         offset    =  searchVo.getOffset();
        int         pageSize  =  searchVo.getPageSize();

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<BoardPagingVo> list = boardPagingMapper.getBoardPagingList(
        		menu_id, title, writer, offset, pageSize);
        response =  new PagingResponse<>(list, pagination);
		
		System.out.println( response );
				
		ModelAndView  mv         =  new ModelAndView();
		mv.addObject("menuList",   menuList ); // pagingmenus.jsp
		mv.addObject("nowpage",    nowpage);   // pagingmenus.jsp, list.jsp
		
		mv.addObject("menu_id",    menu_id );  // list.jsp
		mv.addObject("response",   response ); // list.jsp
		mv.addObject("searchVo",   searchVo );
		mv.setViewName("boardpaging/list");
		return   mv;
		
	}
	
	//  /BoardPaging/WriteForm?menu_id=${menu_id}&nowpage=${nowpage}
	@RequestMapping("/WriteForm")
	public  ModelAndView   writeForm(String menu_id, int nowpage) {
		
		// 메뉴 목록 조회
		List<MenuVo> menuList = menuMapper.getMenuList();
		
		ModelAndView  mv  = new ModelAndView();
		mv.addObject("menu_id" , menu_id);
		mv.addObject("noepage", nowpage);
		mv.addObject("menuList", menuList);
		mv.setViewName("board/write");
		return mv;	
		
	}
	
//  /BoardPaging/Write
	@RequestMapping("/Write")
	public  ModelAndView write(int nowpage, BoardVo boardVo) {
		
		boardPagingMapper.insertBoard(boardVo); // 글쓰기를 위해 넘긴다
		
		ModelAndView  mv  = new ModelAndView();
		String fmt = "redirect:/BoardPaging/List/menu_id={0}&nowpage={1}";
		String loc = MessageFormat.format(fmt, boardVo.getMenu_id(), nowpage);  // format 상위호환
		mv.setViewName(loc);
		return mv;	
		
	}
	
	
}
