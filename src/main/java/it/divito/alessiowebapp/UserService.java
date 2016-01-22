package it.divito.alessiowebapp;
 
import it.divito.enigma.persistence.dao.UserDao;
import it.divito.enigma.persistence.domain.User;
import it.divito.enigma.persistence.util.HibernateUtil;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.HibernateException;
import org.hibernate.Session;
 
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
    @Path("/checkUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static Resp checkUser(UserInfo userInfo) {      
		
		// CREARE OGGETTO CON IMEI (inizialmente)
		System.out.println("Imei:" + userInfo.getImei());
		System.out.println("MacAddress:" + userInfo.getMacAddress());
		System.out.println("DeviceName:" + userInfo.getDeviceName());
		
		User user = Utility.mappingUser(userInfo);
		if(userInfo.getIdOnRemoteDB()!=null && Integer.parseInt(userInfo.getIdOnRemoteDB()) != 0) {
			return UserDao.selectUser(Integer.parseInt(userInfo.getIdOnRemoteDB()));
		}
		
		user.setLives(1);
		UserDao userDao1 = new UserDao();
		return userDao1.saveNewUser(user);
    }
	
}