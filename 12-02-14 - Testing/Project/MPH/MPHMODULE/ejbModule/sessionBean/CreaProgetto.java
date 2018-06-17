package sessionBean;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBean.Professore;
import entityBean.Progetto;
import entityBean.Releas;
import exception.CampiNonCompletiException;
import exception.ProgettoEsistenteException;
import exception.TuplaNonTrovataException;

@Stateful
@Remote(CreaProgettoRemote.class)
public class CreaProgetto implements CreaProgettoRemote {

	@PersistenceContext(unitName="mph")
	private EntityManager em;
	private Progetto progetto = null;
	private List<Releas> listarelease = null;
	@EJB
	private ManagerProfessoreLocal manager;
	
	@Override
	public List<Releas> aggiungiRelease(String tipo, String data) throws CampiNonCompletiException {
		// TODO Auto-generated method stub
		if((tipo.compareTo("") == 0) || (data.compareTo("") == 0))
			throw new CampiNonCompletiException();
		if(listarelease == null)
			listarelease = new ArrayList<Releas>();
		/*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date deadline = formatter.parse(data);*/
		Releas release = null;
		try
		{
			release = new Releas(tipo, data);
			em.persist(release);
			em.remove(release);
			listarelease.add(release);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		return listarelease;
	}

	@Override
	public List<Releas> rimuoviRelease(String id) throws TuplaNonTrovataException {
		// TODO Auto-generated method stub
		for(Releas r : listarelease)
		{
			if(r.getId().compareTo(Long.valueOf(id)) == 0)
				listarelease.remove(r);
		}
		return listarelease;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Progetto creaProgetto(String nome, String descrizione, String materia, String username)
			throws ProgettoEsistenteException, TuplaNonTrovataException, CampiNonCompletiException {
		// TODO Auto-generated method stub
		if((nome.compareTo("") == 0) || (descrizione.compareTo("") == 0) || (materia.compareTo("") == 0))
			throw new CampiNonCompletiException();
		List<Progetto> lista = em.createNamedQuery("Progetto.getByNome").setParameter("nome", nome).getResultList();
		if(lista.size() > 0)
			throw new ProgettoEsistenteException();
		progetto = new Progetto();
		progetto.setNome(nome);
		progetto.setDescrizione(descrizione);
		progetto.setMateria(materia);
		Professore professore = manager.getProfessoreByUsername(username);
		progetto.setProfessore(professore);
		return progetto;
	}
	
	@Override
	public void finalizzaProgetto()
	{
		for(Releas r : listarelease)
		{
			r.setProgetto(progetto);
			Releas r1 = null;
			try
			{
				r1 = new Releas(r.getTipo(), r.getDeadline());
				r1.setProgetto(progetto);
				r1.setConsegnabile(true);
				//progetto.addRelease(r);
				em.persist(r1);
			}
			catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		em.persist(progetto);
	}
	
	public void annulla()
	{
		progetto = null;
		listarelease = null;
	}

	@Remove
	public void remove()
	{
		
	}

}
