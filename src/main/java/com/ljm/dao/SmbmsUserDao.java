package com.ljm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ljm.entity.SmbmsUser;

public interface SmbmsUserDao {
	/**
	 * ��¼
	 * @param smbmsUser
	 * @return
	 */
	SmbmsUser login(SmbmsUser smbmsUser);
	/**
	 * ��ѯ�����û�
	 * @return
	 */
	List<SmbmsUser> getListSmbmsUser(@Param("userName") String userName,@Param("userRole") Integer userRole,
			@Param("pageIndex") Integer pageIndex,@Param("pagaSize") Integer pagaSize);
	/** 
	 * ��ѯ�û�������
	 * @param userName
	 * @param userRole
	 * @return
	 */
	int getUserRows(@Param("userName") String userName,@Param("userRole") Integer userRole);
 
}