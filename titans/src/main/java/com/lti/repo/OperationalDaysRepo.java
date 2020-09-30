package com.lti.repo;

import java.util.List;

import com.lti.entity.OperationalDays;

public interface OperationalDaysRepo {

	void save(OperationalDays operationaldays);

	OperationalDays fetch(int operationalId);

	List<OperationalDays> fetchAll();
}
