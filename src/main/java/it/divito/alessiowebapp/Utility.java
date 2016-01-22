package it.divito.alessiowebapp;

import it.divito.enigma.persistence.domain.User;

public class Utility {
	
	public static User mappingUser(UserInfo userInfo) {
		User userDao = new User();
		userDao.setImei(userInfo.getImei());
		userDao.setMacAddress(userInfo.getMacAddress());
		userDao.setDeviceName(userInfo.getDeviceName());
		return userDao;
	}
}
