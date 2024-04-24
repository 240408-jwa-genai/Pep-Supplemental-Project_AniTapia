package com.revature.controller;

import com.revature.MainDriver;
import com.revature.models.Planet;
import com.revature.service.PlanetService;

public class PlanetController {
	
	private PlanetService planetService;

	public PlanetController(PlanetService planetService){
		this.planetService = planetService;
	}

	public void getAllPlanets(int currentUserId) {
		System.out.println("These are your planets");
		System.out.println(planetService.getAllPlanets(currentUserId));
	}

	public void getPlanetByName(int currentUserId, String name) {
		Planet p = planetService.getPlanetByName(currentUserId,name);
		if(p.getId() != 0) System.out.println("This is your planet:\n" + p + "\nThe planet's moons: " + MainDriver.moonService.getMoonsFromPlanet(p.getId(),currentUserId));
		else System.out.println("Unable to retrieve planet. Please double check your input");
	}

	public void getPlanetByID(int currentUserId, int id) {
		Planet p = planetService.getPlanetById(currentUserId,id);
		if(p.getId() != 0) System.out.println("This is your planet:\n" + p + "\nThe planet's moons: " + MainDriver.moonService.getMoonsFromPlanet(id,currentUserId));
		else System.out.println("Unable to retrieve planet. Please double check your input");
	}

	public void createPlanet(int currentUserId, Planet planet) {
		if(planet.getName().equals("pluto")) System.out.println("Nice try! Pluto is not a planet.");
		else{
			Planet planetResponse = planetService.createPlanet(currentUserId,planet);
			if(planetResponse.getId() != 0) System.out.println("Planet created successfully!");
			else System.out.println("Planet creation failed. Please double check your input");
		}
	}

	public void deletePlanet(int id, int currentUserId) {
		Boolean planetDeleted = planetService.deletePlanetById(id, currentUserId);
		if(planetDeleted) System.out.println("Planet with id: " + id + " and all of its moons have been successfully deleted");
		else System.out.println("Failed to delete planet. Please double check your input");
	}
}
