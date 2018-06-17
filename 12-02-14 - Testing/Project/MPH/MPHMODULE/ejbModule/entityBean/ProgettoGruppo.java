package entityBean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({
	
	@NamedQuery(
			name = "ProgettoGruppo.getById",
			query = "SELECT p FROM ProgettoGruppo p WHERE p.id = :id")
})

@Entity
public class ProgettoGruppo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9153313438292312035L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(precision = 1)
	private float votoFinale;

	@ManyToOne(cascade ={CascadeType.ALL})
	private Gruppo gruppo2;
	
	@ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE})
	private Progetto progetto;
	
	@OneToMany(mappedBy = "idProgettoGruppo",cascade ={CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
	private Set<ProgettoRelease> listaRelease;
	

	public ProgettoGruppo() {
		super();
		listaRelease = new HashSet<ProgettoRelease>();
	}

	public Long getId() {
		return id;
	}

	public Gruppo getGruppo() {
		return gruppo2;
	}

	public void setGruppo(Gruppo gruppo) {
		this.gruppo2 = gruppo;
	}

	public Progetto getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetto progetto) {
		this.progetto = progetto;
	}
	
	public float getVotoFinale() {
		return votoFinale;
	}

	public void setVotoFinale(float votoFinale) {
		this.votoFinale = votoFinale;
	}

	public Set<ProgettoRelease> getListaRelease() {
		return listaRelease;
	}
	
	public void removeRelease(ProgettoRelease pr) {
		this.listaRelease.remove(pr);
	}

	public void setListaRelease(Set<ProgettoRelease> listaRelease) {
		this.listaRelease = listaRelease;
	}
	
}
