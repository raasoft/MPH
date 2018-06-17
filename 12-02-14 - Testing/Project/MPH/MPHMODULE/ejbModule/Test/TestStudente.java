package Test;

import static Test.ManagerInizializzazioneDatabaseRemote.*;
import static org.junit.Assert.*;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entityBean.Professore;
import entityBean.Progetto;
import entityBean.Studente;

import sessionBean.CreaProgettoRemote;
import sessionBean.ManagerAmministratoreRemote;
import sessionBean.ManagerProfessoreRemote;
import sessionBean.RegistrazioneStudenteRemote;
import util.ContextUtil;

public class TestStudente {

	private static InitialContext ctx = null;
	private static ManagerInizializzazioneDatabaseRemote db = null;
	private static RegistrazioneStudenteRemote managerRegistrazione = null;
	private static ManagerProfessoreRemote managerProfessore = null;
	private static ManagerAmministratoreRemote managerAmministratore = null;
	private static CreaProgettoRemote managerProgetto = null;
	
	private static String[] NOME_PROGETTO_PROF1 = {"Progetto Prova1", "Progetto Prova2" };
	private static String[] NOME_PROGETTO_PROF2 = {"Progetto Prova3", "Progetto Prova4" };
	private static String DESCR_PROGETTO = "Descrizione Progetto Prova";
	private static String CORSO_PROGETTO = "Corso Progetto Prova";
	private static String NOME_RELEASE[] = {"Release1", "Release2", "Release3"};
	private static String DATA_DEADLINE[] = {"28/01/2012", "30/03/2012", "25/04/2012"};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ctx = ContextUtil.getInitialContext();
	}
	
	@Before
	public void setUp() throws Exception
	{
		managerRegistrazione = (RegistrazioneStudenteRemote) 
				PortableRemoteObject.narrow(
						ctx.lookup("RegistrazioneStudente/remote"), 
						RegistrazioneStudenteRemote.class);
		managerProfessore = (ManagerProfessoreRemote) 
				PortableRemoteObject.narrow(
						ctx.lookup("ManagerProfessore/remote"), 
						ManagerProfessoreRemote.class);
		managerAmministratore = (ManagerAmministratoreRemote) 
				PortableRemoteObject.narrow(
						ctx.lookup("ManagerAmministratore/remote"), 
						ManagerAmministratoreRemote.class);
		db = (ManagerInizializzazioneDatabaseRemote) 
				PortableRemoteObject.narrow(
						ctx.lookup("ManagerInizializzazioneDatabase/remote"), 
						ManagerInizializzazioneDatabaseRemote.class);
		managerProgetto = (CreaProgettoRemote) 
				PortableRemoteObject.narrow(
						ctx.lookup("CreaProgetto/remote"), 
						CreaProgettoRemote.class);
		db.pulisci();
		db.creaProfAndProgetti();
	}
	
	@Test
	public void testRegistrazioneCreaGruppo() {
		
		try
		{
			managerRegistrazione.creaStudente(USR_STUDENTI_PRED[0], PASSWORD_UTENTI_PREDEFINITA[0], PASSWORD_UTENTI_PREDEFINITA[0], NOME_STUD_PRED[0], COGNOME_UTENTI_PREDEFINITI[0], EMAIL_UTENTI_PREDEFINITI[0], MATR_STUDENTI_PREDEFINITI[0]);
			managerRegistrazione.creaGruppo(NOME_GRUPPI_PRED[0], false, null);
			managerRegistrazione.scegliProgetto(NOME_PROGETTO_PROF1[0]);
			managerRegistrazione.finalizza();
		}
		catch(Exception e)
		{
			fail("La registrazione non deve fallire!");
		}
	}
	
	/*@Test
	public void testRegistrazioneAggiungiAGruppo() {
		
		try
		{
			managerRegistrazione.creaStudente(USR_STUDENTI_PRED[2], PASSWORD_UTENTI_PREDEFINITA[2], PASSWORD_UTENTI_PREDEFINITA[2], NOME_STUD_PRED[2], COGNOME_UTENTI_PREDEFINITI[2], EMAIL_UTENTI_PREDEFINITI[2], MATR_STUDENTI_PREDEFINITI[2]);
			managerRegistrazione.creaGruppo(NOME_GRUPPI_PRED[0], false, null);
			managerRegistrazione.scegliProgetto(NOME_PROGETTO_PROF1[0]);
			managerRegistrazione.finalizza();
			Studente s = managerRegistrazione.creaStudente(USR_STUDENTI_PRED[1], PASSWORD_UTENTI_PREDEFINITA[1], PASSWORD_UTENTI_PREDEFINITA[1], NOME_STUD_PRED[0], COGNOME_UTENTI_PREDEFINITI[1], EMAIL_UTENTI_PREDEFINITI[1], MATR_STUDENTI_PREDEFINITI[1]);
			managerRegistrazione.aggiungiAGruppo(s, NOME_GRUPPI_PRED[0]);
			managerRegistrazione.finalizza();
		}
		catch(Exception e)
		{
			fail("La registrazione non deve fallire!");
		}
	}*/

}
