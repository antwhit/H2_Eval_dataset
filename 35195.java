/*
 * This file is part of the Panini project at Iowa State University.
 *
 * The contents of this file are subject to the Mozilla Public License
 * Version 1.1 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/.
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 * 
 * For more details and the latest version of this code please see
 * http://www.cs.iastate.edu/~design/projects/panini/
 *
 * Contributor(s):
 */


import java.util.Date;
import java.util.List;

public class CheckAirline{
	public CheckAirline(){
		register(this);
	}

	//Bind the checkFlights method to PlanTrip event type announcements.
	when PlanTrip do checkFlights;

	/**
	 * On a PlanTrip event announcement, get the Airline
	 * service provider and get the airline flights from
	 * each provider.
	 * @param rest
	 * @param td
	 */
	public void checkFlights(PlanTrip rest, TripData td){
		//System.out.println("Checking for available flights");
	    AirlineService as = ServiceProvider.getAirlineService();
		Date arriveDate = td.getArriveDate();
		Date departDate = td.getDepartDate();
		
	    List<FlightInfo> flights1 = as.getAirline1Flights(arriveDate, departDate);
	    List<FlightInfo> flights2 = as.getAirline2Flights(arriveDate, departDate);
	    List<FlightInfo> flights3 = as.getAirline3Flights(arriveDate, departDate);
	
	    td.addAvailableFlights(flights1);
	    td.addAvailableFlights(flights2);
	    td.addAvailableFlights(flights3);
        
	    //System.out.println("Done looking for flights");
	}
}
