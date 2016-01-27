package it.divito.enigma.question;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
 
@Path("/")
public class QuestionService {
 
	@GET
	@Path("/verify") 
	public Response verify(){
		return Response.status(200).entity("Everything's ok").build();
	}
	
}