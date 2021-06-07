package com.metro;


public class MetroException extends Exception 
{
	private String message;
	
	public MetroException()
	{
		super();
		
	}
	public MetroException(String _message)
	{
		super(_message);
		this.message=_message;
	
		
	}
	public String toString()
	{
		
		return "METRO Exception [ "+this.message+"] ";
		
	}

}
