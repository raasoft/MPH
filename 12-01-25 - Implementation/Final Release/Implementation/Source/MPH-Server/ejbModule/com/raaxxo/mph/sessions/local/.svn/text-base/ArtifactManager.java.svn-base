package com.raaxxo.mph.sessions.local;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mph.beans.dto.ArtifactDTO;
import mph.beans.dto.IncomingFileDTO;
import mph.beans.dto.OutcomingFileDTO;
import mph.beans.dto.ids.ArtifactIdDTO;
import mph.beans.dto.ids.DeliverableIdDTO;
import mph.beans.dto.ids.StudentIdDTO;
import mph.beans.dto.ids.TeamIdDTO;
import mph.beans.exceptions.DownloadErrorException;
import mph.beans.exceptions.ForbiddenOperationException;
import mph.beans.exceptions.InvalidArgumentException;
import mph.beans.exceptions.UploadErrorException;
import util.ObjectUtility;

import com.healthmarketscience.rmiio.RemoteInputStreamClient;
import com.raaxxo.mph.entities.Artifact;
import com.raaxxo.mph.entities.Deliverable;
import com.raaxxo.mph.entities.Project;
import com.raaxxo.mph.entities.Team;
import com.raaxxo.mph.entities.classids.ArtifactId;
import com.raaxxo.mph.entities.classids.DeliverableId;


@Stateless
public class ArtifactManager implements ArtifactManagerLocal {
	@PersistenceContext(unitName = "mph") private EntityManager em;

	@EJB ProjectManagerLocal projectManager;
	@EJB EntityUtilityLocal entityUtility;
	@EJB DeliverableManagerLocal deliverableManager;
	@EJB TeamManagerLocal teamManager;

	
	
