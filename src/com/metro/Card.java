package com.metro;

import com.metro.charging.ChargingStrategy;


public class Card 
{
	private float initBalance=0.0f;
	private float totalFair=0;
	private boolean isAllowedToTravel=false;
	private boolean isWeekend=false;
	private String cardNumber;
	private Station startStation;
	private float recentFair;
	private Station endStation;
	private ChargingStrategy chargingStrategey;

	/*To issue a new card*/
	public Card(float _initBalance,ChargingStrategy  _chargingStrategey ) 
	{
		this.chargingStrategey=_chargingStrategey;
		this.initBalance=_initBalance;
		this.cardNumber=MetroUtility.generateCardNumber();
				System.out.println("MSCS::  A new card is issued with initail balance "+this.initBalance +" and card number "+cardNumber);

	}
	public boolean isAllowedToTraved()
	{
		if(initBalance<5.5)
		{
			System.out.println("MSCS::  Use is not allowed to enter due to low balance");
			return false;
		}

		else
		{
			isAllowedToTravel=true;
			return true;
		}



	}
	public boolean swipeIn(Station _startPoint)
	{
		if(isAllowedToTravel)
		{
			this.startStation=_startPoint;		
			totalFair=0;
			this.isWeekend=MetroUtility.isWeekend();
			return true;

		}
		else
		{
			System.out.println("MSCS::  You are not allowed to go,Minimum balance should be 5.5 roubles :)");
			return false;

		}



	}
	public void recharge(Float amount)
	{
		this.initBalance+=amount;
		System.out.println("MSCS::  Card [ "+this.cardNumber+ " ] Succesfully recharge with [ "+amount+ "] ");
		System.out.println("MSCS::  Your total balance is now "+initBalance +"Roubles ");

	}
	public boolean swipeOut(Station _endPoint)
	{

		this.endStation=_endPoint;
		int distance=_endPoint.getId()-startStation.getId();
		
		float totalTravelCharge=chargingStrategey.deductBalance(distance);
 

		if(this.initBalance < totalTravelCharge)
		{

			System.out.println("MSCS::  You are not allowed to exit due to ,you have low balance !!\n" +
					"Minimum balance required is "+(totalTravelCharge-initBalance)+" Plesae recharge from counter !!");
			return false;	
		}
		else
		{
			this.recentFair=totalTravelCharge;
			this.initBalance-=totalTravelCharge;

			this.isAllowedToTravel=false;
			System.out.println("MSCS::  Thank you for using our metro ,your current balance is"+initBalance );
			return true;

		}



	}
	public float getBalance()
	{
		
		return this.initBalance;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public Station getStartStation() {
		return startStation;
	}
	public Station getEndStation() {
		return endStation;
	}

	public float getRecentFair() {
		return recentFair;
	}
	
}
