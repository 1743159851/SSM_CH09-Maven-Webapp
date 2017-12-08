package com.ljm.controller;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ljm.entity.SmbmsUser;
import com.ljm.service.SmbmsRoleService;
import com.ljm.service.SmbmsUserService;

@Controller
@RequestMapping("user")
public class SmbmsUserController {
	
	@Autowired
	private SmbmsUserService smbmsUserService;
	@Autowired
	private SmbmsRoleService smbmsRoleService;

	/**
	 * �û���¼
	 * @param smbmsUser
	 * @param session
	 * @return
	 */
	@RequestMapping("login.do")
	public String login(SmbmsUser smbmsUser,HttpSession session){
		SmbmsUser user = smbmsUserService.login(smbmsUser);
		if (user != null) {
			session.setAttribute("userSession", user);
			return "jsp/frame";
		}
		session.setAttribute("error", "��¼�������벻��ȷ!");
		return "login";
	}
	/**
	 * �û��˳�
	 * @param sessiong
	 * @return
	 */
	@RequestMapping("logout.do")
	public String logout(HttpSession sessiong){
		sessiong.removeAttribute("userSession");
		return "login";
	}
	
	private final int pagaSize = 5;
	/**
	 * ��ҳ��ѯ�û��б�
	 * @param queryname �û�����
	 * @param queryUserRole �û���ɫ
	 * @param pageIndex ��ǰҳ
	 * @param model ����model
	 * @return �û��б�
	 */
	@RequestMapping("user.do")
	public String getUsers(String queryname,Integer queryUserRole,@RequestParam(defaultValue = "1")Integer pageIndex,Model model){
		//�õ�������
		int totalCount = smbmsUserService.getUserRows(queryname, queryUserRole);
		//����
		int totalPageCount = totalCount%pagaSize==0?totalCount/pagaSize:totalCount/pagaSize+1;
		model.addAttribute("userList", smbmsUserService.getListSmbmsUser(queryname, queryUserRole, pageIndex, pagaSize));
		model.addAttribute("roleList", smbmsRoleService.getListSmbmsRoles());
		model.addAttribute("currentPageNo", pageIndex);//��ǰ��ҳ��
		model.addAttribute("totalPageCount", totalPageCount);//��ҳ��
		model.addAttribute("totalCount", totalCount);//������
		model.addAttribute("queryUserName", queryname);
		model.addAttribute("queryUserRole", queryUserRole);
		return "jsp/userlist";
	}
}
