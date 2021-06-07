package com.metro;

import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;
import java.util.Vector;

public class MetroUtility 
{

	private static Vector<Station>   listOfSatations=null;
	public static boolean isValidStation(int stationId)
	{
		try {
			Iterator<Station> it=listOfSatations.iterator();
			while (it.hasNext()) {
				Station st = (Station) it.next();
				if(st.getId()==stationId)
					return true;

			}
			return false;

		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	static
	{
		listOfSatations=new Vector<Station>(10);
		try
		{
			listOfSatations.add(new Station("A1"));
			listOfSatations.add(new Station("A2"));
			listOfSatations.add(new Station("A3"));
			listOfSatations.add(new Station("A4"));
			listOfSatations.add(new Station("A5"));
			listOfSatations.add(new Station("A6"));
			listOfSatations.add(new Station("A7"));
			listOfSatations.add(new Station("A8"));
			listOfSatations.add(new Station("A9"));
			listOfSatations.add(new Station("A10"));
		} 
		catch (MetroException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String generateCardNumber()
	{
		
			String cardId=UUID.randomUUID().toString();	

		return cardId;

	}
	public static boolean isWeekend()
	{

		Calendar cal=Calendar.getInstance();
		int dayofweek=cal.get(Calendar.DAY_OF_WEEK);
		if(dayofweek==7 || dayofweek==1)
			return true;
		else
			return false;

	}

	public static void listStations()
	{

		System.out.println("List of stations ::");
		for(Station s :listOfSatations)
		{
			System.out.println(s.toString());

		}




	}
}
