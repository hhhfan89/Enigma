package it.divito.alessiowebapp;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Produces("application/json")
public class UserServiceResponse {
	
	private boolean isAlreadyRegistered;
	private int livesLeft;
	private Long idOnRemoteDB;
	
	public UserServiceResponse() {}
	
	public UserServiceResponse(boolean isAlreadyRegistered, int livesLeft, Long idOnRemoteDB) {
		super();
		this.isAlreadyRegistered = isAlreadyRegistered;
		this.livesLeft = livesLeft;
		this.idOnRemoteDB = idOnRemoteDB;
	}

	public boolean isAlreadyRegistered() {
		return isAlreadyRegistered;
	}

	public void setAlreadyRegistered(boolean isAlreadyRegistered) {
		this.isAlreadyRegistered = isAlreadyRegistered;
	}

	public int getLivesLeft() {
		return livesLeft;
	}

	public void setLivesLeft(int livesLeft) {
		this.livesLeft = livesLeft;
	}

	public Long getIdOnRemoteDB() {
		return idOnRemoteDB;
	}

	public void setIdOnRemoteDB(Long idOnRemoteDB) {
		this.idOnRemoteDB = idOnRemoteDB;
	}

	@Override
	public String toString() {
		return "UserServiceResponse [isAlreadyRegistered="
				+ isAlreadyRegistered + ", livesLeft=" + livesLeft
				+ ", idOnRemoteDB=" + idOnRemoteDB + "]";
	}

}