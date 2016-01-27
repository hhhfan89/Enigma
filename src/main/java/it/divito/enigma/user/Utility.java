package it.divito.enigma.user;

import it.divito.enigma.persistence.domain.User;

public class Utility {
	
	public static User mappingUser(UserServiceRequest userInfo) {
		User userDao = new User();
		userDao.setImei(userInfo.getImei());
		userDao.setMacAddress(userInfo.getMacAddress());
		userDao.setDeviceName(userInfo.getDeviceName());
		userDao.setId(userInfo.getIdOnRemoteDB());
		return userDao;
	}
}
