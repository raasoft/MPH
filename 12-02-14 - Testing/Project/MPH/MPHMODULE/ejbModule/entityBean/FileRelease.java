package entityBean;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	
	@NamedQuery(
			name = "FileRelease.getById",
			query = "SELECT r FROM FileRelease r WHERE r.id = :id")
})

@Entity
public class FileRelease implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1107596039600762290L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne//(cascade ={CascadeType.PERSIST, CascadeType.MERGE})
	private ProgettoRelease progettorelease;
	
	private String file;
	private String Descrizione;
	
	public FileRelease() {
		super();
		
	}
	
	public Long getId() {
		return id;
	}
	
	public ProgettoRelease getProgettorelease() {
		return progettorelease;
	}
	
	public void setProgettorelease(ProgettoRelease progettorelease) {
		this.progettorelease = progettorelease;
	}
	
	public String getFile() {
		return file;
	}
	
	public void setFile(String file) {
		this.file = file;
	}
	
	public String getDescrizione() {
		return Descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
	
	
}
