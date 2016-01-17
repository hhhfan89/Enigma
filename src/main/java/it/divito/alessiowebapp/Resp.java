package it.divito.alessiowebapp;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Produces("application/json")
public class Resp {
	
	private boolean isAlreadyRegistered;
	private int livesLeft;
	
	public Resp() {}
	
	public Resp(boolean isAlreadyRegistered, int livesLeft) {
		super();
		this.isAlreadyRegistered = isAlreadyRegistered;
		this.livesLeft = livesLeft;
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

}