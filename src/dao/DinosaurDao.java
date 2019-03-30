package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Dinosaur;

public class DinosaurDao {
	
	private Connection connection;
	private final String GET_DINOSAURS_QUERY = "SELECT * FROM dinosaurs";
	private final String GET_DINOSAUR_BY_ID_QUERY = "SELECT * FROM dinosaurs WHERE id = ?";
	private final String CREATE_NEW_DINOSAUR_QUERY = "INSERT INTO dinosaurs(species_name, diet, stance) VALUES(?, ?, ?)";
	private final String DELETE_DINOSAUR_BY_ID_QUERY = "DELETE FROM dinosaurs WHERE id = ?";
	
	public DinosaurDao() {
		connection = DBConnection.getConnection();
	}
	
	public List<Dinosaur> getDinosaurs() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_DINOSAURS_QUERY).executeQuery();
		List<Dinosaur> dinosaurs = new ArrayList<Dinosaur>();
		
		while (rs.next()) {
			dinosaurs.add(populateDinosaur(rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4)));
		}
		
		return dinosaurs;
	}
	
	public Dinosaur getDinosaurById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(GET_DINOSAUR_BY_ID_QUERY);
		ps.setInt(1,  id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return populateDinosaur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
	}
	
	public void createNewDinosaur(String speciesName, String diet, String stance) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_DINOSAUR_QUERY);
		ps.setString(1, speciesName);
		ps.setString(2, diet);
		ps.setString(3, stance);
		ps.executeUpdate();
	}
	
	public void deleteDinosaurById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_DINOSAUR_BY_ID_QUERY);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	private Dinosaur populateDinosaur(int id, String speciesName, String diet, String stance) {
		return new Dinosaur(id, speciesName, diet, stance);
	}
	
}
