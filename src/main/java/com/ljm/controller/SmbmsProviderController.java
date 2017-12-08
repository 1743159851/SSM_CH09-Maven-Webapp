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
		//�õ�������
		int totalCount = smbmsProviderService.getSmbmsProviderRows(queryProCode, queryProName);
		//������ҳ��
		int totalPageCount = totalCount % pagaSize==0?totalCount / pagaSize:totalCount / pagaSize +1;
		model.addAttribute("providerList", smbmsProviderService.getListSmbmsProvider(queryProCode, queryProName, pageIndex, pagaSize));
		model.addAttribute("totalCount", totalCount);//������
		model.addAttribute("currentPageNo", pageIndex);//��ǰҳ��
		model.addAttribute("totalPageCount", totalPageCount);//��ҳ��
		return "jsp/providerlist";
	}
}
