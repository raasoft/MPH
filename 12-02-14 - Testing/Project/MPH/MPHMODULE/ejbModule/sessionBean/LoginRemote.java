package sessionBean;

import javax.ejb.Remote;

import exception.ErroreLoginException;

@Remote
public interface LoginRemote {
	
	public Object login(String username, String password) throws ErroreLoginException;

}
