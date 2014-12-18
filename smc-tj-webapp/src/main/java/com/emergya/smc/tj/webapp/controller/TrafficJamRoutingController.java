package com.emergya.smc.tj.webapp.controller;

import com.emergya.smc.tj.model.Issue;
import org.geojson.LineString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.emergya.smc.tj.TrafficJamHandler;
import com.emergya.smc.tj.webapp.dto.TrafficJamEventsRequest;
import com.emergya.smc.tj.webapp.dto.TrafficJamRoutingRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import com.emergya.smc.tj.model.EventVO;
import java.util.ArrayList;

/**
 *
 * @author marcos
 */
@RestController
@RequestMapping("/tj")
public class TrafficJamRoutingController {

    @Autowired
    TrafficJamHandler tjhandler;
    List<Issue> eventIssues = new ArrayList<Issue>();

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
    @RequestMapping(value = "trafficEvents", method = RequestMethod.POST)
    public void trafficEvents(@RequestBody TrafficJamEventsRequest request) throws ParseException {
        List<EventVO> events = request.getEvents();
        for (EventVO e : events) {
            Issue eventIssue = new Issue();
            eventIssue.setName(e.getDescription());
            eventIssue.setId(1);
            eventIssue.setLatitude(Double.parseDouble(e.getLocation().split(",")[0]));
            eventIssue.setLongitude(Double.parseDouble(e.getLocation().split(",")[1]));
            eventIssue.setEndDate(e.getEndTimestamp());
            eventIssues.add(eventIssue);

        }

    }

    @ResponseBody
    @RequestMapping(value = "trafficEvents", method = RequestMethod.GET)
    public List<Issue> trafficEvents() throws ParseException {
        return eventIssues;
    }

    @ResponseBody
    @RequestMapping(value = "routing", method = RequestMethod.POST)
    public LineString routing(@RequestBody TrafficJamRoutingRequest request) {
        LineString route = null;
        List<Issue> issuesInput = new ArrayList<Issue>();
        List<Issue> issuesOutput = new ArrayList<Issue>();
        issuesInput.addAll(eventIssues);
        issuesInput.addAll(request.getIssues());
        for (Issue i : issuesInput) {
            Date actualDate = new Date();
            Date endDate = i.getEndDate();
            if (endDate == null || actualDate.before(endDate)) {
                issuesOutput.add(i);
            }
        }
        route = tjhandler.getRouteTrafficJam(request.getOrigin(), request.getTarget(), issuesOutput);
        return route;
    }
}
