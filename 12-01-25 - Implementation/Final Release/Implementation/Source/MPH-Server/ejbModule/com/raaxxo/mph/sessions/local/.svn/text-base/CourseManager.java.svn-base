package com.raaxxo.mph.sessions.local;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.raaxxo.mph.entities.Course;
import com.raaxxo.mph.entities.Professor;
import mph.beans.dto.CourseDTO;
import mph.beans.dto.ids.CourseIdDTO;
import mph.beans.dto.ids.ProfessorIdDTO;
import mph.beans.dto.ids.ProjectIdDTO;
import mph.beans.exceptions.InvalidArgumentException;
import util.ObjectUtility;

@Stateless
public class CourseManager implements CourseManagerLocal {

	@PersistenceContext(unitName = "mph") private EntityManager em;
	@EJB EntityUtilityLocal searchUtility;
	@EJB ProjectManagerLocal projectManager;
	@EJB UserManagerLocal userManager;
	
	@Override
	public Course reconstructEntity(CourseIdDTO theCourseIdDTO)
			throws InvalidArgumentException {

		ObjectUtility.isNull(theCourseIdDTO);
		
		Course aCourse = (Course) em.find(Course.class,theCourseIdDTO.getId());
		return aCourse;
		
	}

	public Set<Course> getCourseSet() {
		
		System.out.println("Trying to get the course set for the entire MPH software");

		Query q = em.createQuery("select object(c) from Course c");

		System.out.println("Executing the searching query");

		Set<Course> theCourseSet = new HashSet<Course>();


		System.out.println("Getting the course set");
		for (Object c : q.getResultList()) 
		{

			theCourseSet.add((Course) c);

		}


		System.out.println("Course set cardinality: " + theCourseSet.size());

		return theCourseSet;
	}

	@Override
	public Set<CourseIdDTO> getCourseIdDTOSet() {
		
		Set<Course> theCourseSet = getCourseSet();
		Set<CourseIdDTO> theCourseIdDTOList = new HashSet<CourseIdDTO>();
		
		for (Course aCourse : theCourseSet) 
		{
				theCourseIdDTOList.add(aCourse.getDTO().getId());	
		}
		
		return theCourseIdDTOList;
	}

	@Override
	public CourseDTO getCourseInfo(CourseIdDTO theCourseIdDTO)
			throws InvalidArgumentException {
		
		ObjectUtility.isNull(theCourseIdDTO);
		
		System.out.println("Trying to get the course DTO of the course with id: " + theCourseIdDTO);
		
		Course aCourse = reconstructEntity(theCourseIdDTO);
		
		System.out.println("The course with [" + theCourseIdDTO + "] exists.");
		
		System.out.println("Building the data structure to create the course DTO.");
		
		ProfessorIdDTO aProfessorIdDTO = new ProfessorIdDTO(aCourse.getProfessor().getUsername());
		
		Set<ProjectIdDTO> aProjIdDTOSet = projectManager.getProjectIdDTOSet(theCourseIdDTO);
		
		System.out.println("The data structure to create the course DTO are now builded. Creating the DTO");
		
		CourseDTO aCourseDTO = new CourseDTO(theCourseIdDTO, aCourse.getName(), aProfessorIdDTO, aProjIdDTOSet,  aCourse.getDescription());
		return aCourseDTO;
	}

	@Override
	public boolean existsEntity(Long theCourseId)
			throws InvalidArgumentException {
		
		Course aCourse = getEntity(theCourseId);

		if (aCourse == null)
			return false;
		else
			return true;
	}

	@Override
	public Course getEntity(Long theCourseId)
			throws InvalidArgumentException {
		
		ObjectUtility.isNull(theCourseId);
		Course aCourse = (Course) searchUtility.findOneEntity(Course.class, theCourseId);
		return aCourse;
	}

	@Override
	public Set<CourseIdDTO> getCourseIdDTOSet(ProfessorIdDTO theProfessorIdDTO)
			throws InvalidArgumentException {

		System.out.println("Getting the project set helded by the professor " + theProfessorIdDTO.getUsername());
		
		userManager.checkUserExists(Professor.class, theProfessorIdDTO.getUsername());
		Professor aProfessor = userManager.getEntity(Professor.class, theProfessorIdDTO);

		Set<Course> aCourseSet = aProfessor.getCoursesHolded();
		Set<CourseIdDTO> aCourseIdDTOSet = new HashSet<CourseIdDTO>();
		
		System.out.println("Total list of courses: "  + aCourseSet.size());
		
		for (Course aCourse : aProfessor.getCoursesHolded()) {
				aCourseIdDTOSet.add(aCourse.getDTO().getId());
		}
		
		System.out.println("Total list of projects: "  + aCourseIdDTOSet.size() + " helded by professor " + theProfessorIdDTO.getUsername());
		
		return aCourseIdDTOSet;
	
	}

	@Override
	public void checkEntityExists(Long theEntityId)
			throws InvalidArgumentException {
		if (existsEntity(theEntityId) == false) {
			throw new InvalidArgumentException("The course you've searched for ("+ theEntityId + ") doesn't exists");
		}		
	}
	
		
}