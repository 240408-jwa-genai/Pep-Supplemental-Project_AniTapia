package com.revature.controller;

import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.service.MoonService;

public class MoonController {
	
	private MoonService moonService;

	public MoonController(MoonService moonService) {
		this.moonService = moonService;
	}

	public void getAllMoons(int currentUserId) {
//		System.out.println("These are all the moons the in the planetarium");
//		System.out.println();
	}

	public void getMoonByName(int currentUserId, String name) {
		Moon m = moonService.getMoonByName(currentUserId,name);
		if(m.getId() != 0) System.out.println("This is the moon:\n" + m);
		else System.out.println("Unable to retrive moon. Please dobule check your input");

	}

	public void getMoonById(int currentUserId, int id) {
		Moon m= moonService.getMoonById(currentUserId,id);
		if(m.getId() != 0) System.out.println("This is your moon:\n" + m);
		else System.out.println("Unable to retrive planet. Please dobule check your input");

	}

	public void createMoon(int currentUserId, Moon moon) {
		// TODO: implement
	}

	public void deleteMoon(int id) {
		Boolean moonDeleted = moonService.deleteMoonById(id);
		if(moonDeleted) System.out.println("Moon with id: " + id + " has been successfully deleted.");
		else System.out.println("failed to delete moon with id: " + id);
	}
	
	public void getPlanetMoons(int myPlanetId) {
		// TODO: implement
	}
}
