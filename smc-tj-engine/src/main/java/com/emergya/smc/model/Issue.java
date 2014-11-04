package com.emergya.smc.model;

import java.io.Serializable;

/**
*
* @author marcos
*/
public class Issue implements Serializable {

	private String name;
	private Integer id;
    private double latitude;
    private double longitude;
    
    public Issue(){
    	
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
    
    
}
