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

/**
 * Application entry point.
 */
public class AppEntry{
    public static void main(String[] args){
        AppEntry ap = new AppEntry();
        /**
         * Create a series of trips to plan
         */
        TripData td1 = ap.doPlanTrip();
        td1.setCarPref(new CarPreference());
        td1.setPricePref(new PricePreference());
        
        TripData td2 = ap.doPlanTrip();
        td2.setCarPref(new CarPreference());
        td2.setPricePref(new PricePreference());
        
        TripData td3 = ap.doPlanTrip();
        td3.setCarPref(new CarPreference());
        td3.setPricePref(new PricePreference());
        
        
        System.out.println("Trip Data planned:\n" + td1.toString());
        System.out.println("Trip Data planned:\n" + td2.toString());
        System.out.println("Trip Data planned:\n" + td3.toString());
    }

    private CheckAirline checkAirline = null;
    private CheckHotel checkHotel = null;
    private CheckRentalCar checkRentalCar = null;



    public TripData doPlanTrip(){

        initHandlers();

        
        TripData td = new TripData();
        //announce a PlanTrip event.
        announce PlanTrip(td);

        return td;
    }

    /**
     * Lazily initialize all the handlers.
     * The handler classes register in their constructors.
     * Thus, instantiating these classes is enough to 
     * register them as handlers.
     */
    private void initHandlers(){
        if(checkAirline == null)
            checkAirline = new CheckAirline();
        if(checkHotel == null)
            checkHotel = new CheckHotel();
        if(checkRentalCar == null)
            checkRentalCar = new CheckRentalCar();
    }
}