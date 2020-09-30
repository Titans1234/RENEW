package com.lti.repo;

import java.util.List;

import com.lti.entity.Route;

public interface RouteRepo {

	void save(Route route);

	Route fetch(int routeid);

	List<Route> route();

}
