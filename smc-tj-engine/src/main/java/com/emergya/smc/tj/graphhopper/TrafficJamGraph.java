package com.emergya.smc.tj.graphhopper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.emergya.smc.tj.model.Issue;
import com.graphhopper.GraphHopper;
import com.graphhopper.routing.util.EdgeFilter;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.Weighting;
import com.graphhopper.storage.index.LocationIndex;
import com.graphhopper.storage.index.QueryResult;
import com.graphhopper.util.EdgeIteratorState;


public class TrafficJamGraph extends GraphHopper {
	
	public TrafficJamGraph(){
		super();
	}
	
	Set<Integer> forbiddenEdges;
    public void determineForbiddenEdges(List<Issue> issues){
    	forbiddenEdges = new HashSet<>();
    	LocationIndex index = this.getLocationIndex();
    	for(Issue issue : issues){
    		QueryResult result = index.findClosest(issue.getLatitude(), issue.getLongitude(), EdgeFilter.ALL_EDGES);
    		EdgeIteratorState edge = result.getClosestEdge();
    		
    		forbiddenEdges.add(edge.getEdge());
    	}
    	//this.initCHPrepare();
    }

	@Override
	public Weighting createWeighting(String weighting, FlagEncoder encoder){
		if ("BLOCKING".equalsIgnoreCase(weighting))
            return new BlockingWeighting(encoder, forbiddenEdges);
        else
            return super.createWeighting(weighting, encoder);
	}

	public Set<Integer> getForbiddenEdges() {
		return forbiddenEdges;
	}

	public void setForbiddenEdges(Set<Integer> forbiddenEdges) {
		this.forbiddenEdges = forbiddenEdges;
	}
	
	
}
