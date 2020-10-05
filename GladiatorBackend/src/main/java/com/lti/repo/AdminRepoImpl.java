package com.lti.repo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional.TxType;
import javax.transaction.Transactional;


import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.lti.bridge.ShowFlightDetails;
import com.lti.entity.Admin;
import com.lti.entity.Flight;
import com.lti.entity.OperationalDays;
import com.lti.entity.User;
import com.lti.pojo.AdminLogin;

@Repository
public class AdminRepoImpl implements AdminRepo {

 @PersistenceContext
private EntityManager em;

 
 //================ADD ADMIN======================//
 @Transactional(value =TxType.REQUIRED)
 public void save(Admin admin) {
    em.persist(admin);
 }

 
 //======================REMOVE FLIGHT==============//
@Transactional
public boolean removeFlight(int flightId) {
Flight flight = new Flight();
try {
flight = em.find(Flight.class, flightId);
}
catch(NoResultException nre)
{
	
}
if(flight==null)
{
	System.out.println("Flight Not found ! \n");
	return false;
}
System.out.println(flight.toString());
flight.setFlightStatus("inactive");
try {
em.merge(flight);
} catch (Exception e) {
e.printStackTrace();
}
return true;

 }


//=====================SHOW FLIGHT================//
 public List<Flight> showFlight() {
   List<Flight> flight = new ArrayList<>();
   String sql = "select f from Flight f";
   Query query = em.createQuery(sql);
   System.out.println();
   flight = query.getResultList();

   if(flight==null)
	System.out.println("No record Found in Flight database");
   return flight;
}

 
//================ADMIN LOGIN===================//
@Override
public String login(AdminLogin ald) {
    String query = "select a.userName from Admin a where a.userName= :u1 and a.password= :p1";
    List<String> li = em.createQuery(query).setParameter("u1", ald.getUsername()).setParameter("p1", ald.getPassword()).getResultList();
    if (li.size() > 0) {
     //System.out.println(li.get(0));
     String a = li.get(0);
     System.out.println(a);
     return a;
    }
return null;
/*
* else { System.out.println("No data found"); } return null;
*/

 }



//=================ACTIVATE FLIGHT=============//
 @Override
public boolean activateFlight(int flightId) {
  Flight flight = new Flight();
  flight = em.find(Flight.class, flightId);
  System.out.println(flight.toString());
  flight.setFlightStatus("Flying");
  try {
    em.merge(flight);
   } 
  catch (Exception e) {
    e.printStackTrace();
   }
  return true;
}

 
//===============ADD FLIGHT========================//
 @Transactional
public boolean addAflight(Flight flight) {
		if(flight!= null) {
			em.persist(flight);
			return true;
		}
		return false ;
 }

 
//===============ADD OPERATIONAL DAYS==============// 
@Transactional
public boolean addOperationalDaysWithFlight(List<OperationalDays> operationalDays, int flightId) {
	if (operationalDays.size() > 0) {
		Flight flight = em.find(Flight.class, flightId);
		flight.setOperationalDays(operationalDays);
		for (OperationalDays op : operationalDays) {
			op.setFlight(flight);
		}
		em.merge(flight);
		return true;
	}
	return false;
}

//=============SHOW OPERATIONAL DAYS==============//
@Override
public List<OperationalDays> showOperationalDays(int flightId) {
	String sql="select op from OperationalDays op where flightId=:flightid";
	Query qry=em.createQuery(sql);
	List<OperationalDays> opdlist=new ArrayList<OperationalDays>();
	opdlist=qry.getResultList();
	return opdlist;
 }

}