package com.frameworks.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Flower {

	@Id
	String type;
	
	int availableAmount;
	double pricePerUnit;
	
	public Flower(String type, int amount, double price){
		this.type = type;
		this.availableAmount = amount;
		this.pricePerUnit = price;
	}
	
	public String getType(){
		return type;
	}
	public int getAvailableAmount(){
		return availableAmount;
	}
	public double getPricePerUnit(){
		return pricePerUnit;
	}
	public void setType(String s){
		type = s;
	}
	public void setAvailableAmount(int n){
		availableAmount = n;
	}
	public void setPricePerUnit(double n){
		pricePerUnit = n;
	}
	public boolean reduceAvailable(int purchased){
		if(purchased > availableAmount || purchased <= 0)
			return false;
		availableAmount -= purchased;
		return true;
	}
	public boolean increaseAvailable(int returned){
		if(returned <= 0)
			return false;
		availableAmount += returned;
		return true;
	}
	
	@Override
	public String toString(){
		return type;
	}
}
