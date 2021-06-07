package com.metro;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class   SapientMetroSimulater
{
	private ConcurrentHashMap<String, Card> mapOfSwipedInCard=new ConcurrentHashMap<String, Card>();
	private ConcurrentHashMap<String, ArrayList<String>> logsForApi = new ConcurrentHashMap<String, ArrayList<String>>();
	private ConcurrentHashMap<String, Integer> swipeIn=new ConcurrentHashMap<String, Integer>();
	private ConcurrentHashMap<String, Integer> swipeout=new ConcurrentHashMap<String, Integer>();

	public boolean swipeIn(Card userCard,int stationid)
	{
		if(userCard!=null && MetroUtility.isValidStation(stationid))
		{
			if(userCard.isAllowedToTraved())	
			{
				userCard.swipeIn(new Station(stationid));
				mapOfSwipedInCard.put(userCard.getCardNumber(),userCard);
				System.out.println("MSCS:: Card accepted with card number "+userCard);
				//logging swip in
				String stationName=userCard.getStartStation().getStationName();
				if(swipeIn.containsKey(stationName))
				{
					int totalswipeIns=swipeIn.get(stationName);
					totalswipeIns++;
					swipeIn.put(stationName,totalswipeIns);
				}
				else
				{
					swipeIn.put(stationName,1);
				}
				//logging swap in
				
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			System.out.println("MSCS:: Invalid sation selection :p");
			return false;
		}

	}
	public boolean swipeOut(Card userCard,int stationid)
	{
		if(userCard!=null && MetroUtility.isValidStation(stationid))
		{
			if(userCard.swipeOut(new Station(stationid)))	
			{
				System.out.println("MSCS:: Card accepted for exit with card number "+userCard.getCardNumber());
				
				//logging
				ArrayList<String> ar=null;
				if(logsForApi.containsKey(userCard.getCardNumber()))
				{
					ar=logsForApi.get(userCard.getCardNumber());
				}
				else
				{

					ar =new ArrayList<String>();
				}

				ar.add(new java.util.Date() +":: Card <"+userCard.getCardNumber()+"> used to go from satation " +
						"<"+userCard.getStartStation().getStationName()+" >  to station <"+userCard.getEndStation().getStationName()+"> .Time is  < "  +
						(int)(userCard.getRecentFair() * 2 * 0.75) + " mn> Fare is Rs < " +
						""+userCard.getRecentFair()+"> and balance on the card is < Rs "+userCard.getBalance()+">");

				JOptionPane.showMessageDialog(null,new java.util.Date() +":: \n Card <"+userCard.getCardNumber()+"> \n used to go from satation " +
						"<"+userCard.getStartStation().getStationName()+" >  to station <"+userCard.getEndStation().getStationName()+"> .\nTime is  < "  +
						(int)(userCard.getRecentFair() * 2 * 0.75) + " mn> \n Fare is roubles < " +
						""+userCard.getRecentFair()+"> \nand balance on the card is < Roubles "+userCard.getBalance()+">");

				logsForApi.put(userCard.getCardNumber(),ar);
				//System.out.println("--- "+logsForApi.size());

				//end logging
				mapOfSwipedInCard.remove(userCard.getCardNumber());

				
				
				//logging swip out
				String stationName=userCard.getEndStation().getStationName();
				if(swipeout.containsKey(stationName))
				{
					int totalSwipeOut=swipeout.get(stationName);
					totalSwipeOut++;
					swipeout.put(stationName,totalSwipeOut);
				}
				else
				{
					swipeout.put(stationName,1);
				}

				return true;
			}
			else
			{

				return false;
			}
		}
		else
		{
			System.out.println("MSCS:: Invalid staion selection :p");
			return false;
		}

	}

	public void  showLogs(String cardNumber)
	{
		System.out.println("Logs for card "+cardNumber);
		if(logsForApi.containsKey(cardNumber))
		{
			for(String log :logsForApi.get(cardNumber))
			System.out.println(log);	

		}
		else
		{
			System.out.println("NA");

		}


	}

	public void  showSwapInOut(String stationName)
	{
		System.out.println("Showing total swap in and out for station"+stationName);
		System.out.println("Swap in "+( (swipeIn.get(stationName)==null) ?  0 : swipeIn.get(stationName)) );
		System.out.println("Swap out "+( (swipeout.get(stationName)==null) ?  0 : swipeout.get(stationName)) );

	}

}
