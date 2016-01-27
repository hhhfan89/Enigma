package it.divito.enigma;
 
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
 
@Path("/")
public class UserService {
 
	@GET
	@Path("/verify") 
	public Response verify(){
		return Response.status(200).entity("Everything's ok").build();
	}
	
	@POST
    @Path("/saveUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static UserServiceResponse saveUser(UserServiceRequest userInfo) {      
		
		UserServiceResponse response = null;
		UserDao userDao = new UserDao();
		
		// CREARE OGGETTO CON IMEI (inizialmente)
		System.out.println("Imei:" + userInfo.getImei());
		System.out.println("MacAddress:" + userInfo.getMacAddress());
		System.out.println("DeviceName:" + userInfo.getDeviceName());
		System.out.println("idOnRemoteDB:" + userInfo.getIdOnRemoteDB());
		
		User user = Utility.mappingUser(userInfo);
		try {
			response = userDao.saveNewUser(user);
		} catch(GenericJDBCException e) {
			System.out.println("Errore connessione:" + e.getMessage());
		} catch(JDBCConnectionException e) {
			System.out.println("Connection timeout:" + e.getMessage());
		}
		return response;
    }
	
	
	@POST
    @Path("/checkUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public static UserServiceResponse checkUser(UserServiceRequest userInfo) {      
		
		UserServiceResponse response = null;
		UserDao userDao = new UserDao();
		
		// CREARE OGGETTO CON IMEI (inizialmente)
		System.out.println("Imei:" + userInfo.getImei());
		System.out.println("MacAddress:" + userInfo.getMacAddress());
		System.out.println("DeviceName:" + userInfo.getDeviceName());
		System.out.println("idOnRemoteDB:" + userInfo.getIdOnRemoteDB());
		
		User user = Utility.mappingUser(userInfo);
		// TODO: pensa al caso di manomissione dell'id..
		try {
			response = userDao.selectUser(user);
		}
		catch(GenericJDBCException e) {
			System.out.println("Errore connessione:" + e.getMessage());
		}
		catch(JDBCConnectionException e) {
			System.out.println("Connection timeout:" + e.getMessage());
		}
		return response;
    }
	
	
	public static void main(String[] args) {
		UserServiceRequest info = new UserServiceRequest();
		info.setDeviceName("dev");
		info.setImei("im");
		info.setMacAddress("maccc");
		//info.setIdOnRemoteDB(24);
		UserServiceResponse r = checkUser(info);
		System.out.println("Response:" + r.toString());
	}
	
}