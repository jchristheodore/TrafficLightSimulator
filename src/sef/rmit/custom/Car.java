/*
 * @author:
 */

package sef.rmit.custom;

public class Car 
{
	private int regNo;
	/*
	 * @Author: Eashan
	 * @returnType: String
	 * @Description: Used to Print details of car object
	 */
	@Override
	public String toString() 
	{
		return "Car [regNo=" + regNo + "]";
	}

	public Car(int regNo) 
	{
		super();
		this.regNo = regNo;
	}
	
	public int getRegNo() 
	{
		return regNo;
	}
	
	public void setRegNo(int regNo) 
	{
		this.regNo = regNo;
	}
	
}
