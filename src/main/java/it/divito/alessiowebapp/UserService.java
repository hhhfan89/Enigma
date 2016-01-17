package it.divito.alessiowebapp;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/")
public class UserService {
 
	@POST
	@Path("/isUserRegistered/{param}")
	public Response isUserRegistered(@PathParam("param") String userInfo) {
		String[] userInfoArray = userInfo.split("+");
		String imei = userInfoArray[0];
		String macAddress = userInfoArray[1];
		String deviceName = userInfoArray[2];
		
		// Make the query
		boolean isRegistered = false;
		return Response.status(200).entity(isRegistered).build();
	}
 
	@GET
	@Path("/verify") 
	public Response verify(){
		return Response.status(200).entity("Everything's ok").build();
	}
	
	@POST
    @Path("/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Resp save(UserInfo info) {      
		
		// CREARE OGGETTO CON IMEI (inizialmente)
		System.out.println("Imei:" + info.getImei());
		System.out.println("MacAddress:" + info.getMacAddress());
		
		/*
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        */
        Resp response = new Resp();
        response.setAlreadyRegistered(true);
        response.setLivesLeft(1);
        
        return response;
    }
	
	 public static void main(String[] args) {
			System.out.println(Boolean.parseBoolean("false"));
		}
}