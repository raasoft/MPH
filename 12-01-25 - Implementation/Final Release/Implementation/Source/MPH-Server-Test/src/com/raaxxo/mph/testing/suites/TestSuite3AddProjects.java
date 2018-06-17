package com.raaxxo.mph.testing.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.raaxxo.mph.testing.cases.Test4AddProjects;

@RunWith(Suite.class)
@SuiteClasses({ TestSuite2AddCourses.class, Test4AddProjects.class })
public class TestSuite3AddProjects {

}
