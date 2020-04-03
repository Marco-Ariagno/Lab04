package it.polito.tdp.lab04.model;
import java.util.List;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private CorsoDAO cdao;
	private StudenteDAO sdao;

	public Model() {
		cdao = new CorsoDAO();
		sdao=new StudenteDAO();
	}

	public List<Corso> elencoCorsi(){
		return cdao.getTuttiICorsi();
	}
	
	public Studente studenteDataMatricola(int matricola) {
		return sdao.getStudenteDaMatricola(matricola);	
	}
	
	public List<Studente> studentiDatoCorso(Corso c){
		return sdao.getStudentiDatoCorso(c);
	}
	
	public List<Corso> corsiDataMatricola(Studente s){
		return cdao.getCorsiACuiStudenteEIscritto(s);
	}
	
	public boolean esisteCoppiaCorsoStudente(Corso c, Studente s) {
		return cdao.esisteCoppiaCorsoMatricola(c, s);
	}
	
	public boolean inserisci(Studente s,Corso c) {
		return cdao.inserisciStudenteCorso(s, c);
	}

}
