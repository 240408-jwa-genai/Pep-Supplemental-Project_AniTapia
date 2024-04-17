package com.revature.service;

import java.util.List;

import com.revature.models.Planet;
import com.revature.repository.PlanetDao;

public class PlanetService {

	private PlanetDao dao;

	public PlanetService(PlanetDao dao){
		this.dao = dao;
	}

	public List<Planet> getAllPlanets(int currentUserId) {
		return dao.getAllPlanets(currentUserId);
	}

	public Planet getPlanetByName(int ownerId, String planetName) {
		Planet retrievedPlanet = dao.getPlanetByName(planetName);
		if(retrievedPlanet.getOwnerId() == ownerId)return retrievedPlanet;
		else return new Planet();
	}

	public Planet getPlanetById(int ownerId, int planetId) {
		Planet retrievedPlanet = dao.getPlanetById(planetId);
		if(retrievedPlanet.getOwnerId() == ownerId)return retrievedPlanet;
		else return new Planet();
	}

	public Planet createPlanet(int ownerId, Planet planet) {
		if(planet.getName().length() <= 30){
			Planet retrievedPlanet = dao.getPlanetByName(planet.getName());
			if(retrievedPlanet == null){
				return dao.createPlanet(planet);
			}
		}
		return new Planet();
	}

	public boolean deletePlanetById(int planetId) {
		return dao.deletePlanetById(planetId);
	}
}
