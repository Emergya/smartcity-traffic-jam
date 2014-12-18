package com.emergya.smc.tj.webapp.dto;

import java.io.Serializable;
import java.util.List;


import com.emergya.smc.tj.model.EventVO;

/**
*
* @author amartinez
*/
public class TrafficJamEventsRequest implements Serializable {

        private List<EventVO> events;
	
        public List<EventVO> getEvents() {
		return events;
	}
	public void setEvents(List<EventVO> events) {
		this.events = events;
	}
	
	
}
