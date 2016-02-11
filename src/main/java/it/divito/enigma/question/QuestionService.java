package it.divito.enigma.question;
 
import it.divito.enigma.persistence.dao.QuestionDao;
import it.divito.enigma.persistence.dao.UserDao;
import it.divito.enigma.persistence.domain.User;
import it.divito.enigma.user.Utility;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
 
@Path("/")
public class QuestionService {
 
	final static Logger logger = Logger.getLogger(QuestionService.class);
	
	@GET
	@Path("/verify") 
	public Response verify(){
		return Response.status(200).entity("Everything's ok").build();
	}
	
	
	@GET
    @Path("/getQuestion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public QuestionServiceResponse getQuestion(QuestionServiceRequest questionRequest) {      
		
		logger.info("getQuestion, init");
		
		QuestionServiceResponse response = null;
		QuestionDao questionDao = QuestionDao.getInstance();
		
		logger.info("getQuestion, questionRequest: " + questionRequest);
		
		try {
			response = questionDao.selectRandomQuestion(questionRequest.getUserLevel());
		} catch(GenericJDBCException e) {
			logger.error("getQuestion, connection error:" + e.getMessage(), e);
		} catch(JDBCConnectionException e) {
			logger.error("getQuestion, connection timeout:" + e.getMessage(), e);
		}
		
		logger.info("getQuestion, response: " + response);
		return response;
    }
	
}