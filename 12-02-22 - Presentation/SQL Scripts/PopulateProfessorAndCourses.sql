delete from mph.Project_Deliverable;
delete from mph.Deliverable;
delete from mph.StudentBelongsTeam;
delete from mph.Team;
delete from mph.Student;
delete from mph.Project;

delete from mph.Professor_Course;
delete from mph.Course;
delete from mph.Professor;



INSERT INTO mph.Student (Username, Birthday, Email, FirstName, LastName, Password, TelephoneNumber) VALUES ("raa", "1989-07-31", "riccardo.ancona@gmail.com", "Riccardo", "Ancona", "098f6bcd4621d373cade4e832627b4f6", "091363850");
INSERT INTO mph.Student (Username, Birthday, Email, FirstName, LastName, Password, TelephoneNumber) VALUES ("axxo", "1989-10-11", "alessandro.ditta@gmail.com", "Alessandro", "Ditta", "098f6bcd4621d373cade4e832627b4f6", "3496842794");
INSERT INTO mph.Student (Username, Birthday, Email, FirstName, LastName, Password, TelephoneNumber) VALUES ("lusss89", "1989-10-06", "alberto.lucinipaioni@gmail.com", "Alberto", "Lucini Paioni", "098f6bcd4621d373cade4e832627b4f6", "3289436673");
INSERT INTO mph.Student (Username, Birthday, Email, FirstName, LastName, Password, TelephoneNumber) VALUES ("cale", "1989-10-07", "claudio.caletti@gmail.com", "Claudio", "Caletti", "098f6bcd4621d373cade4e832627b4f6", "091363850");
INSERT INTO mph.Student (Username, Birthday, Email, FirstName, LastName, Password, TelephoneNumber) VALUES ("filieri", "1989-10-06", "antonio.filieri@gmail.com", "Antonio", "Filieri", "098f6bcd4621d373cade4e832627b4f6", "3289436673");
INSERT INTO mph.Student (Username, Birthday, Email, FirstName, LastName, Password, TelephoneNumber) VALUES ("calcavecchia", "1984-06-24", "nicola.calcavecchia@gmail.com", "Nicola", "Calcavecchia", "098f6bcd4621d373cade4e832627b4f6", "3496777247");
INSERT INTO mph.Student (Username, Birthday, Email, FirstName, LastName, Password, TelephoneNumber) VALUES ("caporuscio", "1975-08-13", "caporuscio@gmail.com", "Mauro", "Caporuscio", "098f6bcd4621d373cade4e832627b4f6", "3285793354");

