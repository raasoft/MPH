package Test;

import static org.junit.Assert.*;

import javax.naming.InitialContext;
import javax.rmi.PortableRemoteObject;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import entityBean.Professore;
import entityBean.Progetto;

import sessionBean.CreaProgettoRemote;
import sessionBean.ManagerAmministratoreRemote;
import sessionBean.ManagerProfessoreRemote;
import util.ContextUtil;

import static Test.ManagerInizializzazioneDatabaseRemote.USR_PROFRSSORI_PRED;

public class TestProfessore {

	private static InitialContext ctx = null;
	private static ManagerInizializzazioneDatabaseRemote db = null;
	private static CreaProgettoRemote managerProgetto = null;
	private static ManagerProfessoreRemote managerProfessore = null;
	private static ManagerAmministratoreRemote managerAmministratore = null;
	
	private static String[] NOME_PROGETTO_PROF1 = {"Progetto Prova1", "Progetto Prova2" };
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
		managerProgetto = (CreaProgettoRemote) 
				PortableRemoteObject.narrow(
						ctx.lookup("CreaProgetto/remote"), 
						CreaProgettoRemote.class);
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
		
		db.pulisci();
		db.creaDatiProfessore();
	}
	
	@Test
	public void testCreaProgetto() {
		try
		{
			managerProgetto.creaProgetto(NOME_PROGETTO_PROF1[0], DESCR_PROGETTO, CORSO_PROGETTO, USR_PROFRSSORI_PRED[0]);
			managerProgetto.aggiungiRelease(NOME_RELEASE[0], DATA_DEADLINE[0]);
			managerProgetto.aggiungiRelease(NOME_RELEASE[1], DATA_DEADLINE[1]);
			managerProgetto.finalizzaProgetto();
			managerProgetto.remove();
			for(Professore p : managerAmministratore.getListaProfessori())
			{
				for(Progetto pr : managerProfessore.getProgetti(p))
						assertTrue("Il progetto deve avere almeno una release", pr.getListaRelease().size() > 0);
			}
		}
		catch(Exception e)
		{
			fail("La creazione del progetto non dovrebbe fallire");
		}
	}

}
