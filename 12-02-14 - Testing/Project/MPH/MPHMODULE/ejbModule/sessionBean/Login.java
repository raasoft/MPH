package sessionBean;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityBean.Amministratore;
import entityBean.Professore;
import entityBean.Studente;
import exception.ErroreLoginException;

@Stateless
@Remote(LoginRemote.class)
public class Login implements LoginRemote {

	@PersistenceContext(unitName="mph")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public Object login(String username, String password) throws ErroreLoginException {
		
		List<Professore> professori = em.createNamedQuery("Professore.getProfessoreByUsername").setParameter("username", username).getResultList();
		if ((professori.size() > 0) && (professori.get(0).getPassword().compareTo(password)) == 0)
			return professori.get(0);
		List<Amministratore> amministratori = em.createNamedQuery("Amministratore.getAmministratoreByUsername").setParameter("username", username).getResultList();
		if ((amministratori.size() > 0) && (amministratori.get(0).getPassword().compareTo(password)) == 0)
			return amministratori.get(0);
		List<Studente> studenti = em.createNamedQuery("Studente.getStudenteByUsername").setParameter("username", username).getResultList();
		if ((studenti.size() > 0) && (studenti.get(0).getPassword().compareTo(password)) == 0)
			return studenti.get(0);
		throw new ErroreLoginException("<script type='text/javascript'>TINY.box.show('<center><h1 style=color:#0d66b5>Login errato</h1></center>Username/password potrebbero non essere corretti.',0,0,0,0)</script>");
	}
}
