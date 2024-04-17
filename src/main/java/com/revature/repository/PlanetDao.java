package com.revature.repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Planet;
import com.revature.models.User;
import com.revature.utilities.ConnectionUtil;

public class PlanetDao {
    
    public List<Planet> getAllPlanets(int currentUserId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from planets where ownerId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,currentUserId);
			List<Planet> planetList = new ArrayList<Planet>();
			Planet planet = new Planet();
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				planet.setId(rs.getInt("id"));
				planet.setName(rs.getString("name"));
				planet.setOwnerId(rs.getInt("owenerId"));
				planetList.add(planet);
			}
			return planetList;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Planet getPlanetByName(String planetName) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from planets where name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,planetName);
			Planet possiblePlanet = new Planet();
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				possiblePlanet.setId(rs.getInt("id"));
				possiblePlanet.setName(rs.getString("name"));
				possiblePlanet.setOwnerId(rs.getInt("owenerId"));
			}
			return possiblePlanet;

		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Planet getPlanetById(int planetId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from planets where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,planetId);
			Planet possiblePlanet = new Planet();
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				possiblePlanet.setId(rs.getInt("id"));
				possiblePlanet.setName(rs.getString("name"));
				possiblePlanet.setOwnerId(rs.getInt("owenerId"));
			}
			return possiblePlanet;

		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Planet createPlanet(Planet p) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "insert into planets (name, owenerId) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,p.getName());
			ps.setInt(2,p.getOwnerId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			Planet newPlanet = new Planet();
			newPlanet.setName(p.getName());
			newPlanet.setOwnerId(p.getOwnerId());
			if(rs.next()){
				newPlanet.setId(rs.getInt(1));
			}
			return newPlanet;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean deletePlanetById(int planetId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "delete from planets where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, planetId);
			int rowCount = ps.executeUpdate();
			if(rowCount == 0)return false;
			else return true;
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}
}
