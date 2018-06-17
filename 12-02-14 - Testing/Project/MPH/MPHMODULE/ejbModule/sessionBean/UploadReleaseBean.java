package sessionBean;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import util.NameUtil;

import entityBean.FileRelease;
import entityBean.ProgettoGruppo;
import entityBean.ProgettoRelease;
import entityBean.Releas;
import exception.TuplaNonTrovataException;

/**
 * Session Bean implementation class UploadRelase
 */
@Stateful
public class UploadReleaseBean implements UploadReleaseBeanRemote {

	@PersistenceContext(unitName="mph")
	private EntityManager em;
	ProgettoRelease release = null;
	Set<FileRelease> listaFile = null;
	String idpr = null;
	
    /**
     * Default constructor. 
     */
    public UploadReleaseBean() 
    {
    	
    }

	@SuppressWarnings("unchecked")
	@Override
	public void creaRelease(String id, String idProg) throws TuplaNonTrovataException 
	{
		idpr = idProg;
		if(release == null)
		{
			release = new ProgettoRelease();
			List<Releas> listaRelease = em.createNamedQuery("Releas.getReleaseById").setParameter("id", Long.valueOf(id)).getResultList();
			List<ProgettoGruppo> pg = em.createNamedQuery("ProgettoGruppo.getById").setParameter("id", Long.valueOf(idProg)).getResultList();
			if(listaRelease.size() == 0)
				throw new TuplaNonTrovataException();
			release.setIdRelease(listaRelease.get(0));
			if(pg.size() == 0)
				throw new TuplaNonTrovataException();
			release.setIdProgettoGruppo(pg.get(0));
			release.setVotoParziale(0);
		}
	}

	@Override
	public void uploadFile(File file, String nome, String descrizione, String path) 
	{
		if(listaFile == null)
			listaFile = new HashSet<FileRelease>();
		FileRelease fileR = new FileRelease();
		fileR.setDescrizione(descrizione);
		File dir = new File(path + idpr + "/");
		if(!dir.exists())
			dir.mkdirs();
		File file2 = new File(dir, NameUtil.encode(nome));
		file.renameTo(file2);
		fileR.setFile("mph/" + idpr + "/" + NameUtil.encode(nome));
		//file.delete();
		listaFile.add(fileR);
	}
	
	@Override
	public void finalizza() throws ParseException
	{
		GregorianCalendar gc = new GregorianCalendar();
		Date deadline = release.getIdRelease().getDeadlineAsDate();
		Date current = gc.getTime();
		/* se la data di upload è successiva a quella della deadline
		 * si setta la penalità (da studiare)
		 */
		 if(current.after(deadline))
		 {
			 GregorianCalendar dallaData = new GregorianCalendar();
			 GregorianCalendar allaData = new GregorianCalendar();
			 dallaData.setTime(deadline);
			 allaData.setTime(current);
			 long dallaDataMilliSecondi = dallaData.getTimeInMillis();
			 long allaDataMilliSecondi = allaData.getTimeInMillis();
			 Long distTra = allaDataMilliSecondi - dallaDataMilliSecondi;
			 double giorni = Math.round( distTra / 86400000.0 );
			 if(giorni > 6)
				 release.setPenalita(6);
			 else
				 release.setPenalita((int)giorni);
		 }
		release.setDataUpload(current);
		/*
		 * qui la persist dei singoli file caricati.
		 */
		release.setFiles(listaFile);
		for(FileRelease f : listaFile)
		{
			f.setProgettorelease(release);
		}
		em.merge(release);
		
	}
	
	@Override
	@Remove
	public void remove()
	{
		
	}

}
