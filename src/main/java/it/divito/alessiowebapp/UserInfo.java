package it.divito.alessiowebapp;

import java.io.Serializable;

import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Produces("application/json")
public class UserInfo implements Serializable{

	private String imei;
	private String macAddress;
	private String deviceName;
	private String idOnRemoteDB;
	
	public UserInfo() {}
	/*
	public UserInfo(String imei, String macAddress, String deviceName){
		this.imei = imei;
		this.macAddress = macAddress;
		this.deviceName = deviceName;
	}
	*/

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getIdOnRemoteDB() {
		return idOnRemoteDB;
	}

	public void setIdOnRemoteDB(String idOnRemoteDB) {
		this.idOnRemoteDB = idOnRemoteDB;
	}

	
}
