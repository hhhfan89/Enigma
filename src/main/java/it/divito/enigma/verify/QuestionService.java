package it.divito.enigma.verify;
 
import it.divito.enigma.persistence.dao.UserDao;
import it.divito.enigma.persistence.domain.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.JDBCConnectionException;
 
@Path("/verify")
public class QuestionService {
 
	@GET
	@Path("/verify1") 
	public Response verify(){
		return Response.status(200).entity("Everything's ok").build();
	}
	
}