package Test;

import java.text.ParseException;

import javax.ejb.Remote;


/**
 * Interfaccia remota del session bean che si occupa di pulire il
 * database ed inizializzarlo con valori predefiniti.
 * È utilizzato dai test JUnit per resettare lo stato prima
 * dell'esecuzione dei test stessi. 
 * @author Giusto - Mazuran
 */
@Remote
public interface ManagerInizializzazioneDatabaseRemote {

	/**
	 * Numero di studenti predefinito
	 */
	public static final int NUMERO_STUDENTI = 8;
	
	/**
	 * Numero di professori predefinito
	 */
	public static final int NUMERO_PROFESSORI = 2;
	
	/**
	 * Numero di amministratori predefiniti
	 */
	public static final int NUMERO_ADMIN = 1;
	
	/**
	 * Numero di progetti predefiniti
	 */
	public static final int NUMERO_PROGETTI = 4;
	
	/**
	 * Numero di release predefinite
	 */
	public static final int NUMERO_RELEASE = 8;
	
	/**
	 * Numero di progettigruppo predefiniti
	 */
	public static final int NUMERO_PROG_GRP = 8;
	
	/**
	 * Numero di gruppi predefiniti
	 */
	public static final int NUMERO_GRUPPI = 4;
	
	/**
	 * username degli studenti predefiniti
	 */
	public static final String USR_STUDENTI_PRED[] = {
		"usr1", "usr2", "usr3", "usr4", "usr5", "usr6", "usr7", "usr8"
	};
	
	/**
	 * username dei professori predefiniti
	 */
	public static final String USR_PROFRSSORI_PRED[] = {
		"usr9", "usr10", "usr11", "usr12", "usr13", "usr14", "usr15", "usr16"
	};
	
	/**
	 * username degli amministratori predefiniti
	 */
	public static final String USR_ADMIN_PRED[] = {
		"usr17"
	};
	
	/**
	 * nome dei gruppi predefiniti
	 */
	public static final String NOME_GRUPPI_PRED[] = {
		"gruppo1", "gruppo2", "gruppo3", "gruppo4"
	};
	
	/**
	 * tipi release predefiniti
	 */
	public static final String TIPO_RELEASE_PRED[] = {
		"RASD", "DD", "Implementazione", "Testing", "Desing", "Riproduzione", "Pubblicazione", "Debug"
	};
	
	/**
	 * Password degli utenti predefiniti
	 */
	public static final String PASSWORD_UTENTI_PREDEFINITA[] = {
		"pwd1", "pwd2", "pwd3", "pwd4", "pwd5", "pwd6", "pwd7", "pwd8"
	};
	
	/**
	 * nome degli amministratori predefiniti
	 */
	public static final String NOME_ADMIN_PRED[] = {
		"nomeAdmin1", "nomeAdmin2"
	};
	
	/**
	 * nome dei professori predefiniti
	 */
	public static final String NOME_PROF_PRED[] = {
		"nomeProf1", "nomeProf2"
	};
	
	/**
	 * nome degli studenti predefiniti
	 */
	public static final String NOME_STUD_PRED[] = {
		"nomeStud1", "nomeStud2", "nomeStud3", "nomeStud4", "nomeStud5", "nomeStud6", "nomeStud7", "nomeStud8"
	};
	
	/**
	 * Cognome degli utenti predefiniti
	 */
	public static final String COGNOME_UTENTI_PREDEFINITI[] = {
		"Fassino", "Di Pietro", "Passera", "Pisapia", "Vendola", "Bersani", "DePaola", "Monti"
	};
	
	/**
	 * Matricole degli studenti predefinite
	 */
	public static final String MATR_STUDENTI_PREDEFINITI[] = {
		"12345", "1231", "3144", "2314141", "34141576", "35657", "9678", "89576"
	};
	
	/**
	 * Email degli utenti predefiniti
	 */
	public static final String EMAIL_UTENTI_PREDEFINITI[] = {
		"info_mph@libero.it", "info_mph@libero.it", "info_mph@libero.it",
		"info_mph@libero.it", "info_mph@libero.it", "info_mph@libero.it", 
		"info_mph@libero.it", "info_mph@libero.it"
	};
	
	/**
	 * Telefoni dei prof predefiniti
	 */
	public static final String TEL_UTENTI_PREDEFINITI[] = {
		"038325", "4535446"
	};
	
	/**
	 * Pulisce il database, svuotandolo completamente
	 */
	public void pulisci();
	
	/**
	 * Crea gli utenti predefiniti
	 */
	public void creaDatiProfessore();

	public void creaProfAndProgetti() throws ParseException;
	
}
