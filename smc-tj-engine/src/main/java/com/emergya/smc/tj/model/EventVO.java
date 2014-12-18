package com.emergya.smc.tj.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class EventVO  implements Serializable{
	
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
	
	Long eventID;
	
	//code
    
    public String code;

    //createTimestamp
    public String createTimestamp;
    
    //startTimestamp
    public String startTimestamp;

    //endTimestamp
   public String endTimestamp;

    //source
    public String source;

    //description
    public String description;

    //isDescEdited
    public String isDescEdited;

    //updateTimestamp
    public String updateTimestamp;

    //location
    public String location;

    //eventType
    public String eventType;
   
	public Long getEventID() {
		return eventID;
	}

	public void setEventID(Long eventID) {
		this.eventID = eventID;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String eventKey) {
		this.code = eventKey;
	}

	public Date getCreateTimestamp() throws ParseException {
		if (createTimestamp!=null && !createTimestamp.isEmpty()) {
			return sdf.parse(createTimestamp);
		} else {
			return null;
		}
	}

	public void setCreateTimestamp(String createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getStartTimestamp() throws ParseException {
		if (startTimestamp!=null && !startTimestamp.isEmpty()) {
			return sdf.parse(startTimestamp);
		} else {
			return null;
		}
	}

	public void setStartTimestamp(String startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public Date getEndTimestamp() throws ParseException {
		if (endTimestamp!=null && !endTimestamp.isEmpty()) {
			return sdf.parse(endTimestamp);
		} else {
			return null;
		}
	}

	public void setEndTimestamp(String endTimestamp) {
		this.endTimestamp = endTimestamp;
	}
	
	public Date getUpdateTimestamp() throws ParseException {
		if (updateTimestamp!=null && !updateTimestamp.isEmpty()) {
			return sdf.parse(updateTimestamp);
		} else {
			return null;
		}
	}

	public void setUpdateTimestamp(String updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIsDescEdited() {
		return isDescEdited;
	}
	
	public Boolean getIsDescEditedBool() {
		return Boolean.valueOf(isDescEdited);
	}

	public void setIsDescEdited(String isDescEdited) {
		this.isDescEdited = isDescEdited;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	
//	Date startDate;
//	
//	@XmlElement(name = "startDateForm")
//	String startDateFormatted;
//
//	@XmlTransient
//	public Date getStartDate() {
//		return startDate;
//	}
//
//	public void setStartDate(Date startDate) {
//		this.startDate = startDate;
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
//		this.startDateFormatted = sdf.format(startDate);
//	}
//	
//	@XmlTransient
//	public String getStartDateFormatted() {
//		return startDateFormatted;
//	}


//	@XmlTransient
//	public String getAirTimeServiceFormatted() {
//		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
//		return sdf.format(airTimeService);
//	}

}
