package it.divito.enigma.persistence.dao;

import it.divito.alessiowebapp.Resp;
import it.divito.enigma.persistence.domain.User;
import it.divito.enigma.persistence.util.HibernateUtil;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDao {

	public Resp saveNewUser(User userDao) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		boolean alreadyRegistered = false;
		
		try {
			session.beginTransaction();
			session.save(userDao);
			session.getTransaction().commit();
			session.close();
			alreadyRegistered = false;
		}
		// TODO: distinzione eccezione (db down, unique violato, password sbagliata..)
		catch(HibernateException e) {
			System.out.println("Errore:" + e.getMessage());
			alreadyRegistered = true;
		}

        Resp response = new Resp();
        response.setAlreadyRegistered(alreadyRegistered);
        //response.setLivesLeft(1);
        // TODO: questo serve per le query successive da Android, è più veloce --> 
        // 						introdurre nuovo campo su SQLite
        response.setIdOnRemoteDB(userDao.getId());		
        return response;
	}
	
	public static Resp selectUser(int idOnRemoteDB) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Criteria c = session.createCriteria(User.class);
		c.add(Restrictions.eq("id", idOnRemoteDB));
		/*
		c.add(Restrictions.eq("imei", user.getImei()));
		c.add(Restrictions.eq("deviceName", user.getDeviceName()));
		c.add(Restrictions.eq("macAddress", user.getMacAddress()));
		*/
		
		Resp resp = new Resp();
		List userList = c.list(); 
		
		if(c.list()!=null && c.list().size()==1) {
			User selectedUser = (User) c.list().get(0);
			resp.setAlreadyRegistered(true);
			resp.setLivesLeft(selectedUser.getLives());
		} else {
			resp.setAlreadyRegistered(false);
		}

		session.close();

		return resp;
	}
	
}
