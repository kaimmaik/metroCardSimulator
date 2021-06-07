package com.metro;

import com.metro.charging.WeekendWeekdayStrategy;

import javax.swing.*;

public class test
{
	public static void main(String args[])
	{
		
		SapientMetroSimulater sap = new SapientMetroSimulater();
		CardFactory cardFacrtory = CardFactory.getInstance(new WeekendWeekdayStrategy());
	    System.out.println("SIMULATER:::Creating a card with initail balance with 4.5 roubles");
		Card c4_5=cardFacrtory.IssueACard(4.5f);
		System.out.println("SIMULATER:::User trying to swipe card at station number 2");
		boolean isallowed=sap.swipeIn(c4_5, 2);



		String amount =JOptionPane.showInputDialog(null,"Enter your balance :");
		float amountF = Float.parseFloat(amount);


		Card c100 = cardFacrtory.IssueACard(amountF);
		String[] choices = { "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "A10" };
		String input = (String) JOptionPane.showInputDialog(null, "Choose from :",
				"The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null,choices, choices[1]);
		int from = Integer.parseInt(input.substring(1,input.length()));


		String input1 = (String) JOptionPane.showInputDialog(null, "Choose too :",
				"The Choice of a Lifetime", JOptionPane.QUESTION_MESSAGE, null,choices, choices[1]);
		int too = Integer.parseInt(input1.substring(1,input.length()));

		System.out.println("SIMULATER:::User trying to swipe card at station number 4");
		isallowed=sap.swipeIn(c100, from);
		System.out.println("SIMULATER:::User trying to exit card at station number 10");
		isallowed=sap.swipeOut(c100, too);

		
		
		System.out.println("SIMULATER:::showing log");
		sap.showLogs(c100.getCardNumber());
	
	}

}
