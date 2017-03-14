package com.vpaliy.domain.interactor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AddUseCaseTest.class,
                     DeleteUseCaseTest.class,
                     GetListUseCaseTest.class,
                     GetModelDetailsTest.class})
public class InteractorTests {
}
