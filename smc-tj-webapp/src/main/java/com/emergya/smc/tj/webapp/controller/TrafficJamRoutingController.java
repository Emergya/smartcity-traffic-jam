package com.emergya.smc.tj.webapp.controller;

import org.geojson.LineString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.emergya.smc.tj.TrafficJamHandler;
import com.emergya.smc.tj.webapp.dto.TrafficJamRoutingRequest;

/**
*
* @author marcos
*/
@RestController
@RequestMapping("/tj")
public class TrafficJamRoutingController {

	@Autowired
	TrafficJamHandler tjhandler;
	
	@ResponseBody    
    @RequestMapping(method = RequestMethod.GET)    
    public String test() {
        return "Try using POST on /routing path";
    }
	
	@ResponseBody
    @RequestMapping(value = "routing", method = RequestMethod.GET)
    public ModelAndView routing() {
		ModelAndView model = new ModelAndView("error");
		model.addObject("text", "Try using POST on /routing path");
		return model;
    }
	
	@ResponseBody
    @RequestMapping(value = "routing", method = RequestMethod.POST)
    public LineString routing(@RequestBody TrafficJamRoutingRequest request) {
		LineString route = null;
		route = tjhandler.getRouteTrafficJam(request.getOrigin(), request.getTarget(), request.getIssues());
        return route;
    }
}