INSERT INTO mph.Professor (Username ,Birthday , Email ,FirstName ,LastName ,Password , TelephoneNumber) VALUES ("dinitto", " 1967-07-24", "dinitto@elet.polimi.it", "Elisabetta", "Di Nitto", "098f6bcd4621d373cade4e832627b4f6" , "0289695760");

    INSERT INTO mph.Course (Uid, Description, Name , Professor) VALUES ( 1, 
    "Scopo del corso é fornire i concetti relativi ai moderni processi di produzione del software, i principi dell’ingegneria dei requisiti, delle architetture software, delle metodologie di progettazione. Illustrare le principali notazioni di specifica. Presentare metodi e strumenti per la verifica e la convalida.", 
    "Ingegneria del Software 2", "dinitto");

    INSERT INTO mph.Professor_Course ( Professor_Username , coursesHolded_Uid) VALUES ("dinitto", 1);


    INSERT INTO mph.Project (Title, Description, EndDate, StartDate, course_Uid) 
    VALUES ("MPH", "MPH is an application used by professors and students to facilitate the organization of university courses with project deliveries. The students work together in teams composed by 1, 2 or 3 participants and access the system through username and password. Each student autonomously registers to the system and has a profile containing his/her information (name, last name, id, profile picture, etc.). Moreover, he/she accesses to the information and functionalities associated to the group he/she belongs to.", 
    "2012-03-01", "2012-02-23", 1);


    INSERT INTO mph.Deliverable (Name, Deadline, Description, Penalty, project_course_Uid, project_Title) 
    VALUES ("Group Registration", "2012-02-24", "Group registration and project planning. You should form your group and register it strictly following the directions that will be published on the course website and will be presented during the project lab. Also, you will submit your project plan in which you will identify the tasks that you will execute during the project, the responsible person for each task and will provide a rough estimate of the required effort. To support you in quantifying this effort we will provide November 3rd on the course website some statistics from last year project. Make sure that you start working at your plan immediately so that you can move to the next phase (see below) BEFORE the deadline of the 17th.", 
    3.3, 1, "MPH");

    INSERT INTO mph.Project_Deliverable (Project_course_Uid, Project_Title, deliverableList_Name, deliverableList_project_course_Uid, deliverableList_project_Title) 
    VALUES (1, "MPH", "Group Registration", 1, "MPH");


    INSERT INTO mph.Deliverable (Name, Deadline, Description, Penalty, project_course_Uid, project_Title) 
    VALUES ("RASD", "2012-02-25", "Requirements analysis and specification document (RASD). RASD contains description of the use scenarios, the use cases that describe them, and the models describing requirements and specification. You are asked to use a suitable mix of natural language, UML and Alloy. UML and Alloy MUST be part of the documentation. You must also show that you used the Alloy tool for analysis, by reporting the models you obtained by using it. Of course, the initialwritten problem statement we provided above suffers from the typical drawbacks of natural language descriptions: it is informal, incomplete, uses different terms for the same concepts, etc. You may choose to solve the incompleteness and ambiguity as you wish, but be careful to clearly document the choices you make. Note: the RASD deadline is very close to the group registration deadline. Do not wait the group registration before starting working at your RASD! Start right after you have defined the project plan and feel free to update the plan as you proceed.", 
    0.3, 1, "MPH");

    INSERT INTO mph.Project_Deliverable (Project_course_Uid, Project_Title, deliverableList_Name, deliverableList_project_course_Uid, deliverableList_project_Title) 
    VALUES (1, "MPH", "RASD", 1, "MPH");


    INSERT INTO mph.Deliverable (Name, Deadline, Description, Penalty, project_course_Uid, project_Title) 
    VALUES ("DD", "2012-02-26", "DD must contain a functional description of the system, and any other view you find useful to provide. You should use all the UML diagrams  you need to provide a full description of the system. Alloy may also be useful.", 
    0.6, 1, "MPH");
    INSERT INTO mph.Project_Deliverable (Project_course_Uid, Project_Title, deliverableList_Name, deliverableList_project_course_Uid, deliverableList_project_Title) 
    VALUES (1, "MPH", "DD", 1, "MPH");


    INSERT INTO mph.Deliverable (Name, Deadline, Description, Penalty, project_course_Uid, project_Title) 
    VALUES ("Implementation", "2012-02-27", "You should provide an implementation for the requirements you specified in the RASD, following the design you specified in the DD. You are requested to release source code and executables, installation and use manuals, system test cases.", 
    2.3, 1, "MPH");
    INSERT INTO mph.Project_Deliverable (Project_course_Uid, Project_Title, deliverableList_Name, deliverableList_project_course_Uid, deliverableList_project_Title) 
    VALUES (1, "MPH", "Implementation", 1, "MPH");




    INSERT INTO mph.Course (Uid, Description, Name , Professor) VALUES ( 2, 
    "Il corso si propone di fornire una visione d insieme delle potenzialità offerte dall analisi e dalla modellazione informatica  di problemi reali, e di  formare gli studenti a comprendere i principi che stanno alla base di un futuro utilizzo e interazione con i sistemi informatici. Il corso presenta i concetti fondamentali della programmazione con riferimento all uso del linguaggio C, le tecniche di programmazione con riferimento a uno strumento di calcolo numerico, e le caratteristiche dei sistemi informatici enfatizzandone gli aspetti di integrazione.", 
    "Informatica B", "dinitto");

INSERT INTO mph.Professor_Course ( Professor_Username , coursesHolded_Uid) VALUES ("dinitto", 2);

