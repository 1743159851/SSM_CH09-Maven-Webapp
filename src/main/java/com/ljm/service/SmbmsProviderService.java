package com.ljm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ljm.entity.SmbmsProvider;

public interface SmbmsProviderService {
	
	List<SmbmsProvider> getListSmbmsProvider(String proCode,String proName,Integer pageIndex,Integer pagaSize);
	
	int getSmbmsProviderRows(String proCode,String proName);
}
