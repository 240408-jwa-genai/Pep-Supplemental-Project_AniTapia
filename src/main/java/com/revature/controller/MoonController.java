package com.revature.controller;

import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.service.MoonService;

import java.util.List;

public class MoonController {
	
	private MoonService moonService;

	public MoonController(MoonService moonService) {
		this.moonService = moonService;
	}

	public void getAllMoons(int currentUserId) {
		System.out.println("These are all the moons the in the planetarium");
		System.out.println(moonService.getAllMoons(currentUserId));
	}

	public void getMoonByName(String moonName, int currentUserId) {
		Moon m = moonService.getMoonByName(moonName,currentUserId);
		if(m.getId() != 0) System.out.println("This is the moon:\n" + m);
		else System.out.println("Unable to retrieve moon. Please double check your input");

	}

	public void getMoonById(int moonId, int currentUserId) {
		Moon m= moonService.getMoonById(moonId,currentUserId);
		if(m.getId() != 0) System.out.println("This is your moon:\n" + m);
		else System.out.println("Unable to retrieve moon. Please double check your input");

	}

	public void createMoon(int currentUserId, Moon moon) {
		Moon moonResponse = moonService.createMoon(moon);
		if(moonResponse.getId() != 0) System.out.println("Moon created successfully");
		else System.out.println("Moon creation failed");
	}

	public void deleteMoon(int id, int currentUserId) {
		Boolean moonDeleted = moonService.deleteMoonById(id,currentUserId);
		if(moonDeleted) System.out.println("Moon with id: " + id + " has been successfully deleted.");
		else System.out.println("Failed to delete moon. Please double check your input");
	}
	
	public void getPlanetMoons(int myPlanetId,int currentUserId) {
		List<Moon> moonList = moonService.getMoonsFromPlanet(myPlanetId,currentUserId);
		if(moonList.size() != 0){
			System.out.println("These are all the moons the in the planetarium belonging to planet with planedId " + myPlanetId);
			System.out.println(moonList);
		}
		else System.out.println("Planet is empty of moons or input didn't match any of your planets");

	}
}
