package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente getStudenteDaMatricola(int matricola) {

		final String sql = "SELECT * FROM studente WHERE matricola=?";
		Studente s = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("CDS"));
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return s;

	}

	public List<Studente> getStudentiDatoCorso(Corso c) {
		String sql = "SELECT s.matricola, s.cognome, s.nome, s.CDS " + "FROM studente AS s, iscrizione AS i "
				+ "WHERE s.matricola=i.matricola AND i.codins=?";
		List<Studente> studenti = new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, c.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Studente s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("CDS"));
				studenti.add(s);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return studenti;

	}

}
