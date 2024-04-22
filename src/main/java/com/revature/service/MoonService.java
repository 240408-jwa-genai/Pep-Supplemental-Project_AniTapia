package com.revature.service;

import java.util.List;

import com.revature.MainDriver;
import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.repository.MoonDao;
import com.revature.repository.PlanetDao;

public class MoonService {

	private MoonDao dao;
	private PlanetDao planetDao = new PlanetDao();


	public MoonService(MoonDao dao) {
		this.dao = dao;
	}

	public List<Moon> getAllMoons(int currentUserId) {
		return dao.getAllMoons(currentUserId);
	}

	public Moon getMoonByName(String moonName, int currentUserId) {
		return dao.getMoonByName(moonName,currentUserId);
	}

	public Moon getMoonById(int moonId, int currentUSerId) {
		return dao.getMoonById(moonId,currentUSerId);
	}

	public Moon createMoon(Moon m) {
		if(m.getName().length() <= 30){
			Moon retrievedMoon = dao.getMoonByName(m.getName());
			if(retrievedMoon != null){
				String retrievedMoonName = retrievedMoon.getName();
				String mName = m.getName();
				Planet retrievedPlanet = planetDao.getPlanetById(m.getMyPlanetId());
				if(!mName.equals(retrievedMoonName) && retrievedPlanet.getOwnerId() == MainDriver.loggedInUserId)return dao.createMoon(m);
			}
		}
		return new Moon();
	}

	public boolean deleteMoonById(int moonId, int currentUserId) {
		Moon retrievedMoon = getMoonById(moonId,currentUserId);
		if(retrievedMoon.getId() != 0)return dao.deleteMoonById(moonId);
		else return false;
	}

	public List<Moon> getMoonsFromPlanet(int myPlanetId,int currentUserId) {
		return  dao.getMoonsFromPlanet(myPlanetId,currentUserId);
	}
}
