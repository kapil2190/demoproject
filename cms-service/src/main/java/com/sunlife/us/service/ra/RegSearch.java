/*
* Used this interface to declare the AgcyDeleteEvent, StateListGetEvent, AgcyInsertEvent ,
* ContactDeleteEvent, ContactUpdateEvent, AgcyUpdateEvent,
* AgcyLocalListGetEvent, AgcyListGetEvent, CityListGetEvent
* */
package com.sunlife.us.service.ra;

import com.sunlife.us.entities.ComplaintInsuranceDepartment;
import com.sunlife.us.entities.InsuranceDepartmentContact;
import com.sunlife.us.entities.InsuranceDepartmentLocation;

public interface RegSearch {
    void AgcyDeleteEvent(InsuranceDepartmentLocation insuranceDepartmentLocation);

/*

    var event = new regSearchEvent(arr, regSearchEvent.RADELETE);
        return event;
*/


    void StateListGetEvent();

    /*var ar:Array =new

    Array();

    var event = new regSearchEvent(ar, regSearchEvent.GETSTLIST);
        return event;
*/

    void AgcyInsertEvent(ComplaintInsuranceDepartment complaintInsuranceDepartment);

/*

    var event = new regSearchEvent(arr, regSearchEvent.RAINSERT);
        return event;
*/


    void ContactDeleteEvent(InsuranceDepartmentContact insuranceDepartmentContact);

/*

    var event = new regSearchEvent(arr, regSearchEvent.RACDELETE);
        return event;
*/


    void ContactInsertEvent(InsuranceDepartmentContact insuranceDepartmentContact);

/*

    var event = new regSearchEvent(arr, regSearchEvent.RACINSERT);
        return event;
*/


    void ContactUpdateEvent(InsuranceDepartmentContact insuranceDepartmentContact);


    /*var event = new regSearchEvent(arr, regSearchEvent.RACUPDATE);
        return event;
*/

    void AgcyUpdateEvent(InsuranceDepartmentLocation insuranceDepartmentLocation);


    /*var event = new regSearchEvent(arr, regSearchEvent.RAUPDATE);
        return event;

*/
    void AgcyLocalListGetEvent(String stprCd, String insdNm, String insdcityNm);

    /*var arr:Array =new

    Array();

    arr[0]=name;
    arr[1]=city;
    arr[2]=st;
    arr[3]=true;
    arr[4]=stName;
    var event = new regSearchEvent(arr, regSearchEvent.RASRCHLOCALAGCY);
        return event;*/


    void AgcyListGetEvent(String citySelected, String stateSelected);

    /*var arr:Array =new

    Array();

    arr[0]=city;
    arr[1]=st;

    var event = new regSearchEvent(arr, regSearchEvent.RASRCHAGCY);
        return event;

*/
    void CityListGetEvent(String city);

    /*var arr:Array =new

    Array();

    arr[0]=st;

    var event = new regSearchEvent(arr, regSearchEvent.RASRCHCITY);
        return event;*/


}