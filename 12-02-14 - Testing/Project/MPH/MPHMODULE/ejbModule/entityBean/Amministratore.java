/**
 * 
 */
package entityBean;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	
	@NamedQuery(
			name="Amministratore.getAmministratoreByUsername",
			query="SELECT a FROM Amministratore a WHERE a.username = :username"),
	@NamedQuery(
			name = "Amministratore.getGruppi",
			query = "SELECT g FROM Gruppo g"),
	@NamedQuery(
			name="Amministratore.getGruppoByNome",
			query="SELECT g FROM Gruppo g WHERE g.nome = :nome"),
})

/**
 *
 *
 */
@Entity
public class Amministratore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1678634676298901539L;
	
	/**
	 * Identificativo univoco dell'amministratore
	 */
	@Id
	private String username;

	/**
	 * Password utilizzata dall'amministratore per accedere al sistema
	 */
	private String password;
	
	/**
	 * Nome dell'amministratore
	 */
	private String nome;

	/**
	 * Cognome dell'amministratore
	 */
	private String cognome;

	/**
	 * e-mail dell'amministratore
	 */
	private String email;

	public String getUsername()
	{
		return username;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Amministratore() {
		super();
	}

}
