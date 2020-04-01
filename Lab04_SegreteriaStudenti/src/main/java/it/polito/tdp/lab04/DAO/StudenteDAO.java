package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getStudenteDaMatricola (int matricola) {
		
		final String sql="SELECT * FROM studente WHERE matricola=?";
		Studente s=null;
		try {
			Connection conn=ConnectDB.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				s=new Studente(rs.getInt("matricola"),rs.getString("cognome"),rs.getString("nome"),rs.getString("CDS"));
			}
			
			conn.close();
			
		}catch(SQLException e) {
			throw new RuntimeException();
		}
		
		return s;
		
	}
	
	

}
