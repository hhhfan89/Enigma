package it.divito.enigma.user;
 
import it.divito.enigma.persistence.dao.UserDao;
import it.divito.enigma.persistence.domain.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;

 
@Path("/")
public class UserService {
	
	final static Logger logger = Logger.getLogger(UserService.class);
 
	@GET
	@Path("/verify") 
	public Response verify(){
		logger.info("Verify, init");
		boolean isConnected = UserDao.getInstance().checkConnection();
		logger.info("Verify, connection with db established: " + isConnected);
		return Response.status(200).entity("Connection with db established: " + isConnected).build();
	}
	
	@POST
    @Path("/saveUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserServiceResponse saveUser(UserServiceRequest userInfo) {      

		logger.info("saveUser, init");
		UserServiceResponse response = null;
		UserDao userDao = UserDao.getInstance();

		logger.info("saveUser, userServiceRequest: " + userInfo);

		User user = Utility.mappingUser(userInfo);
		
		try {
			response = userDao.saveNewUser(user);
		} catch(GenericJDBCException e) {
			logger.error("saveUser, connection error:" + e.getMessage(), e);
		} catch(JDBCConnectionException e) {
			logger.error("saveUser, connection timeout:" + e.getMessage(), e);
		}

		logger.info("saveUser, response: " + response);
		return response;
	}
	
	
	@POST
    @Path("/checkUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public UserServiceResponse checkUser(UserServiceRequest userInfo) {      
		
		logger.info("checkUser, init");
		
		UserServiceResponse response = null;
		UserDao userDao = UserDao.getInstance();
		
		logger.info("checkUser, userServiceRequest: " + userInfo);
		
		User user = Utility.mappingUser(userInfo);
		// TODO: pensa al caso di manomissione dell'id..
		try {
			response = userDao.selectUser(user);
		} catch(GenericJDBCException e) {
			logger.error("checkUser, connection error:" + e.getMessage(), e);
		} catch(JDBCConnectionException e) {
			logger.error("checkUser, connection timeout:" + e.getMessage(), e);
		}
		
		logger.info("checkUser, response: " + response);
		return response;
    }
	
}