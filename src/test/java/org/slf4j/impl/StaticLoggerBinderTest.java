package org.slf4j.impl;

import com.ocarlsen.test.MockLoggerFactory;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.Test;
import org.slf4j.ILoggerFactory;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.slf4j.impl.StaticLoggerBinder.REQUESTED_API_VERSION;

public class StaticLoggerBinderTest {

    @Test
    public void apiVersion1_7() {
        assertThat(REQUESTED_API_VERSION, is("1.7"));
    }

    @Test
    public void getSingleton() throws IllegalAccessException {
        final StaticLoggerBinder staticLoggerBinder = StaticLoggerBinder.getSingleton();
        final StaticLoggerBinder singleton =
                (StaticLoggerBinder) FieldUtils.readDeclaredStaticField(StaticLoggerBinder.class, "SINGLETON", true);
        assertThat(staticLoggerBinder, is(sameInstance(singleton)));
    }

    @Test
    public void getLoggerFactory() {
        final ILoggerFactory loggerFactory = StaticLoggerBinder.getSingleton().getLoggerFactory();
        assertThat(loggerFactory, is(instanceOf(MockLoggerFactory.class)));
    }

}