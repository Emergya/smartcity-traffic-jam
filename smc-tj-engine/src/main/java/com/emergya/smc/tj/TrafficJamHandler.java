package com.emergya.smc.tj;

import java.util.List;

import javax.annotation.PostConstruct;

import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emergya.smc.model.Issue;
import com.emergya.smc.model.Stop;
import com.graphhopper.GraphHopper;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.util.PointList;

/**
*
* @author marcos
*/
@Component
public class TrafficJamHandler {

	@Value("${smc.dm.osmFilePath}")
	private String OSM_FILE_PATH;
	@Value("${smc.dm.graphPath}")
	private String GRAPH_PATH;
	@Value("${smc.dm.vehicle}")
	private String VEHICLE;
	@Value("${smc.dm.weighting}")
	private String WEIGHTING;
	
	private GraphHopper hopper;
	
	public LineString getRouteTrafficJam(Stop stopFrom, Stop stopTo, List<Issue> issues){
		LineString route = null;
		
		return route;
	}
	
	@PostConstruct
	public void init(){
		hopper = new GraphHopper().forServer();
		hopper.setInMemory(true);
		hopper.setOSMFile(this.OSM_FILE_PATH);
		hopper.setGraphHopperLocation(this.GRAPH_PATH);
		hopper.setEncodingManager(new EncodingManager(this.VEHICLE));
		
		hopper.importOrLoad();
	}
	
	private LineString getRoute(PointList points){
		LineString route = new LineString();
		for(int p=0; p<points.getSize(); p++){
			Double latitude = points.getLatitude(p);
			Double longitude = points.getLongitude(p);
			route.add(new LngLatAlt(longitude, latitude));
		}
		return route;
	}
}
