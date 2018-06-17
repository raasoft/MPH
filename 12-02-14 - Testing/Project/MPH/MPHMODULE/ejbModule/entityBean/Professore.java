/**
 * 
 */
package entityBean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NamedQueries({
	
	@NamedQuery(
			name = "Professore.getProfessoreByUsername",
			query = "SELECT p FROM Professore p WHERE p.username = :username"),
	@NamedQuery(
			name = "Professore.getProfessori",
			query = "SELECT p FROM Professore p")
})

@Entity
public class Professore implements Serializable {

	
	private static final long serialVersionUID = -1910231395676050489L;

	/**
	 * Identificativo univoco del professore
	 */
	@Id
	private String username;

	/**
	 * Password utilizzata dal professore per accedere al sistema
	 */
	private String password;

	/**
	 * Numero di telefono del professore
	 */
	private String telefono;

	/**
	 * Nome del professore
	 */
	private String nome;

	/**
	 * Cognome del professore
	 */
	private String cognome;

	/**
	 * e-mail del professore
	 */
	private String email;
	
	
	@OneToMany(mappedBy="professore",cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<Progetto> progetti;
	
	public Set<Progetto> getProgetti() {
		return progetti;
	}


	public void setProgetti(Set<Progetto> progetti) {
		this.progetti = progetti;
	}

	public void aggiungiProgetto(Progetto progetto)
	{
		this.progetti.add(progetto);
	}

	public Professore()
	{
		super();
	}
	
	public Professore(String nome,
			  String cognome,
			  String username,
			  String telefono,
			  String email,
			  String password)
	{
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
}
