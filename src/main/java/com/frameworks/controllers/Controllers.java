package com.frameworks.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.frameworks.entities.Flower;
import com.frameworks.entities.Order;
import com.frameworks.repositories.FlowerRepository;
import com.frameworks.repositories.OrderRepository;

@Controller
public class Controllers {
	
	@Autowired
	FlowerRepository flowerRepo;
	@Autowired
	OrderRepository orderRepo;
	
	Order currentOrder;
	
	@GetMapping("/")
	public String doWelcomeWithParams(Model model){
		return "index";
	}
	
	@GetMapping("/displayFlowers")
	public String displayFlowers(Model model){
		Iterable<Flower> flowers = flowerRepo.findAll();
		List<String> flowerStrings = new ArrayList<>();
		flowers.forEach(f->flowerStrings.add(f.toString()));
		model.addAttribute("flowers", flowerStrings);
		return "displayFlowers";
	}
	
	@GetMapping("/displayOrders")
	public String displayOrders(Model model){
		Iterable<Order> orders = orderRepo.findAll();
		List<String> orderStrings = new ArrayList<>();
		orders.forEach(o->orderStrings.add(o.toString()));
		model.addAttribute("flowers", orderStrings);
		return "displayFlowers";
	}
	
	@GetMapping("/newOrder")
	public String newOrder(@RequestParam(value="id", defaultValue="-1")String id,
			@RequestParam(value="shop", defaultValue="noShop")String shop,Model model){
		this.currentOrder = new Order(id, shop);
		return "newOrder";
	}
	
	@GetMapping("/addFlower")
	public int addFlower(@RequestParam(value="type", defaultValue="-1")String type,
			@RequestParam(value="amount", defaultValue="-1")String amount,Model model){
		Flower f = flowerRepo.findOne(type);
		int i = Integer.parseInt(amount);
		if(f==null)
			return -1;
		if(i<1 || i> f.getAvailableAmount())
			return -2;
		f.reduceAvailable(i);
		currentOrder.addFlower(f, i);
		flowerRepo.save(f);
		return 0;
	}
	
	@GetMapping("/removeFlower")
	public int removeFlower(@RequestParam(value="type", defaultValue="-1")String type,
			@RequestParam(value="amount", defaultValue="-1")String amount,Model model){
		Flower f = flowerRepo.findOne(type);
		int i = Integer.parseInt(amount);
		if(f==null)
			return -1;
		if(i<1)
			return -2;
		if(!currentOrder.removeFlower(f, i))
			return -2;
		f.increaseAvailable(i);
		flowerRepo.save(f);
		return 0;
	}
	
	
	@GetMapping("/purchase")
	public int purchase(){
		orderRepo.insertOrderData(currentOrder.getId(), currentOrder.getShop(), false);
		Map<Flower, Integer> content = currentOrder.getContent();
		for (Flower f: content.keySet()){
			orderRepo.insertOrderContent(currentOrder.getId(), f.getType(),  content.get(f));
		}
		return 0;
	}
	
}
