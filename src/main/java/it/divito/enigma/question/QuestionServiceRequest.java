package it.divito.enigma.question;

import java.io.Serializable;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Produces("application/json")
public class QuestionServiceRequest implements Serializable{

	private static final long serialVersionUID = -6676278322477676147L;

	private int userLevel;
	
	public QuestionServiceRequest() {}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}
	
	
	
}
