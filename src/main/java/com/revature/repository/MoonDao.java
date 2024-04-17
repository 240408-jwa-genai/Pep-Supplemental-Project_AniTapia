package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.MoonFailException;
import com.revature.models.Moon;
import com.revature.models.Planet;
import com.revature.utilities.ConnectionUtil;

public class MoonDao {
    
    public List<Moon> getAllMoons() {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from moons";
			PreparedStatement ps = connection.prepareStatement(sql);
			List<Moon> moonList = new ArrayList<Moon>();
			Moon moon = new Moon();
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				moonList.add(moon);
			}
			return moonList;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Moon getMoonByName(String moonName) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from moons where name = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1,moonName);
			Moon possibleMoon = new Moon();
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				possibleMoon.setId(rs.getInt("id"));
				possibleMoon.setName(rs.getString("name"));
				possibleMoon.setMyPlanetId(rs.getInt("myPlanetId"));
			}
			return possibleMoon;

		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Moon getMoonById(int moonId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,moonId);
			Moon possibleMoon = new Moon();
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				possibleMoon.setId(rs.getInt("id"));
				possibleMoon.setName(rs.getString("name"));
				possibleMoon.setMyPlanetId(rs.getInt("myPlanetId"));
			}
			return possibleMoon;

		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public Moon createMoon(Moon m) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "insert into moons (name, myPlanetId) values (?,?)";
			PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,m.getName());
			ps.setInt(2,m.getMyPlanetId());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			Moon newMoon = new Moon();
			newMoon.setName(m.getName());
			newMoon.setMyPlanetId(m.getMyPlanetId());
			if(rs.next()){
				newMoon.setId(rs.getInt(1));
			}
			return newMoon;
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteMoonById(int moonId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "delete from moons where id = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, moonId);
			int rowCount = ps.executeUpdate();
			if(rowCount == 0)return false;
			else return true;
		}catch (SQLException e){
			e.printStackTrace();
			return false;
		}
	}

	public List<Moon> getMoonsFromPlanet(int planetId) {
		try(Connection connection = ConnectionUtil.createConnection()){
			String sql = "select * from moons where myPlanetId = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1,planetId);
			List<Moon> moonList = new ArrayList<Moon>();
			Moon moon = new Moon();
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				moon.setId(rs.getInt("id"));
				moon.setName(rs.getString("name"));
				moon.setMyPlanetId(rs.getInt("myPlanetId"));
				moonList.add(moon);
			}
			return moonList;
		}catch (SQLException e){
			e.printStackTrace();
			return null;
		}
	}
}
