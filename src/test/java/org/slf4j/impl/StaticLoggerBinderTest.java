package org.slf4j.impl;

import com.ocarlsen.test.MockLoggerFactory;
import org.junit.Test;
import org.slf4j.ILoggerFactory;

import java.lang.reflect.Field;

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
    public void getSingleton() throws NoSuchFieldException, IllegalAccessException {
        final StaticLoggerBinder staticLoggerBinder = StaticLoggerBinder.getSingleton();
        final StaticLoggerBinder singleton = getPrivateStaticValue();
        assertThat(staticLoggerBinder, is(sameInstance(singleton)));
    }

    @Test
    public void getLoggerFactory() {
        final ILoggerFactory loggerFactory = StaticLoggerBinder.getSingleton().getLoggerFactory();
        assertThat(loggerFactory, is(instanceOf(MockLoggerFactory.class)));
    }

    // TODO: Factor out to utility
    private StaticLoggerBinder getPrivateStaticValue() throws NoSuchFieldException, IllegalAccessException {
        // Get field instance
        final Field field = StaticLoggerBinder.class.getDeclaredField("SINGLETON");
        field.setAccessible(true); // Suppress Java language access checking

        // Get value
        return (StaticLoggerBinder) field.get(null);
    }

}