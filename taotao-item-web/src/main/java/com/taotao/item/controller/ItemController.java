package com.taotao.item.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.item.pojo.Item;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping("/item/{itemId}")
	public String showItem(@PathVariable Long itemId, Model model) {
		//取商品详情
		TbItem tbItem = itemService.getItemById(itemId);
		Item item = new Item(tbItem);
		
		//取描述
		TbItemDesc tbTtemDesc = itemService.getItemDescById(itemId);
		
		//把数据传输给页面
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", tbTtemDesc);
		
		return "item";
	}
	
}
