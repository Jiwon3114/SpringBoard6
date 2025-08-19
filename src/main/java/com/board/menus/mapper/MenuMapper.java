package com.board.menus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.board.menus.domain.MenuDTO;

@Mapper
public interface MenuMapper {

	void insertMenu(MenuDTO menuDTO);

	List<MenuDTO> getMenuList();

	void deleteMenu(MenuDTO menuDTO);

	MenuDTO getMenu(MenuDTO menuDTO);

	void update(MenuDTO menuDTO);

	void insertMenu2(MenuDTO menuDTO);

	MenuDTO getMenuName(String menu_id);

}
