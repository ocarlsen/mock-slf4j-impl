package com.ocarlsen.test;

import org.junit.Test;
import org.slf4j.Logger;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

public class DelegatingLoggerFactoryTest {

    private final DelegatingLoggerFactory loggerFactory = new DelegatingLoggerFactory();

    @Test
    public void getLogger() {
        final String name = "abc";
        final Logger logger1 = loggerFactory.getLogger(name);
        assertThat(logger1, is(notNullValue()));

        assertThat(logger1, is(instanceOf(DelegatingLogger.class)));

        final Logger logger2 = loggerFactory.getLogger(name);
        assertThat(logger2, is(sameInstance(logger1)));
    }
}