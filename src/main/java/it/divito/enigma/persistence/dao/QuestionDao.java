package it.divito.enigma.persistence.dao;

import it.divito.enigma.persistence.domain.Question;
import it.divito.enigma.persistence.domain.User;
import it.divito.enigma.persistence.util.HibernateUtil;
import it.divito.enigma.question.QuestionServiceResponse;
import it.divito.enigma.user.UserServiceResponse;

import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class QuestionDao {

	final static Logger logger = Logger.getLogger(QuestionDao.class);
	 
	private static QuestionDao questionDao;
	
	public static QuestionDao getInstance(){
		logger.debug("Getting QuestionDao instance");
		if(questionDao==null){
			questionDao = new QuestionDao();
		}
		return questionDao;
	}
	

	
	@SuppressWarnings("unchecked")
	public QuestionServiceResponse selectRandomQuestion(int level) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Criteria c = session.createCriteria(Question.class);
		c.add(Restrictions.eq("level", level));
		
		QuestionServiceResponse resp = new QuestionServiceResponse();
		List<Question> questionList = c.list(); 
		
		logger.info("selectRandomQuestion, questionList size: " + questionList.size());
		if(questionList!=null && questionList.size()>0) {
			Random r = new Random(System.currentTimeMillis());
			Question selectedQuestion = (Question) questionList.get(r.nextInt(questionList.size()-1));
			resp.setIdOnRemoteDB(selectedQuestion.getId());
			resp.setLevel(selectedQuestion.getLevel());
			resp.setQuestion(selectedQuestion.getQuestion());
			resp.setAnswerTime(selectedQuestion.getAnswerTime());
			logger.info("selectRandomQuestion, find a question with id " + selectedQuestion.getId());
		}

		session.close();

		return resp;
	}
	
	public static void main(String[] args) {
		Random r = new Random(System.currentTimeMillis());
		System.out.println(r.nextInt(5));
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
