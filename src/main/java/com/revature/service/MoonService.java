package com.revature.service;

import java.util.List;

import com.revature.models.Moon;
import com.revature.repository.MoonDao;

public class MoonService {

	private MoonDao dao;

	public MoonService(MoonDao dao) {
		this.dao = dao;
	}

	public List<Moon> getAllMoons() {
		return dao.getAllMoons();
	}

	public Moon getMoonByName(int myPlanetId, String moonName) {
		Moon retrievedMoon = dao.getMoonByName(moonName);
		if(retrievedMoon.getMyPlanetId() == myPlanetId)return retrievedMoon;
		else return new Moon();
	}

	public Moon getMoonById(int myPlanetId, int moonId) {
		Moon retrievedMoon = dao.getMoonById(moonId);
		if(retrievedMoon.getMyPlanetId() == myPlanetId)return retrievedMoon;
		else return new Moon();
	}

	public Moon createMoon(Moon m) {
		if(m.getName().length() <= 30){
			Moon retrievedMoon = dao.getMoonByName(m.getName());
			if(retrievedMoon != null){
				String retrievedMoonName = retrievedMoon.getName();
				String mName = m.getName();
				if(!mName.equals(retrievedMoonName))return dao.createMoon(m);
			}
		}
		return new Moon();
	}

	public boolean deleteMoonById(int moonId) {
		return dao.deleteMoonById(moonId);
	}

	public List<Moon> getMoonsFromPlanet(int myPlanetId) {
		return  dao.getMoonsFromPlanet(myPlanetId);
	}
}
