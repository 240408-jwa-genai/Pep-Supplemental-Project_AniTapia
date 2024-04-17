package com.revature.controller;

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
		// TODO: implement
	}

	public void getPlanetByID(int currentUserId, int id) {
		// TODO: implement
	}

	public void createPlanet(int currentUserId, Planet planet) {
		// TODO: implement
	}

	public void deletePlanet(int currentUserId, int id) {
		// TODO: implement
	}
}
