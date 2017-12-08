package com.ljm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ljm.entity.SmbmsUser;

public interface SmbmsUserDao {
	/**
	 * 登录
	 * @param smbmsUser
	 * @return
	 */
	SmbmsUser login(SmbmsUser smbmsUser);
	/**
	 * 查询所有用户
	 * @return
	 */
	List<SmbmsUser> getListSmbmsUser(@Param("userName") String userName,@Param("userRole") Integer userRole,
			@Param("pageIndex") Integer pageIndex,@Param("pagaSize") Integer pagaSize);
	/** 
	 * 查询用户的行数
	 * @param userName
	 * @param userRole
	 * @return
	 */
	int getUserRows(@Param("userName") String userName,@Param("userRole") Integer userRole);
 
}