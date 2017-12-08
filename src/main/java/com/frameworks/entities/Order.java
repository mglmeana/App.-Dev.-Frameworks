package com.frameworks.entities;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {

	@Id
	String id;
	
	String shop;
	Map<Flower, Integer> content;
	boolean payed;
	
	public Order(String id, String shop){
		this.id = id;
		this.shop = shop;
		this.content = new HashMap<Flower, Integer>();
		this.payed = false;
	}
	
	public String getId(){
		return id;
	}
	public String getShop(){
		return shop;
	}
	public Map<Flower, Integer> getContent(){
		return content;
	}
	public boolean isPayed(){
		return payed;
	}
	public void pay(){
		payed = true;
	}
	public int getPrice(){
		int price = 0;
		for(Flower f:content.keySet())
			price += f.getPricePerUnit()*content.get(f);
		return price;
	}
	
	@Override
	public String toString(){
		String aux = "";
		aux += shop + " - " + id;
		if(content.isEmpty())
			return aux;
		aux += " (" + String.format("%.2f", getPrice())+ "€)";
		aux += ":";
		for(Flower f:content.keySet()){
			aux+= " " + f.toString() + " (" + content.get(f) + ")";
		}
		return aux;
	}
	
	public void addFlower(Flower f, int n){
		if(content.containsKey(f))
			content.put(f, content.get(f)+n);
		else
			content.put(f,  n);
	}
	
	public boolean removeFlower(Flower f, int n){
		if(!content.containsKey(f)) return false;
		if(content.get(f)<=n) 
			content.remove(f);
		else
			content.put(f, content.get(f)- n);
		return true;
	}
}
