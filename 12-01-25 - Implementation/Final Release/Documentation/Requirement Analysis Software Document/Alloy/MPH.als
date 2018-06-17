module MPH_world

//Support signatures

//Date
sig Date {}

//Personal Data
sig PersonalData {}

//ID
sig ID {}

//Name
sig Name{}

//Score
sig Score {}

//Signatures

//User
abstract sig User {
	id: one ID,
	personalData: lone PersonalData,
}

//Professors
sig Professor extends User {
	courses: set Course,					//every professor can be associated to a set of courses
}

//Courses
sig Course{
	projects: set Project, 					//every course can have projects
}

//Projects
sig Project {
	deliverables: some Deliverable,	//every project must have some Deliverables
	teams: set Team,							//every project has a set of teams associated to it
}

//Students
sig Student extends User{}

//Team
sig Team {
	name: one Name,
	students: set Student,
	artifacts: set Artifact,					//every team has uploaded a set of artifacts
	shared: set Team,						//every team can share information with another one
} {#students >=1 and #students <= 3}

//Deliverables
sig Deliverable {
	deadline: one Date,						//every derivable must have only one deadline associated to it
}

//Artifacts
sig Artifact {
	deliverable: one Deliverable,
	score: lone Score,
}

//Facts

//ID's of professors and students must be unique
fact uniqueID {
	all p: Professor, s: Student | p.id != s.id
	all s1, s2: Student | s1 != s2 implies s1.id != s2.id
	all p1, p2: Professor | p1 != p2 implies p1.id != p2.id
}

//for every course there is a professor associated to it
fact noCourseWithoutProfessor {
	all c: Course | one p: Professor | c in p.courses
}

//for every project there is a course associated to it
fact noProjectWithoutCourse {
	all p: Project | one c: Course| p in c.projects
}

//for every deliverable there is a project associated to it
fact noDeliverableWithoutProject {
	all d: Deliverable | one p: Project| d in p.deliverables
}

//every student can not belong to teams related to the same project
fact uniqueProjectTeamPerStudent {
	all s: Student, t1, t2: Team | (s in t1.students and s in t2.students and t1 != t2) implies no p: Project | t1 in p.teams and t2 in p.teams
}

//every team must be associated to only one project
fact uniqueProjectPerTeam {
	all t: Team | no p1, p2: Project | p1 != p2 and t in p1.teams and t in p2.teams
	all t: Team | one p: Project | t in p.teams
}

//every project must be associated to only one course
fact uniqueCoursePerProject {
	all p: Project | no c1, c2: Course | c1 != c2 and p in c1.projects and p in c2.projects
}

//a team can not be composed by students with the same ID
fact uniqueStudentsPerTeam {
	all t: Team, s1,s2: Student |  (s1 in t.students and s2 in t.students and s1 != s2) implies s1.id != s2.id
}

//every team has a unique name per project
fact uniqueTeamNamePerProject {
	all p: Project, t1,t2: Team | (t1 in p.teams and t2 in p.teams and t1 != t2) implies t1.name != t2.name
}

//every artifact must belong to only one team
fact uniqueTeamPerArtifact {
	all a: Artifact | one t: Team | a in t.artifacts
	all a: Artifact | no t1, t2: Team | t1 != t2 and a in t1.artifacts and a in t2.artifacts
}

//every artifact delivered by a team must be associated to different deliverables belonging to the same project
fact uniqueDeliverablePerProjectTeamArtifact {
	all a1, a2 : Artifact, t: Team | (a1 != a2 and a1 in t.artifacts and a2 in t.artifacts) implies a1.deliverable != a2.deliverable
	all a: Artifact, t: Team, p: Project | t in p.teams implies a.deliverable in p.deliverables
}	

//every deliverable must be associated to only one project
fact uniqueProjectPerDeliverable {
	no d: Deliverable, p1, p2: Project | p1 != p2 and d in p1.deliverables and d in p2.deliverables
}

//every team has a number of artifacts minor or equal to the number of deliverables of the project
fact maxArtifactsPerTeam {
	all t: Team, p: Project | t in p.teams implies #t.artifacts <= #p.deliverables
}

//every team can not share information with itself
fact noSelfSharing{
	all t: Team | no t1: Team | t1  in t.shared and t1 = t
}

//every team can only share information with teams related to the same project
fact noSharingBetweenDifProjects{
	all t1, t2: Team, p: Project | (t1 in p.teams and (t1 in t2.shared or t2 in t1.shared)) implies t2 in p.teams
}

//no lone signatures
fact noLoneSignatures {

	all d: Date | one del: Deliverable | del.deadline = d			//Date vs Deliverable
	all n: Name | one t: Team | t.name = n								//Name vs Team
	all s: Score | one a: Artifact | a.score = s							//Score vs Artifact
	all pd: PersonalData | one u: User | u.personalData = pd	//Personal Data vs User
	all i: ID | one u: User| u.id = i 											//ID vs User

}

//Assertions

//every team contains at least one student but no more than three
assert studentsInTeam {
	no t: Team | #t.students < 1 or #t.students > 3
}

check studentsInTeam

//if there are no students, there are no teams
assert  noStudentsNoTeams{
	#Student = 0 implies #Team = 0
}

check noStudentsNoTeams

//there are no project teams associated to zero or more than two projects
assert projectsPerTeam {
	all t: Team | one p: Project | t in p.teams
	all t: Team | no p1, p2: Project | p1 != p2 and t in p1.teams and t in p2.teams 
}

check projectsPerTeam

//there are no artifacts associated to deliverables belonging to a project different from the project associated to the team
assert artifactsProject {
	all a: Artifact, t: Team, p: Project | (a in t.artifacts and t in p.teams) implies a.deliverable in p.deliverables
}

check artifactsProject

//if there are no projects, there are no artifacts
assert  noProjectsNoArtifacts {
	#Project = 0 implies #Artifact = 0
}

check noProjectsNoArtifacts

//a team can not share information with teams belonging to a different project
assert noDifSharing {
	all t1, t2 : Team | no p1,p2: Project | (t1 in t2.shared or t2 in t1.shared) and p1 != p2 and t1 in p1.teams and t2 in p2.teams
}

check noDifSharing

//Predicates

pred showWorld1(){

	#Deliverable > 5
	#Professor > 1
	#Team > 2
	#Course > 1
	#Artifact > 5
}


pred showWorld2(){

	all c: Course | #c.projects > 1

	#Project > 3
	#Professor > 1
	#Team > 2
	#Course > 2
	#Student > 4

}

pred showWorld3(){

	all c: Course | #c.projects = 1
	all c: Project | #c.deliverables> 0
	all t: Team | #t.students = 3 and #t.shared = 1

	#Team = 2
	#Course = 1
	#Professor = 1

	#Artifact > 3
	#Score> 3 and #Score < #Artifact
	#PersonalData > 2


}
run showWorld1 for 7
run showWorld2 for 7
run showWorld3 for 7
