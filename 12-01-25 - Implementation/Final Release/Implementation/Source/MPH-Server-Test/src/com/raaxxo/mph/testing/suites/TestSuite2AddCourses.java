package com.raaxxo.mph.testing.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.raaxxo.mph.testing.cases.Test3AddCourses;

@RunWith(Suite.class)
@SuiteClasses({ TestSuite1Login.class, Test3AddCourses.class })
public class TestSuite2AddCourses {

}
