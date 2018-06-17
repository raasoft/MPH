package sessionBean;

import javax.ejb.Local;

import entityBean.Professore;
import exception.TuplaNonTrovataException;

@Local
public interface ManagerProfessoreLocal {
	
	public Professore getProfessoreByUsername(String username) throws TuplaNonTrovataException;

}
