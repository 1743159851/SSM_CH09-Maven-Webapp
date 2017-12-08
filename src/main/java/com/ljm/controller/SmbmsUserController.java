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
	 * 用户登录
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
		session.setAttribute("error", "登录名和密码不正确!");
		return "login";
	}
	/**
	 * 用户退出
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
	 * 分页查询用户列表
	 * @param queryname 用户姓名
	 * @param queryUserRole 用户角色
	 * @param pageIndex 当前页
	 * @param model 返回model
	 * @return 用户列表
	 */
	@RequestMapping("user.do")
	public String getUsers(String queryname,Integer queryUserRole,@RequestParam(defaultValue = "1")Integer pageIndex,Model model){
		//得到总行数
		int totalCount = smbmsUserService.getUserRows(queryname, queryUserRole);
		//计算
		int totalPageCount = totalCount%pagaSize==0?totalCount/pagaSize:totalCount/pagaSize+1;
		model.addAttribute("userList", smbmsUserService.getListSmbmsUser(queryname, queryUserRole, pageIndex, pagaSize));
		model.addAttribute("roleList", smbmsRoleService.getListSmbmsRoles());
		model.addAttribute("currentPageNo", pageIndex);//当前的页码
		model.addAttribute("totalPageCount", totalPageCount);//总页数
		model.addAttribute("totalCount", totalCount);//总行数
		model.addAttribute("queryUserName", queryname);
		model.addAttribute("queryUserRole", queryUserRole);
		return "jsp/userlist";
	}
}