INSERT INTO mph.Professor (Username ,Birthday , Email ,FirstName ,LastName ,Password , TelephoneNumber) VALUES ("sami", " 1945-01-20", "sami@elet.polimi.it", "Mariagiovanna", "Sami", "098f6bcd4621d373cade4e832627b4f6" , "0223993516");

    INSERT INTO mph.Course (Uid, Description, Name , Professor) VALUES ( 3, 
    "Scopo del corso è porre lo studente in grado di analizzare e valutare le moderne architetture di CPU con particolare attenzione ai multicore, identificando gli aspetti più rilevanti nell ottica delle prestazioni e le caratteristiche più significative anche in un ambito di progettazione di sistemi dedicati (embedded systems).", 
    "Architetture Avanzate dei Calcolatori", "sami");

    INSERT INTO mph.Professor_Course ( Professor_Username , coursesHolded_Uid) VALUES ("sami", 3);

    INSERT INTO mph.Course (Uid, Description, Name , Professor) VALUES ( 4, 
    "Scopo del corso è porre lo studente in grado di analizzare e valutare le moderne architetture di calcolo a livello di CPU e di sistema, identificando gli aspetti più rilevanti nell ottica delle prestazioni e le caratteristiche più significative anche in un ambito di progettazione di sistemi dedicati (embedded systems). ", 
    "Principi di Architetture dei Calcolatori", "sami");

    INSERT INTO mph.Professor_Course ( Professor_Username , coursesHolded_Uid) VALUES ("sami", 4);

INSERT INTO mph.Professor (Username ,Birthday , Email ,FirstName ,LastName ,Password , TelephoneNumber) VALUES ("lanzi", " 1967-01-27", "lanzi@elet.polimi.it", "Pier Luca", "Lanzi", "098f6bcd4621d373cade4e832627b4f6" , "022665337");

    INSERT INTO mph.Course (Uid, Description, Name , Professor) VALUES ( 5, 
    "The goal of the course is to develop games. For this purpose, each student is require to submit a game proposal at the beginning of the course. All the proposals are collected and all the students can cast 5 votes for the proposals they like most. The game receiving the highest number of votes are the one that will be developed during the course.", 
    "Videogame Design and Programming", "lanzi");

    INSERT INTO mph.Professor_Course ( Professor_Username , coursesHolded_Uid) VALUES ("lanzi", 5);

    INSERT INTO mph.Course (Uid, Description, Name , Professor) VALUES ( 6, 
    "This course provides an introduction to Data Mining and an overview of all the most important algorithms used in this field. The course consists two sets of lectures. The first set, covering 24 hours, introduces the field of Data Mining and overviews all the main algorithms available in most commercial tools. The second set of lectures, covering 16 hours, focuses on specific application areas such as Text Mining, Bioinformatics, social networks, etc.Students enrolled in the course of Tecniche di Apprendimento Automatico per Applicazioni di Data Mining need to attend only the first 20 hours of the course and can give the full exam in April/May during the first mid-term.", 
    "Data Mining and Text Mining", "lanzi");

    INSERT INTO mph.Professor_Course ( Professor_Username , coursesHolded_Uid) VALUES ("lanzi", 6);


INSERT INTO mph.Team (Name, isClosed, project_course_Uid, project_Title) 
VALUES ("Codemasters", 0, 1, "MPH");
INSERT INTO mph.StudentBelongsTeam (studentSet_Username, studentTeams_Name, studentTeams_project_course_Uid, studentTeams_project_Title) VALUES ("axxo", "Codemasters", 1, "MPH");
INSERT INTO mph.StudentBelongsTeam (studentSet_Username, studentTeams_Name, studentTeams_project_course_Uid, studentTeams_project_Title) VALUES ("lusss89", "Codemasters", 1, "MPH");

INSERT INTO mph.Team (Name, isClosed, project_course_Uid, project_Title) 
VALUES ("DEEP S.E.", 0, 1, "MPH");
INSERT INTO mph.StudentBelongsTeam (studentSet_Username, studentTeams_Name, studentTeams_project_course_Uid, studentTeams_project_Title) VALUES ("filieri", "DEEP S.E.", 1, "MPH");
INSERT INTO mph.StudentBelongsTeam (studentSet_Username, studentTeams_Name, studentTeams_project_course_Uid, studentTeams_project_Title) VALUES ("calcavecchia", "DEEP S.E.", 1, "MPH");

