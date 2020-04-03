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

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				// System.out.println(codins + " " + numeroCrediti + " " + nome + " " +
				// periodoDidattico);

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}

			conn.close();

			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {

	}

	public List<Corso> getCorsoDaIscrizione(Iscrizione i) {
		final String sql = "SELECT * FROM corso WHERE codins=?";
		List<Corso> corsi = new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, i.getCodins());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				// System.out.println(codins + " " + numeroCrediti + " " + nome + " " +
				// periodoDidattico);

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return corsi;

	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public void getStudentiIscrittiAlCorso(Corso corso) {
		// TODO
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}

	public List<Corso> getCorsiACuiStudenteEIscritto(Studente s) {
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd FROM corso AS c, iscrizione AS i WHERE c.codins=i.codins AND i.matricola=?";
		List<Corso> corsi = new LinkedList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, s.getMatricola());
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				// System.out.println(codins + " " + numeroCrediti + " " + nome + " " +
				// periodoDidattico);

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException();
		}

		return corsi;
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
