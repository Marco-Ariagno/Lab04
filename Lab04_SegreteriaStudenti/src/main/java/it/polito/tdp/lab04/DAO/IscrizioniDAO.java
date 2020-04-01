package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Iscrizione;
import it.polito.tdp.lab04.model.Studente;

public class IscrizioniDAO {

	public List<Iscrizione> iscrizioniPerCorso(Corso c) {
		final String sql = "SELECT * FROM iscrizione WHERE codins=?";
		List<Iscrizione> iscrizioni = new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, c.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Iscrizione i = new Iscrizione(rs.getInt("matricola"), rs.getString("codins"));
				iscrizioni.add(i);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return iscrizioni;

	}

	public List<Iscrizione> corsiDataMatricola(Studente s) {
		final String sql = "SELECT * FROM iscrizione WHERE matricola=?";
		List<Iscrizione> iscrizioni = new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Iscrizione i = new Iscrizione(rs.getInt("matricola"), rs.getString("codins"));
				iscrizioni.add(i);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return iscrizioni;

	}

	public boolean esisteCoppiaCorsoMatricola(Corso c, Studente s) {
		final String sql = "SELECT * FROM iscrizione WHERE (matricola=? AND codins=?)";
		List<Iscrizione> iscrizioni = new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Iscrizione i = new Iscrizione(rs.getInt("matricola"), rs.getString("codins"));
				iscrizioni.add(i);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		if (iscrizioni.size() == 0) {
			return false;
		} else {
			return true;
		}

	}
	
	public boolean inserisciStudenteCorso(Studente s, Corso c) {
		if(esisteCoppiaCorsoMatricola(c,s)==true) {
			return false;
		}
		final String sql = "INSERT INTO iscrizione (matricola,codins) VALUES (?,?)";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			st.setString(2, c.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return true;
	}

}
