package com.ocarlsen.util;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

/**
 * http://www.slf4j.org/faq.html#slf4j_compatible
 */
public class DelegatingLoggerFactory implements ILoggerFactory {

    private final Map<String, Logger> nameloggerMap = new HashMap<>();

    @Override
    public Logger getLogger(final String name) {
        final Logger mockLogger = mock(Logger.class);
        return nameloggerMap.computeIfAbsent(name, key -> new DelegatingLogger(mockLogger));
    }
}