	@Override
	public boolean existsEntity(ArtifactId theEntityId)
			throws InvalidArgumentException {
		
		Artifact aArtifact = getEntity(theEntityId);

		if (aArtifact  == null) {
			System.out.println("Artifact doesn't exist");
			return false;
		}
		else {
			System.out.println("Artifact exists");
			return true;
		}
		
	}
	@Override
	public void checkEntityExists(ArtifactId theEntityId)
			throws InvalidArgumentException {
		
		if (existsEntity(theEntityId) == false) {
			throw new InvalidArgumentException("The artifact you've searched for ("+ theEntityId + ") doesn't exists");
		}		
		
	}
	@Override
	public Artifact reconstructEntity(ArtifactIdDTO theEntityIdDTO)
			throws InvalidArgumentException {

		ObjectUtility.isNull(theEntityIdDTO);
		
		Deliverable aDeliverable = deliverableManager.reconstructEntity(theEntityIdDTO.getDeliverableId());
		Team aTeam = teamManager.reconstructEntity(theEntityIdDTO.getTeamId());
		
		return getEntity( new ArtifactId(aDeliverable,aTeam) );
		
	}
	@Override
	public Artifact getEntity(ArtifactId theEntityId)
			throws InvalidArgumentException {

		ObjectUtility.isNull(theEntityId);
		
		System.out.println("Trying to get the artifact with id: " + theEntityId);
		
		Artifact anArtifact = (Artifact) entityUtility.findOneEntity(Artifact.class, theEntityId);
		
		if (anArtifact != null)
			System.out.println("Succesfully got the artifact with id: " + theEntityId);
		else
			System.out.println("Can't  find the artifact with id: " + theEntityId);
		
		return anArtifact;
	}
	@Override
	public Set<Artifact> getEntitySet(DeliverableIdDTO theDeliverableIdDTO)
			throws InvalidArgumentException {
		
		Project aProject = projectManager.reconstructEntity(theDeliverableIdDTO.getProjectId());
		projectManager.checkProjectExists(aProject.getId());
		
		Deliverable aDeliverable = deliverableManager.getEntity(new DeliverableId(aProject, theDeliverableIdDTO.getDeliverableName()));
		
		return aDeliverable.getArtifactSet();
	}
	@Override
	public Set<ArtifactIdDTO> getEntityIdDTOSet(DeliverableIdDTO theDeliverableIdDTO)
			throws InvalidArgumentException {
		
		Set<Artifact> anArtifactSet = getEntitySet(theDeliverableIdDTO);
		Set<ArtifactIdDTO> aSet = new HashSet<ArtifactIdDTO>();
		
		for (Artifact anArtifact : anArtifactSet)  {
			aSet.add(anArtifact.getId().getDTO());	
		}
		
		return aSet;
		
	}
	@Override
	public ArtifactIdDTO uploadArtifact(TeamIdDTO theTeamId,
			DeliverableIdDTO theDeliverableId, OutcomingFileDTO theOutcomingFileDTO,
			StudentIdDTO theStudentIdDTO) throws InvalidArgumentException, ForbiddenOperationException, UploadErrorException {
		
		System.out.println("Trying to upload a new artifact: " + theOutcomingFileDTO);
		System.out.println("Perform some checks");

		ObjectUtility.isNull(theTeamId);
		ObjectUtility.isNull(theDeliverableId);
		ObjectUtility.isNull(theOutcomingFileDTO);
		
		System.out.println("Trying to validate team");
		Team aTeam = teamManager.reconstructEntity(theTeamId);
		ObjectUtility.isNull(aTeam);
		
		System.out.println("Trying to validate deliverable");
		Deliverable aDeliverable = deliverableManager.reconstructEntity(theDeliverableId);
		ObjectUtility.isNull(aDeliverable);
		
		System.out.println("Trying to read from the input stream");
		
		InputStream aObjectInputStream;
		try {
			System.out.println("Trying to wrap the input stream");
			aObjectInputStream = RemoteInputStreamClient.wrap(theOutcomingFileDTO.exportInUpload());
		} catch (IOException e1) { throw new UploadErrorException("There was an error trying to upload the file. The file was not uploaded to the server."); }
		ByteArrayOutputStream aStream = new ByteArrayOutputStream();
		
		System.out.println("Now reading the the input stream");
		int c;
		try {
			while ((c = aObjectInputStream.read()) != -1) {
				aStream.write(c);
			}
		} catch (IOException e) { throw new UploadErrorException("There was an error trying to read the uploaded file. The file was not uploaded to the server."); }
		finally { try {
			aStream.close();
		} catch (IOException e) {
			throw new UploadErrorException("There was an error finalizing the uploaded file. The file was not uploaded to the server.");
		} }
		System.out.println("Transformed into a byte array");
		byte[] artifactFile = aStream.toByteArray();
	
		Artifact anArtifact = null; 
		
		boolean merge = false;
		
		System.out.println("BEFORE the " + aDeliverable.getId().getName() +  " had " +aDeliverable.getArtifactSet().size() + " uploaded");

		
		System.out.println("Lookup for old artifact linked to this deliverable");
		if (existsEntity(new ArtifactId(aDeliverable,aTeam))) {
			System.out.println("There is already an artifact for this deliverable. Got the old artifact");
			anArtifact = getEntity(new ArtifactId(aDeliverable,aTeam));
			merge = true;
		} else {
			System.out.println("There are no artifact for this deliverable. Created a new artifact");
			System.out.println("Update the blob");
			anArtifact = new Artifact(aDeliverable, aTeam, entityUtility.getServerTimestamp(), artifactFile, theOutcomingFileDTO.getFilePath().getName());
			aDeliverable.addArtifact(anArtifact);
			aTeam.addArtifact(anArtifact);

			//FOR TESTING PURPOSES REPLACE THE PREVIOUS LINE WITH THE FOLLOWING
			//anArtifact = new Artifact(aDeliverable, aTeam, Date.valueOf("2012-05-02"), artifactFile, theOutcomingFileDTO.getFilePath().getName());
		}
		
		if (merge) {
			System.out.println("Update the blob");
			anArtifact.setFile(artifactFile);
			anArtifact.setFileName(theOutcomingFileDTO.getFilePath().getName());
			anArtifact.setSubmissionDate(entityUtility.getServerTimestamp());
			//FOR TESTING PURPOSES REPLACE THE PREVIOUS LINE WITH THE FOLLOWING
			//anArtifact.setSubmissionDate(Date.valueOf("2012-05-02"));
			em.merge(anArtifact);
		}
		else {
			em.persist(anArtifact);
		}
		
		System.out.println("Now the deliverable " + aDeliverable.getId().getName() +  " has " +aDeliverable.getArtifactSet().size() + " uploaded");
		em.merge(aDeliverable);
		
		System.out.println("Flushing");
		try {
		em.flush();
		}
		catch (Exception e) {
			throw new InvalidArgumentException("Data inserted is too big. Please check the data entered and retry.");
		}
		return anArtifact.getId().getDTO();
	}
	@Override
	public IncomingFileDTO downloadArtifact(ArtifactIdDTO theArtifactIdDTO)
			throws InvalidArgumentException, DownloadErrorException {
		
		System.out.println("Trying to let the client download the artifact " + theArtifactIdDTO);
		
		Artifact anArtifact = reconstructEntity(theArtifactIdDTO);
		ObjectUtility.isNull(anArtifact);
		
		System.out.println("Got the artifact");
		System.out.println("Trying to prepare the file to be downloaded");
		
		IncomingFileDTO aFile = null;

		aFile = anArtifact.getFile();
		
		System.out.println("Sending file " + anArtifact.getFileName());
		
		return aFile;
	}
	@Override
	public ArtifactDTO getArtifactInfo(ArtifactIdDTO theArtifactIdDTO)
			throws InvalidArgumentException {
		
		System.out.println("Trying to get the artifact dto of the artifact " + theArtifactIdDTO);

		ObjectUtility.isNull(theArtifactIdDTO);
		
		Artifact anArtifact = reconstructEntity(theArtifactIdDTO);
		ObjectUtility.isNull(anArtifact);

		System.out.println("Artifact exists.Returning its dto");
		
		return anArtifact.getDTO();
	}
	@Override
	public ArtifactIdDTO getArtifact(DeliverableIdDTO theDeliverableIdDTO,
			TeamIdDTO theTeamIdDTO) throws InvalidArgumentException {
		
		ObjectUtility.isNull(theDeliverableIdDTO);
		ObjectUtility.isNull(theTeamIdDTO);
		
		Team aTeam = teamManager.reconstructEntity(theTeamIdDTO);
		ObjectUtility.isNull(aTeam);
		
		Deliverable aDeliverable = deliverableManager.reconstructEntity(theDeliverableIdDTO);
		ObjectUtility.isNull(aDeliverable);

		Artifact anArtifact = getEntity(new ArtifactId(aDeliverable, aTeam));
		
		if (anArtifact == null) {
			return null;
		} else {
			return anArtifact.getId().getDTO();
		}
	}
	@Override
	public void setArtifactScore(ArtifactIdDTO theArtifactIdDTO, Double theScore)
			throws InvalidArgumentException {
		
		System.out.println("Trying to set the artifact score of the artifact " + theArtifactIdDTO);

		ObjectUtility.isNull(theArtifactIdDTO);
		ObjectUtility.isNull(theScore);
		
		Artifact anArtifact = reconstructEntity(theArtifactIdDTO);
		ObjectUtility.isNull(anArtifact);
		
		System.out.println("Setup artifact score to " + theScore);
		anArtifact.setScore(theScore);
		
		System.out.println("Merging");
		em.merge(anArtifact);

		System.out.println("Returning.");
		
	}
	@Override
	public Set<ArtifactIdDTO> getArtifactSetByDeliverable(DeliverableIdDTO theDeliverableIdDTO) throws InvalidArgumentException {
		
		ObjectUtility.isNull(theDeliverableIdDTO);
		
		Deliverable aDeliverable = deliverableManager.reconstructEntity(theDeliverableIdDTO);
		ObjectUtility.isNull(aDeliverable);
		
		Set<ArtifactIdDTO> aSet = new HashSet<ArtifactIdDTO>();
		
		for (Artifact anArtifact : aDeliverable.getArtifactSet()) {
			aSet.add(anArtifact.getDTO().getId());
		}
				
		return aSet;
	}
	@Override
	public Set<ArtifactIdDTO> getArtifactSetByTeam(TeamIdDTO theTeamIdDTO)
			throws InvalidArgumentException {

		Team aTeam = teamManager.reconstructEntity(theTeamIdDTO);
		ObjectUtility.isNull(aTeam);
		
		Set<ArtifactIdDTO> aSet = new HashSet<ArtifactIdDTO>();
		
		for (Artifact anArtifact : aTeam.getArtifactSet()) {
			aSet.add(anArtifact.getDTO().getId());
		}
				
		return aSet;
	
	}
	
	
}