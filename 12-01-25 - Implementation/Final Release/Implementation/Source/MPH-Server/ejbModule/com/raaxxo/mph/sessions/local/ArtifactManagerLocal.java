package com.raaxxo.mph.sessions.local;

import java.util.Set;

import javax.ejb.Local;

import com.raaxxo.mph.entities.Artifact;
import com.raaxxo.mph.entities.classids.ArtifactId;

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

/**
 * 
 * Local interface that manages the {@link Artifact} entities.
 * 
 */
@Local
public interface ArtifactManagerLocal extends ManagerLocal<Artifact, ArtifactId, ArtifactDTO, ArtifactIdDTO> {

	public Set<Artifact> getEntitySet(DeliverableIdDTO theDeliverableIdDTO) throws InvalidArgumentException;
	public Set<ArtifactIdDTO> getEntityIdDTOSet(DeliverableIdDTO theDeliverableIdDTO) throws InvalidArgumentException;
	
	public ArtifactIdDTO uploadArtifact(TeamIdDTO theTeamId, DeliverableIdDTO theDeliverableId, OutcomingFileDTO theFileDTO, StudentIdDTO theStudentIdDTO) throws InvalidArgumentException, ForbiddenOperationException, UploadErrorException;
	public IncomingFileDTO downloadArtifact(ArtifactIdDTO theArtifactIdDTO) throws InvalidArgumentException, DownloadErrorException;
	
	public ArtifactIdDTO getArtifact(DeliverableIdDTO theDeliverableIdDTO, TeamIdDTO theTeamIdDTO) throws InvalidArgumentException;
	public ArtifactDTO getArtifactInfo(ArtifactIdDTO theArtifactIdDTO) throws InvalidArgumentException;

	public Set<ArtifactIdDTO> getArtifactSetByDeliverable(	DeliverableIdDTO theDeliverableId) throws InvalidArgumentException;
	public Set<ArtifactIdDTO> getArtifactSetByTeam(TeamIdDTO theTeamIdDTO) throws InvalidArgumentException;

	public void setArtifactScore(ArtifactIdDTO theArtifactId, Double theScore) throws InvalidArgumentException;
	
	
}
