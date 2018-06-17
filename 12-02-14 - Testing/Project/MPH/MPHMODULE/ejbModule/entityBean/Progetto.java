package entityBean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@NamedQueries({
	
	@NamedQuery(
			name = "Progetto.getProgettiByProfessore",
			query = "SELECT p FROM Progetto p WHERE p.professore = :professore"),
	@NamedQuery(
			name = "Progetto.getByNome",
			query = "SELECT p FROM Progetto p WHERE p.nome = :nome"),
	@NamedQuery(
			name = "Progetto.getAll",
			query = "SELECT p FROM Progetto p")
})

@Entity
public class Progetto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4448100715953643902L;
	
	@Id
	private String nome;
	private String materia;
	private String descrizione;
	
	@OneToMany(mappedBy = "progetto",cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<ProgettoGruppo> progettigruppo;
	
	@OneToMany(mappedBy = "progetto2",cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<Releas> listaRelease;
	
	@ManyToOne//(cascade ={CascadeType.ALL})
	private Professore professore;
	
	public Progetto() {
		super();
		listaRelease = new HashSet<Releas>();
	}
	public Professore getProfessore() {
		return professore;
	}
	public void setProfessore(Professore professore) {
		this.professore = professore;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public Set<ProgettoGruppo> getProgettigruppo() {
		return progettigruppo;
	}
	public void setProgettigruppo(Set<ProgettoGruppo> progettigruppo) {
		this.progettigruppo = progettigruppo;
	}
	public Set<Releas> getListaRelease() {
		return listaRelease;
	}
	public void setListaRelease(Set<Releas> listaRelease) {
		this.listaRelease = listaRelease;
	}
	public void addRelease(Releas r)
	{
		this.listaRelease.add(r);
	}
	public boolean controllaProg(List<ProgettoGruppo> prog){
		for (ProgettoGruppo pg : prog)
			if (pg.getProgetto().getNome().compareTo(this.nome) == 0)
				return true;
		return false;
	}
	public boolean controllaProgSet(Set<ProgettoGruppo> prog){
		for (ProgettoGruppo pg : prog)
			if (pg.getProgetto().getNome().compareTo(this.nome) == 0)
				return true;
		return false;
	}
}
