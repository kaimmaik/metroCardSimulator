package com.metro.charging;
import com.metro.MetroUtility;

public class WeekendWeekdayStrategy implements ChargingStrategy {

	@Override
	public float deductBalance(int distance) 
	{
		float totalTravelCharge=0.0f;
		if(MetroUtility.isWeekend())
		{
			 totalTravelCharge=(float)(distance*5.5) ;
		}
		else
		{
			 totalTravelCharge=(float)(distance*7 );
		}
		return totalTravelCharge;
	}
}
