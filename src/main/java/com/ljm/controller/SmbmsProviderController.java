package com.ljm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ljm.service.SmbmsProviderService;

@Controller
@RequestMapping("provider")
public class SmbmsProviderController {
	@Autowired
	private SmbmsProviderService smbmsProviderService;
	
	private final int pagaSize = 5;
	
	@RequestMapping("provider.do")
	public String getProviders(String queryProCode,String queryProName,@RequestParam(defaultValue = "1")Integer pageIndex,Model model){
		//得到总行数
		int totalCount = smbmsProviderService.getSmbmsProviderRows(queryProCode, queryProName);
		//计算总页数
		int totalPageCount = totalCount % pagaSize==0?totalCount / pagaSize:totalCount / pagaSize +1;
		model.addAttribute("providerList", smbmsProviderService.getListSmbmsProvider(queryProCode, queryProName, pageIndex, pagaSize));
		model.addAttribute("totalCount", totalCount);//总行数
		model.addAttribute("currentPageNo", pageIndex);//当前页数
		model.addAttribute("totalPageCount", totalPageCount);//总页数
		return "jsp/providerlist";
	}
}
