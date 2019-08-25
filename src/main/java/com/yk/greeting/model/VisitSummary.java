package com.yk.greeting.model;

import java.util.List;
import java.util.Set;

import com.yk.greeting.projection.VisitorProjection;

/**
 * @author Vykuntarao
 *
 */
public class VisitSummary {
	private long totalVisits;
	private Set<String> visitorFirstNames;
	private Set<String> visitorLastNames;
	private List<VisitorProjection> visitors;
	/**
	 * 
	 * @return
	 */
	public long getTotalVisits() {
		return totalVisits;
	}
	/**
	 * 
	 * @param totalVisits
	 */
	public void setTotalVisits(long totalVisits) {
		this.totalVisits = totalVisits;
	}
	/**
	 * 
	 * @return
	 */
	public Set<String> getVisitorFirstNames() {
		return visitorFirstNames;
	}
	/**
	 * 
	 * @param visitorFirstNames
	 */
	public void setVisitorFirstNames(Set<String> visitorFirstNames) {
		this.visitorFirstNames = visitorFirstNames;
	}
	/**
	 * 
	 * @return
	 */
	public Set<String> getVisitorLastNames() {
		return visitorLastNames;
	}
	/**
	 * 
	 * @param visitorLastNames
	 */
	public void setVisitorLastNames(Set<String> visitorLastNames) {
		this.visitorLastNames = visitorLastNames;
	}

	/**
	 * @return the visitors
	 */
	public List<VisitorProjection> getVisitors() {
		return visitors;
	}

	/**
	 * @param visitors the visitors to set
	 */
	public void setVisitors(List<VisitorProjection> visitors) {
		this.visitors = visitors;
	}
	
	
}
