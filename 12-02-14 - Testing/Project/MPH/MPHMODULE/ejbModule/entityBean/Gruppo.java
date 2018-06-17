package entityBean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({
	
	@NamedQuery(
			name = "Gruppo.getGruppi",
			query = "SELECT g FROM Gruppo g"),
	@NamedQuery(
			name = "Gruppo.getGruppiByStudente",
			query = "SELECT g FROM Gruppo g WHERE :s IN g.studenti"),
	@NamedQuery(
			name = "Gruppo.getGruppoByNome",
			query = "SELECT g FROM Gruppo g WHERE g.nome = :nome")
})

@Entity
public class Gruppo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5034921683278571592L;
	
	@Id
	private String nome;
	
	private boolean control;
	
	public boolean isControl() {
		return control;
	}

	public void setControl(boolean control) {
		this.control = control;
	}

	@ManyToMany(cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "studente_gruppo", 
			   joinColumns=@JoinColumn(name = "nome"), 
			   inverseJoinColumns = @JoinColumn(name = "username"))
	private Set<Studente> studenti;
	
	@OneToMany(mappedBy = "gruppo2",cascade ={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private Set<ProgettoGruppo> progettigruppo;
	
	@ManyToMany(mappedBy = "gruppi",cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<ProgettoRelease> releaseCondivise;

	public Gruppo() {
		super();
		studenti = new HashSet<Studente>();
		releaseCondivise = new HashSet<ProgettoRelease>();
	}

	public Gruppo(String nome, Studente studente)
	{
		this.nome = nome;
		this.studenti = new HashSet<Studente>();
		this.studenti.add(studente);
	}
	
	public String getNome()
	{
		return nome;
	}
	
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	
	public Set<Studente> getStudenti()
	{
		return studenti;
	}
	
	public void setStudenti(Set<Studente> studenti)
	{
		this.studenti = studenti;
	}
	
	public boolean addStudente(Studente studente)
	{
		if(studenti.size() < 3)
		{
			studenti.add(studente);
			return true;
		}
		else
			return false;
	}
	
	public void removeStudente(Studente s)
	{
		this.studenti.remove(s);
	}
	
	public void removeProgettoGruppo(ProgettoGruppo pg)
	{
		this.progettigruppo.remove(pg);
	}
	
	public Set<ProgettoGruppo> getProgettigruppo() {
		return progettigruppo;
	}

	public void setProgettigruppo(Set<ProgettoGruppo> progettigruppo) {
		this.progettigruppo = progettigruppo;
	}

	public Set<ProgettoRelease> getReleaseCondivise() {
		return releaseCondivise;
	}

	public void setReleaseCondivise(Set<ProgettoRelease> releaseCondivise) {
		this.releaseCondivise = releaseCondivise;
	}
	
	public void addReleaseCondivisa(ProgettoRelease releaseCondivisa) {
		this.releaseCondivise.add(releaseCondivisa);
	}
	
	public void removeReleaseCondivisa(ProgettoRelease releaseCondivisa) {
		this.releaseCondivise.remove(releaseCondivisa);
	}
	
	//Ultimo metodo aggiunto
	public boolean isCondivisa(ProgettoRelease rel){
		for (ProgettoRelease r: this.getReleaseCondivise())
			if (r.getId().compareTo(rel.getIdRelease().getId()) == 0)
				return true;
		return false;
	}
	
	

}
