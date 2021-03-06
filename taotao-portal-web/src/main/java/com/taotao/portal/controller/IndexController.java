package com.taotao.portal.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.common.utils.JsonUtils;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AD1Node;

/**
 * 首页展示
 * @author Administrator
 *
 */
@Controller
public class IndexController {
	
	@Value("${AD1_CATEGORY_ID}")
	private Long AD1_CATEGORY_ID;
	@Value("${AD1_WIDTH}")
	private Integer AD1_WIDTH;
	@Value("${AD1_WIDTH_B}")
	private Integer AD1_WIDTH_B;
	@Value("${AD1_HEIGHT}")
	private Integer AD1_HEIGHT;
	@Value("${AD1_HEIGHT_B}")
	private Integer AD1_HEIGHT_B;
	
	@Autowired
	private ContentService contentService;

	@RequestMapping("/index")
	public String showIndex(Model model) {
		//根据Cid 查询轮播图内容列表
		List<TbContent> contentList = contentService.getContentByCid(AD1_CATEGORY_ID);
		//把列表转换为AD1Node列表
		List<AD1Node> ad1Node = new ArrayList<>();
		for(TbContent content : contentList) {
			AD1Node node1 = new AD1Node();
			node1.setAlt(content.getTitle());
			node1.setHeight(AD1_HEIGHT);
			node1.setHeightB(AD1_HEIGHT_B);
			node1.setHref(content.getUrl());
			node1.setSrc(content.getPic());
			node1.setSrcB(content.getPic2());
			node1.setWidth(AD1_WIDTH);
			node1.setWidthB(AD1_WIDTH_B);
			
			ad1Node.add(node1);
		}
		//把列表转成json数据
		String ad1Jason = JsonUtils.objectToJson(ad1Node);
		
		//把json传递给页面
		model.addAttribute("ad1", ad1Jason);
		
		return "index";
	}
	
}
