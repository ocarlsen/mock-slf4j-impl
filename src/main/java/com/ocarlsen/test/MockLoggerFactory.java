package com.ocarlsen.test;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

/**
 * http://www.slf4j.org/faq.html#slf4j_compatible
 */
public class MockLoggerFactory implements ILoggerFactory {

    private final Map<String, Logger> nameLoggerMap = new HashMap<>();

    @Override
    public Logger getLogger(final String name) {
        return nameLoggerMap.computeIfAbsent(name, key -> mock(Logger.class));
    }
}
