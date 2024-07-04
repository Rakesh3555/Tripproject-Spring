package com.project.tripmanager.dao;

import com.project.tripmanager.model.Trip;

public class Methods {  
	
	public void tripAwountWithFlightCalculation(int adult, int children,int differenceInDays,Trip Trip) {
		
		int totalPeopleAmount = 10000 *  adult + 6000 * children;
		int totalAmount =totalPeopleAmount * differenceInDays;
		
		Trip.setTotalPrice(totalAmount);
		
	}

}
