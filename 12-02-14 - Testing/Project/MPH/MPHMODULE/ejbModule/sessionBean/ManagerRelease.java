package sessionBean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBean.FileRelease;
import entityBean.Gruppo;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Releas;
import exception.TuplaNonTrovataException;

/**
 * Session Bean implementation class ManagerFileRelease
 */
@Stateless
@Remote(ManagerReleaseRemote.class)
public class ManagerRelease implements ManagerReleaseRemote {

	@PersistenceContext(unitName="mph")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ManagerRelease() {
        // TODO Auto-generated constructor stub
    }

	@SuppressWarnings("unchecked")
	@Override
	public FileRelease getById(Long id) throws TuplaNonTrovataException {
		// TODO Auto-generated method stub
		List<FileRelease> lista = em.createNamedQuery("FileRelease.getById").setParameter("id", id).getResultList();
		if(lista.size() > 0)
			return lista.get(0);
		else
			throw new TuplaNonTrovataException();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ProgettoRelease getProgettoRelease(Long id) throws TuplaNonTrovataException {
		// TODO Auto-generated method stub
		List<ProgettoRelease> lista = em.createNamedQuery("ProgettoRelease.getById").setParameter("id", id).getResultList();
		if(lista.size() > 0)
			return lista.get(0);
		else
			throw new TuplaNonTrovataException();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void CondividiRelease(Long idRelease, String nomeGruppo)
			throws TuplaNonTrovataException {
		List<Gruppo> gruppi = em.createNamedQuery("Gruppo.getGruppoByNome").setParameter("nome", nomeGruppo).getResultList();
		List<ProgettoRelease> release = em.createNamedQuery("ProgettoRelease.getById").setParameter("id", idRelease).getResultList();
		if(gruppi.size() > 0 && release.size() > 0)
		{
			Gruppo g = gruppi.get(0);
			ProgettoRelease pr = release.get(0);
			pr.addGruppoCond(g);
			//g.addReleaseCondivisa(pr);
			//em.merge(g);
			em.merge(pr);
			//em.persist(g);
			//em.persist(pr);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void AggiungiReleaseVotoNullo(Long idRelease, Long id) 
			throws TuplaNonTrovataException {
		List<Releas> release = 
				em.createNamedQuery("Releas.getReleaseById").setParameter("id", idRelease).getResultList();
		List<ProgettoGruppo> progetti = em.createNamedQuery("ProgettoGruppo.getById").setParameter("id", id).getResultList();
		if(release.size() <= 0 || progetti.size() <= 0)
			throw new TuplaNonTrovataException();
		ProgettoRelease pr = new ProgettoRelease();
		pr.setIdRelease(release.get(0));
		pr.setIdProgettoGruppo(progetti.get(0));
		pr.setVotoParziale(1);
		em.persist(pr);
	}

}
