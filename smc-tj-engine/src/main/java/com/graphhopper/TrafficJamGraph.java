package com.graphhopper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.emergya.smc.model.Issue;
import com.graphhopper.routing.util.EdgeFilter;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.Weighting;
import com.graphhopper.storage.index.QueryResult;
import com.graphhopper.util.EdgeIteratorState;


public class TrafficJamGraph extends GraphHopper {
	
	public TrafficJamGraph(){
		super();
	}
	
	Set<Integer> forbiddenEdges;
    public void determineForbiddenEdges(List<Issue> issues){
    	forbiddenEdges = new HashSet<>();
    	for(Issue issue : issues){
    		QueryResult result = this.getLocationIndex().findClosest(issue.getLatitude(), issue.getLongitude(), EdgeFilter.ALL_EDGES);
    		EdgeIteratorState edge = result.getClosestEdge();
    		forbiddenEdges.add(edge.getEdge());
    	}
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
