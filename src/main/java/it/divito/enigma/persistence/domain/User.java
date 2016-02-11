package it.divito.enigma.persistence.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


@Entity
@Table(name = "users", catalog = "enigmawebappopenshift", uniqueConstraints=@UniqueConstraint(columnNames={"imei", "mac", "device_name"}))
public class User implements java.io.Serializable {
	
	private Long id;
	private String imei;
	private String macAddress;
	private String deviceName;
	private Integer lives;
	private Integer level;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "IMEI", nullable = false, length = 20)
	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}
	
	@Column(name = "MAC", nullable = false, length = 20)
	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress;
	} 

	@Column(name = "DEVICE_NAME", nullable = false, length = 100)
	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Column(name = "LIVES", nullable = false)
	public Integer getLives() {
		return lives;
	}

	public void setLives(Integer lives) {
		this.lives = lives;
	}

	@Column(name = "LEVEL", insertable = false)
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
}