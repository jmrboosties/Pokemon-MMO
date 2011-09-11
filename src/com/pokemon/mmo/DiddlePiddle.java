package com.pokemon.mmo;

import java.awt.Point;

import com.pokemon.mmo.Enums.Weather;

public class DiddlePiddle {
	
	private Point mPoint1;
	private Point mPoint2;
	
	private Weather mWeather;

	public DiddlePiddle(Point point1, Point point2) {
		this.mPoint1 = point1;
		this.mPoint2 = point2;
		
		mWeather = Weather.NORMAL;
		System.out.println(mWeather.name());
		Items item = new Items(this);
		item.members();
		System.out.println(mWeather.name());
		
		tricky(mPoint1, mPoint2);
	}
	
	public void tricky(Point arg1, Point arg2)	{
	  arg1.x = 100;
	  arg1.y = 100;
	  Point temp = arg1;
	  arg1 = arg2;
	  arg2 = temp;
	}
	
	public void setWeather(Weather weather) {
		mWeather = weather;
	}
	
	public Weather getWeather() {
		return mWeather;
	}
	
//	public static void main(String [] args) {
//	  Point pnt1 = new Point(0,0);
//	  Point pnt2 = new Point(0,0);
//	  System.out.println("X: " + pnt1.x + " Y: " +pnt1.y); 
//	  System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);
//	  System.out.println(" ");
//	  tricky(pnt1,pnt2);
//	  System.out.println("X: " + pnt1.x + " Y:" + pnt1.y); 
//	  System.out.println("X: " + pnt2.x + " Y: " +pnt2.y);  
//	}
}
