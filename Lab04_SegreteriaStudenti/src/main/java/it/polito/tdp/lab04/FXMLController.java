/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<Corso> comboBoxCorsi;
	// popolo la tendina nel setModel

	@FXML
	private Button btnCercaIscrittiCorso;

	@FXML
	private TextField txtMatricola;

	@FXML
	private Button btnX;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCognome;

	@FXML
	private Button btnCercaCorsi;

	@FXML
	private Button btnIscrivi;

	@FXML
	private TextArea txtResult;

	@FXML
	private Button btnReset;

	@FXML
	void doCercaCorsi(ActionEvent event) {
		txtResult.clear();
		String matricola = txtMatricola.getText();
		int matr = 0;
		try {
			matr = Integer.parseInt(matricola);
		} catch (NumberFormatException nfe) {
			txtResult.appendText("Devi inserire un numero!!\n");
		}
		Studente s = model.studenteDataMatricola(matr);
		if (s == null) {
			txtResult.appendText("Matricola non esistente");
			return;
		}
		Corso c = comboBoxCorsi.getValue();
		if (c == null || c.getCodins().equals(" ")) {
			List<Corso> corsi = model.corsiDataMatricola(new Studente(matr, null, null, null));
			if (corsi.size() == 0) {
				txtResult.appendText("Studente non iscritto ad alcun corso");
			}
			for (Corso co : corsi) {
				txtResult.appendText(
						co.getCodins() + " " + co.getCrediti() + " " + co.getNome() + " " + co.getPd() + "\n");
			}
		} else {
			boolean esiste = model.esisteCoppiaCorsoStudente(c, s);
			if (esiste == true) {
				txtResult.appendText("Studente iscritto a questo corso\n");
			} else {
				txtResult.appendText("Studente NON iscritto a questo corso\n");
			}
		}
	}

	@FXML
	void doCercaIscrittiCorso(ActionEvent event) {
		txtResult.clear();
		Corso c = comboBoxCorsi.getValue();
		if (c == null) {
			txtResult.appendText("Errore: non hai selezionato nessun corso!\n");
			return;
		}
		if (c.getCodins().equals(" ")) {
			txtResult.appendText("Errore: non hai selezionato nessun corso!\n");
			return;
		}
		List<Studente> studenti = model.studentiDatoCorso(c);
		if (studenti.size() == 0) {
			txtResult.appendText("Nessuno studente insritto a questo corso\n");
			return;
		}
		for (Studente s : studenti) {
			txtResult.appendText(s.toString() + "\n");
		}
	}

	@FXML
	void doCercaNomeECognomeDataMatricola(ActionEvent event) {
		int matricola = controlloMatricola();
		if (matricola == 0) {
			return;
		}
		Studente s = model.studenteDataMatricola(matricola);
		if (s == null) {
			txtResult.appendText("Matricola non esistente\n");
			return;
		}
		txtNome.appendText(s.getNome());
		txtCognome.appendText(s.getCognome());
	}

	@FXML
	void doIscrivi(ActionEvent event) {
		Corso c = comboBoxCorsi.getValue();
		int matricola = controlloMatricola();
		if (matricola == 0) {
			return;
		}
		Studente s = model.studenteDataMatricola(matricola);
		boolean inseribile = model.inserisci(s, c);
		if (inseribile == false) {
			txtResult.appendText("Studente gia iscritto a questo corso\n");
		} else {
			txtResult.appendText("Studente iscritto ora a questo corso\n");
		}
	}

	@FXML
	void doReset(ActionEvent event) {
		txtMatricola.clear();
		txtNome.clear();
		txtCognome.clear();
		txtResult.clear();
		comboBoxCorsi.getSelectionModel().clearSelection();
	}

	public int controlloMatricola() {
		String matricola = txtMatricola.getText();
		int matr = 0;
		try {
			matr = Integer.parseInt(matricola);
		} catch (NumberFormatException nfe) {
			txtResult.appendText("Digita un numero di matricola!!\n");
		}
		return matr;
	}

	@FXML
	void initialize() {
		assert comboBoxCorsi != null : "fx:id=\"comboBoxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaIscrittiCorso != null : "fx:id=\"btnCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnX != null : "fx:id=\"btnX\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";
		assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";

	}

	public void setModel(Model model) {
		this.model = model;
		comboBoxCorsi.getItems().add(new Corso(" ", -1, " ", -1));
		comboBoxCorsi.getItems().addAll(this.model.elencoCorsi());
	}

}
