package com.ljm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ljm.entity.SmbmsProvider;

public interface SmbmsProviderDao {
	/**
	 * ��ѯ���й�Ӧ��
	 * @param proCode ��Ӧ�̱���
	 * @param proName ��Ӧ������
	 * @param pageIndex ҳ��
	 * @param pagaSize ����
	 * @return �����������й�Ӧ��
	 */
	List<SmbmsProvider> getListSmbmsProvider(@Param("proCode") String proCode,@Param("proName") String proName,
			@Param("pageIndex") Integer pageIndex,@Param("pagaSize") Integer pagaSize);
	/**
	 * ��ѯ��Ӧ������
	 * @param proCode ��Ӧ�̱���
	 * @param proName ��Ӧ������
	 * @return ��Ӧ������
	 */
	int getSmbmsProviderRows(@Param("proCode") String proCode,@Param("proName") String proName);
	
}
