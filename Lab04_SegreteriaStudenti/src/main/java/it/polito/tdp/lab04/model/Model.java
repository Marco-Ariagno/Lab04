package it.polito.tdp.lab04.model;

import java.util.LinkedList;
import java.util.List;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.IscrizioniDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO cdao;
	private StudenteDAO sdao;
	private IscrizioniDAO idao;

	public Model() {
		cdao = new CorsoDAO();
		sdao=new StudenteDAO();
		idao=new IscrizioniDAO();
	}

	public List<Corso> elencoCorsi(){
		return cdao.getTuttiICorsi();
	}
	
	public Studente studenteDataMatricola(int matricola) {
		return sdao.getStudenteDaMatricola(matricola);	
	}
	
	public List<Studente> studentiDatoCorso(Corso c){
		List<Iscrizione> matricole=idao.iscrizioniPerCorso(c);
		List<Studente> studenti=new LinkedList<Studente>();
		for(Iscrizione i:matricole) {
			studenti.add(sdao.getStudenteDaMatricola(i.getMatricola()));
		}
		return studenti;
	}
	
	public List<Corso> corsiDataMatricola(int matricola){
		Studente s=sdao.getStudenteDaMatricola(matricola);
		List<Iscrizione> corsi=idao.corsiDataMatricola(s);
		List<Corso> cors=new LinkedList<>();
		for(Iscrizione i:corsi) {
			cors.addAll(cdao.getCorsoDaIscrizione(i));
		}
		return cors;
	}
	
	public boolean esisteCoppiaCorsoStudente(Corso c, Studente s) {
		return idao.esisteCoppiaCorsoMatricola(c, s);
	}
	
	public boolean inserisci(Studente s,Corso c) {
		return idao.inserisciStudenteCorso(s, c);
	}

}
