package it.divito.enigma.persistence.dao;

import it.divito.enigma.persistence.domain.User;
import it.divito.enigma.persistence.util.HibernateUtil;
import it.divito.enigma.user.UserServiceResponse;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

public class UserDao {

	final static Logger logger = Logger.getLogger(UserDao.class);
	 
	private static UserDao userDao;
	
	public static UserDao getInstance(){
		logger.debug("Getting UserDao instance");
		if(userDao==null){
			userDao = new UserDao();
		}
		return userDao;
	}
	
	
	public boolean checkConnection() {
		boolean isConnected;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			isConnected = session.isConnected();
			session.close();
		} catch (Throwable ex) {
			logger.error("checkConnection, exception", ex);
			isConnected = false;
		}
		return isConnected;
	}
	
	
	public UserServiceResponse saveNewUser(User user) {
		
		UserServiceResponse response = null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			// E' la prima volta, quindi ha solo una vita
			user.setLives(1);
			
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.close();
			
			response = new UserServiceResponse();
			response.setAlreadyRegistered(false);
			response.setIdOnRemoteDB(user.getId());
			response.setLevel(user.getLevel());
		}
		catch(ConstraintViolationException e) {
			logger.info("saveNewUser, ConstraintViolationException");
			response = selectUser(user);
		} 
		
        return response;
	}
	
	@SuppressWarnings("unchecked")
	public UserServiceResponse selectUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Criteria c = session.createCriteria(User.class);
		if(user.getId()!=null && user.getId()!=0) {
			logger.info("selectUser, added id restrinction");
			c.add(Restrictions.eq("id", user.getId()));
		} else {
			logger.info("selectUser, added imei, deviceName and macAddress restrinctions");
			c.add(Restrictions.eq("imei", user.getImei()));
			c.add(Restrictions.eq("deviceName", user.getDeviceName()));
			c.add(Restrictions.eq("macAddress", user.getMacAddress()));
		}
		
		UserServiceResponse resp = new UserServiceResponse();
		List<User> userList = c.list(); 
		
		logger.info("selectUser, userList size: " + userList.size());
		if(userList!=null && userList.size()==1) {
			User selectedUser = (User) userList.get(0);
			resp.setAlreadyRegistered(true);
			resp.setIdOnRemoteDB(selectedUser.getId());
			resp.setLivesLeft(selectedUser.getLives());
			resp.setLevel(selectedUser.getLevel());
			logger.info("selectUser, find an user, with id " + selectedUser.getId());
		}

		session.close();

		return resp;
	}
	
	
	public boolean select() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria c = session.createCriteria(User.class);
		logger.info("select, number of users: " + c.list().size());
		return true;
	}
	
	/*
	@SuppressWarnings("unchecked")
	public Resp selectUser(long idOnRemoteDB) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("id", idOnRemoteDB));

		Resp resp = new Resp();
		List<User> userList = c.list(); 
		
		if(userList!=null && userList.size()==1) {
			User selectedUser = (User) userList.get(0);
			resp.setAlreadyRegistered(true);
			resp.setLivesLeft(selectedUser.getLives());
		} else {
			resp.setAlreadyRegistered(false);
		}

		session.close();

		return resp;
	}
	*/
}
