package com.graphhopper;

import java.util.Set;

import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.Weighting;
import com.graphhopper.util.EdgeIteratorState;

public class BlockingWeighting implements Weighting 
{
    private final FlagEncoder encoder;
    private final double maxSpeed;
    private Set<Integer> forbiddenEdges;

    public BlockingWeighting( FlagEncoder encoder, Set<Integer> fe)
    {
        this.encoder = encoder;
        this.maxSpeed = encoder.getMaxSpeed();
        if(fe != null && fe.size() > 0){
        	forbiddenEdges = fe;
        }
        
    }

    @Override
    public double getMinWeight( double distance )
    {
        return distance / maxSpeed;
    }

    @Override
    public double calcWeight(EdgeIteratorState edgeState, boolean reverse)
    {
        if(forbiddenEdges != null && forbiddenEdges.contains(edgeState.getEdge()))
            return Double.POSITIVE_INFINITY;

        double speed = reverse ? encoder.getReverseSpeed(edgeState.getFlags()) : encoder.getSpeed(edgeState.getFlags());
        if (speed == 0)
            return Double.POSITIVE_INFINITY;
        return edgeState.getDistance() / speed;
    }

    @Override
    public String toString()
    {
        return "BLOCKING";
    }
}
