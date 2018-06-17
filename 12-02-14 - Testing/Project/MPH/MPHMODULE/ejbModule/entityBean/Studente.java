package entityBean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.IndexColumn;

@NamedQueries({
	
	@NamedQuery(
			name="Studente.getStudenteByUsername",
			query="SELECT s FROM Studente s WHERE s.username = :username"),
	@NamedQuery(
			name = "Studente.getGruppi",
			query = "SELECT s.gruppi FROM Studente s WHERE s.username = :username"),
	@NamedQuery(
			name = "Studente.getAll",
			query = "SELECT s FROM Studente s"),
	@NamedQuery(
			name = "Studente.getStudenteByMatricola",
			query = "SELECT s FROM Studente s WHERE s.matricola = :matricola")
})

@Entity
public class Studente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8388124483121916565L;
	
	/**
	 * Identificativo univoco dello studente
	 */
	@Id
	private String username;

	/**
	 * Password utilizzata dallo studente per accedere al sistema
	 */
	private String password;

	/**
	 * Numero di matricola dello studente
	 */
	private String matricola;

	/**
	 * Nome dello studente
	 */
	private String nome;

	/**
	 * Cognome dello studente
	 */
	private String cognome;

	/**
	 * e-mail dello studente
	 */
	private String email;
	
	@ManyToMany(mappedBy = "studenti",cascade ={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private Set<Gruppo> gruppi;
	
	public Studente() {
		super();
	}

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

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
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

	public Set<Gruppo> getGruppi() {
		return gruppi;
	}

	public void setGruppi(Set<Gruppo> gruppi) {
		this.gruppi = gruppi;
	}

}
