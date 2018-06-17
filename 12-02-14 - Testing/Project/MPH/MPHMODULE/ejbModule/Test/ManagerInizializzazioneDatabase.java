package Test;

import java.text.ParseException;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBean.Amministratore;
import entityBean.FileRelease;
import entityBean.Gruppo;
import entityBean.Professore;
import entityBean.Progetto;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Releas;
import entityBean.Studente;

@Stateless(name="ManagerInizializzazioneDatabase")
@Remote(ManagerInizializzazioneDatabaseRemote.class)
public class ManagerInizializzazioneDatabase implements
		ManagerInizializzazioneDatabaseRemote {

	private static String[] NOME_PROGETTO_PROF1 = {"Progetto Prova1", "Progetto Prova2" };
	private static String[] NOME_PROGETTO_PROF2 = {"Progetto Prova3", "Progetto Prova4" };
	private static String DESCR_PROGETTO = "Descrizione Progetto Prova";
	private static String CORSO_PROGETTO = "Corso Progetto Prova";
	private static String NOME_RELEASE[] = {"Release1", "Release2", "Release3"};
	private static String DATA_DEADLINE[] = {"28/01/2012", "30/03/2012", "25/04/2012"};
	
	
	@PersistenceContext(unitName="mph") 
	private EntityManager em;
	
	private static final String NOME_ENTITIES[] = {
		"Studente", "Professore", "Amministratore", "FileRelease", 
		"Gruppo", "Progetto", "ProgettoGruppo", "ProgettoRelease", "Releas"
	};
	
	@SuppressWarnings("unchecked")
	@Override
	public void pulisci() {
		// TODO Auto-generated method stub
		for(Professore p : (ArrayList<Professore>) em.createNamedQuery("Professore.getProfessori").getResultList())
		{
			for(Progetto pr : p.getProgetti())
			{
				for(ProgettoGruppo pg : pr.getProgettigruppo())
				{
					for(ProgettoRelease prel : pg.getListaRelease())
					{
						for(FileRelease f : prel.getFiles())
							em.remove(f);
						em.remove(prel);
					}
					//pg.setListaRelease(null);
					//pg.setGruppo(null);
					em.remove(pg);
				}
				for(Releas r : pr.getListaRelease())
					em.remove(r);
				em.remove(pr);
			}
			em.remove(p);
		}
		//em.flush();
		for(Studente s: (ArrayList<Studente>) em.createNamedQuery("Studente.getAll").getResultList())
		{
			for(Gruppo g: (ArrayList<Gruppo>) em.createNamedQuery("Gruppo.getGruppi").getResultList())
			{
				if(g.getStudenti() != null)
				{
					boolean ctrl = false;
					for(Studente s1 : g.getStudenti())
						if(s1.getUsername().compareTo(s.getUsername()) == 0)
							ctrl = true;
					if(ctrl)
					{
						ctrl = false;
						g.setStudenti(null);
						em.merge(g);
					}
				}
			}
		}
		em.flush();
		/*for(Gruppo g: (ArrayList<Gruppo>) em.createNamedQuery("Gruppo.getGruppi").getResultList())
			em.remove(g);
		em.flush();*/
		//em.createQuery("DELETE " + "Studente" + " o").executeUpdate();
		for(String nome: NOME_ENTITIES) {
			em.createQuery("DELETE " + nome + " o").executeUpdate();			   
		}

	}

	@Override
	public void creaDatiProfessore() {
		// TODO Auto-generated method stub
		for(int i = 0; i < NUMERO_ADMIN; i++) {
			Amministratore a = new Amministratore();
			a.setUsername(USR_ADMIN_PRED[i]);
			a.setNome(NOME_ADMIN_PRED[i]);
			a.setCognome(COGNOME_UTENTI_PREDEFINITI[i]);
			a.setPassword(PASSWORD_UTENTI_PREDEFINITA[i]);
			a.setEmail(EMAIL_UTENTI_PREDEFINITI[i]);
			em.persist(a);
		}
		for(int i = 0; i < NUMERO_PROFESSORI; i++) {
			Professore p = new Professore();
			p.setUsername(USR_PROFRSSORI_PRED[i]);
			p.setNome(NOME_PROF_PRED[i]);
			p.setCognome(COGNOME_UTENTI_PREDEFINITI[i]);
			p.setPassword(PASSWORD_UTENTI_PREDEFINITA[i]);
			p.setEmail(EMAIL_UTENTI_PREDEFINITI[i]);
			p.setTelefono(TEL_UTENTI_PREDEFINITI[i]);
			em.persist(p);
		}
		
		/*
		 * 
			Set<Progetto>
			Set<Releas> release = new HashSet<Releas>();
			for(int y = 0; y < NUMERO_RELEASE; y++)
			{
				Releas r = new Releas()
			}
		 */
	}

	@Override
	public void creaProfAndProgetti() throws ParseException 
	{
		for(int i = 0; i < NUMERO_ADMIN; i++) {
			Amministratore a = new Amministratore();
			a.setUsername(USR_ADMIN_PRED[i]);
			a.setNome(NOME_ADMIN_PRED[i]);
			a.setCognome(COGNOME_UTENTI_PREDEFINITI[i]);
			a.setPassword(PASSWORD_UTENTI_PREDEFINITA[i]);
			a.setEmail(EMAIL_UTENTI_PREDEFINITI[i]);
			em.persist(a);
		}
		for(int i = 0; i < NUMERO_PROFESSORI; i++) 
		{
			Professore p = new Professore();
			p.setUsername(USR_PROFRSSORI_PRED[i]);
			p.setNome(NOME_PROF_PRED[i]);
			p.setCognome(COGNOME_UTENTI_PREDEFINITI[i]);
			p.setPassword(PASSWORD_UTENTI_PREDEFINITA[i]);
			p.setEmail(EMAIL_UTENTI_PREDEFINITI[i]);
			p.setTelefono(TEL_UTENTI_PREDEFINITI[i]);
			em.persist(p);
			for(int y = 0; y < NOME_PROGETTO_PROF1.length;y++)
			{
				Progetto pr = new Progetto();
				pr.setDescrizione(DESCR_PROGETTO);
				if(i == 0)
					pr.setNome(NOME_PROGETTO_PROF1[y]);
				else
					pr.setNome(NOME_PROGETTO_PROF2[y]);
				pr.setMateria(CORSO_PROGETTO);
				pr.setProfessore(p);
				Set<Releas> listaRelease = new HashSet<Releas>();
				//em.persist(pr);
				for(int x = 0; x < NOME_RELEASE.length; x++)
				{
					Releas r = new Releas();
					r.setTipo(TIPO_RELEASE_PRED[x]);
					r.setConsegnabile(true);
					r.setDeadline(DATA_DEADLINE[x]);
					r.setProgetto(pr);
					//listaRelease.add(r);
					em.persist(r);
				}
				//pr.setListaRelease(listaRelease);
				em.persist(pr);
			}
		}
	}

}
