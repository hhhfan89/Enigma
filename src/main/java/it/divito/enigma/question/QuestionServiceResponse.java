package it.divito.enigma.question;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Produces("application/json")
public class QuestionServiceResponse {
	
	private Long idOnRemoteDB;
	private String question;
	private Long answerTime;
	private int level;
	
	public QuestionServiceResponse() {}

	public Long getIdOnRemoteDB() {
		return idOnRemoteDB;
	}

	public void setIdOnRemoteDB(Long idOnRemoteDB) {
		this.idOnRemoteDB = idOnRemoteDB;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Long getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(Long answerTime) {
		this.answerTime = answerTime;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	

}