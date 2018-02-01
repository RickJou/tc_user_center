package com.platform.user.mapper.userbasicinfo;

import java.util.Map;

@SuppressWarnings("rawtypes")
public interface UserBasicInfoMapper {

	void addUser(Map param);

	void delUser(Map param);

	Map findUser(Map param);

	Map findUserForMastre(Map param);

	Map findUserForSlave(Map param);

	Map findUserForPartition1(Map param);

	Map findUserForPartition2(Map param);

	Map findUserForPartition3(Map param);
	
}
