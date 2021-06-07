package com.metro;

public final class Station
{
	private String stationName;
	private int id;
	
	public Station(String _stationName) throws MetroException
	{
		this.stationName=_stationName;
		try
		{
		this.id=Integer.parseInt(this.stationName.substring(1));
		}
		catch (NumberFormatException e) 
		{
		   throw new MetroException("Invalid metro station name :p");
		}
	}

	@Override
	public String toString() {

		return "Id [ "+id+"  ]  , Station name [ "+stationName+" ]   ";
	}
    public int  getId()
    {
    	return id;
    	
    	
    }
    public Station(int stationId)
    {
    	this.id=stationId;
    	this.stationName="A"+this.id;
    	
    }
	public String getStationName()
	{
		return stationName;
		
	}
}
