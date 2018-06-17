package entityBean;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
			name = "Releas.getReleaseById",
			query = "SELECT r FROM Releas r WHERE r.id = :id")
})

@Entity
public class Releas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3471352341534418715L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne//(cascade ={CascadeType.ALL})
	private Progetto progetto2;
	
	@OneToMany(mappedBy = "idRelease",cascade ={CascadeType.ALL}, fetch = FetchType.EAGER)
	private Set<ProgettoRelease> listaRelease;
	
	private String tipo;
	private Date deadline;
	private boolean consegnabile;
	
	public Releas() {
		super();
		listaRelease = new HashSet<ProgettoRelease>();
	}
	public Releas(String tipo, String deadline) throws ParseException {
		this.tipo = tipo;
		SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
		this.deadline = parser.parse(deadline);
		//this.deadline = deadline;
	}
	public Progetto getProgetto() {
		return progetto2;
	}
	public void setProgetto(Progetto progetto2) {
		this.progetto2 = progetto2;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDeadline() {
		String data = "";
		data = data + String.valueOf(deadline.getDate()) + "/" + String.valueOf(deadline.getMonth() + 1) + "/" + String.valueOf(deadline.getYear() + 1900);
		return data;
	}
	public void setDeadline(String deadline) throws ParseException {
		SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");
		this.deadline = parser.parse(deadline);
		//this.deadline = deadline;
	}
	public Date getDeadlineAsDate() throws ParseException {
		return deadline;
	}
	public Long getId() {
		return id;
	}
	public String getIdAsString()
	{
		return String.valueOf(id);
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Set<ProgettoRelease> getListaRelease() {
		return listaRelease;
	}
	public void setListaRelease(Set<ProgettoRelease> listaRelease) {
		this.listaRelease = listaRelease;
	}
	public boolean isConsegnabile() {
		return consegnabile;
	}
	public void setConsegnabile(boolean consegnabile) {
		this.consegnabile = consegnabile;
	}

}
