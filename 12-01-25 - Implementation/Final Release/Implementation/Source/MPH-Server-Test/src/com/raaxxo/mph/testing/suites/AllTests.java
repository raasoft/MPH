package com.raaxxo.mph.testing.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.raaxxo.mph.testing.cases.Test1PopulateDatabase;
import com.raaxxo.mph.testing.cases.Test2LoginUsers;
import com.raaxxo.mph.testing.cases.Test3AddCourses;
import com.raaxxo.mph.testing.cases.Test4AddProjects;
import com.raaxxo.mph.testing.cases.Test5AddTeams;
import com.raaxxo.mph.testing.cases.Test6AddArtifacts;

@RunWith(Suite.class)
@SuiteClasses({ Test1PopulateDatabase.class, Test2LoginUsers.class,
		Test3AddCourses.class, Test4AddProjects.class, Test5AddTeams.class,
		Test6AddArtifacts.class })
public class AllTests {

}
