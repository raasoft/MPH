package com.raaxxo.mph.sessions.local;

import java.util.Set;

import com.raaxxo.mph.entities.Course;
import mph.beans.dto.CourseDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.exceptions.InvalidArgumentException;



/**
 * 
 * Local interface that manages the {@link Course} entities.
 * 
 */
public interface CourseManagerLocal extends ManagerLocal<Course, Long, CourseDTO, CourseIdDTO>  {
	
	public Course reconstructEntity(CourseIdDTO theCourseIdDTO) throws InvalidArgumentException;
	public Course getEntity(Long theCourseId) throws InvalidArgumentException;
	
	public boolean existsEntity(Long theCourseId) throws InvalidArgumentException;
	
	public Set<Course> getCourseSet();
	public Set<CourseIdDTO> getCourseIdDTOSet();
	public Set<CourseIdDTO> getCourseIdDTOSet(ProfessorIdDTO theProfessorIdDTO) throws InvalidArgumentException;	
	
	public CourseDTO getCourseInfo(CourseIdDTO theCourseIdDTO) throws InvalidArgumentException;
	
}
