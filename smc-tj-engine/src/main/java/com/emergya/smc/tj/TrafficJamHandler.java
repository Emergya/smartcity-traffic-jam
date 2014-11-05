package com.emergya.smc.tj;

import java.util.List;

import javax.annotation.PostConstruct;

import org.geojson.LineString;
import org.geojson.LngLatAlt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.emergya.smc.model.Issue;
import com.emergya.smc.model.Stop;
import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.GraphHopper;
import com.graphhopper.TrafficJamGraph;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.FlagEncoder;
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

	private TrafficJamGraph hopper;

	public LineString getRouteTrafficJam(Stop stopFrom, Stop stopTo, List<Issue> issues) {
		LineString route = null;
		
		hopper.determineForbiddenEdges(issues);
		EncodingManager encoding = hopper.getEncodingManager();
		FlagEncoder flagEncoder = encoding.getSingle();
		hopper.createWeighting(this.WEIGHTING, flagEncoder);
		
		GHRequest request = new GHRequest(stopFrom.getLatitude(),
				stopFrom.getLongitude(), stopTo.getLatitude(),
				stopTo.getLongitude()).setWeighting(this.WEIGHTING).setVehicle(
				this.VEHICLE);
		
		GHResponse response = hopper.route(request);
		PointList points = response.getPoints();
    	route = this.getRoute(points);
		return route;
	}

	@PostConstruct
	public void init() {
		hopper = (TrafficJamGraph) new TrafficJamGraph().forServer();
		hopper.setInMemory(true);
		hopper.setOSMFile(this.OSM_FILE_PATH);
		hopper.setGraphHopperLocation(this.GRAPH_PATH);
		hopper.setEncodingManager(new EncodingManager(this.VEHICLE));
		hopper.setCHShortcuts(this.WEIGHTING);

		hopper.importOrLoad();
	}

	private LineString getRoute(PointList points) {
		LineString route = new LineString();
		for (int p = 0; p < points.getSize(); p++) {
			Double latitude = points.getLatitude(p);
			Double longitude = points.getLongitude(p);
			route.add(new LngLatAlt(longitude, latitude));
		}
		return route;
	}
}
