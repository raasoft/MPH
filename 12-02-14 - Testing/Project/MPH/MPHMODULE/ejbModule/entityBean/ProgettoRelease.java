package entityBean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({
	
	@NamedQuery(
			name = "ProgettoRelease.getById",
			query = "SELECT p FROM ProgettoRelease p WHERE p.id = :id")
})

@Entity
public class ProgettoRelease implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3612192214434121868L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(cascade ={CascadeType.ALL})
	private ProgettoGruppo idProgettoGruppo;
	
	@ManyToOne(cascade ={CascadeType.PERSIST, CascadeType.MERGE})
	private Releas idRelease;
	
	@ManyToMany(cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(name = "release_condivisa",
			   joinColumns = @JoinColumn(name = "id"), 
			   inverseJoinColumns = @JoinColumn(name = "nome"))
	private Set<Gruppo> gruppi;
	
	@OneToMany(mappedBy ="progettorelease",cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<FileRelease> files;
	
	@Column(precision = 1)
	private float votoParziale;
	private int penalita;
	private Date dataUpload;
	
	public ProgettoRelease()
	{
		super();
		files = new HashSet<FileRelease>();
		gruppi = new HashSet<Gruppo>();
	}

	public ProgettoGruppo getIdProgettoGruppo() {
		return idProgettoGruppo;
	}

	public void setIdProgettoGruppo(ProgettoGruppo idProgettoGruppo) {
		this.idProgettoGruppo = idProgettoGruppo;
	}

	public Releas getIdRelease() {
		return idRelease;
	}

	public void setIdRelease(Releas idRelease) {
		this.idRelease = idRelease;
	}

	public float getVotoParziale() {
		return votoParziale;
	}

	public void setVotoParziale(float votoParziale) {
		this.votoParziale = votoParziale;
	}

	public int getPenalita() {
		return penalita;
	}

	public void setPenalita(int penalita) {
		this.penalita = penalita;
	}

	public Date getDataUpload() {
		return dataUpload;
	}

	public void setDataUpload(Date dataUpload) {
		this.dataUpload = dataUpload;
	}

	public Long getId() {
		return id;
	}

	public Set<Gruppo> getGruppi() {
		return gruppi;
	}

	public void setGruppi(Set<Gruppo> gruppi) {
		this.gruppi = gruppi;
	}

	public Set<FileRelease> getFiles() {
		return files;
	}

	public void setFiles(Set<FileRelease> files) {
		this.files = files;
	}
	
	public void addGruppoCond(Gruppo g) {
		this.gruppi.add(g);
	}

	public void removeFile(FileRelease f) {
		// TODO Auto-generated method stub
		this.files.remove(f);
	}

}
