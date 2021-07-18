package com.ocarlsen.util;

import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class DelegatingLoggerTest {

    private final Logger logger = mock(Logger.class);
    private final DelegatingLogger delegatingLogger = new DelegatingLogger(logger);

    @After
    public void noMoreInteractions() {
        verifyNoMoreInteractions(logger);
    }

    @Test
    public void getName() {
        delegatingLogger.getName();
        verify(logger).getName();
    }

    @Test
    public void isTraceEnabled() {
        delegatingLogger.isTraceEnabled();
        verify(logger).isTraceEnabled();
    }

    @Test
    public void trace() {
        final String msg = "abc";
        delegatingLogger.trace(msg);
        verify(logger).trace(msg);
    }

    @Test
    public void trace_format_1() {
        final String format = "arg:{}";
        final Object arg = new Object();
        delegatingLogger.trace(format, arg);
        verify(logger).trace(format, arg);
    }

    @Test
    public void trace_format_2() {
        final String format = "{}/{}";
        final Object arg1 = new Object();
        final Object arg2 = new Object();
        delegatingLogger.trace(format, arg1, arg2);
        verify(logger).trace(format, arg1, arg2);
    }

    @Test
    public void trace_format_varargs() {
        final String format = "{}/{}/{}";
        final Object arg1 = new Object();
        final Object arg2 = new Object();
        final Object arg3 = new Object();
        delegatingLogger.trace(format, arg1, arg2, arg3);
        verify(logger).trace(format, arg1, arg2, arg3);
    }

    @Test
    public void trace_throwable() {
        final String msg = "def";
        final Throwable t = new Throwable();
        delegatingLogger.trace(msg, t);
        verify(logger).trace(msg, t);
    }

    // TODO: The rest
}