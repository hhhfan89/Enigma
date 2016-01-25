package it.divito.enigma.persistence.dao;

import it.divito.alessiowebapp.UserServiceResponse;
import it.divito.enigma.persistence.domain.User;
import it.divito.enigma.persistence.util.HibernateUtil;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;

public class UserDao {

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
		}
		catch(ConstraintViolationException e) {
			System.out.println("Errore:" + e.getMessage());
			response = selectUser(user);
		} 
		
        return response;
	}
	
	@SuppressWarnings("unchecked")
	public UserServiceResponse selectUser(User user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Criteria c = session.createCriteria(User.class);
		if(user.getId()!=null && user.getId()!=0) {
			c.add(Restrictions.eq("id", user.getId()));
		} else {
			c.add(Restrictions.eq("imei", user.getImei()));
			c.add(Restrictions.eq("deviceName", user.getDeviceName()));
			c.add(Restrictions.eq("macAddress", user.getMacAddress()));
		}
		
		UserServiceResponse resp = new UserServiceResponse();
		List<User> userList = c.list(); 
		
		if(userList!=null && userList.size()==1) {
			User selectedUser = (User) userList.get(0);
			resp.setAlreadyRegistered(true);
			resp.setLivesLeft(selectedUser.getLives());
			resp.setIdOnRemoteDB(selectedUser.getId());
		}

		session.close();

		return resp;
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
