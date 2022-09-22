package com.ocarlsen.test;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.IMarkerFactory;
import org.slf4j.spi.MDCAdapter;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

public class MockServiceProviderTest {

  public MockServiceProvider serviceProvider;

  @Before
  public void setUp() {
    serviceProvider = new MockServiceProvider();
  }

  @Test
  public void getLoggerFactory() {
    serviceProvider.initialize();

    assertThat(serviceProvider.getLoggerFactory(), is(instanceOf(MockLoggerFactory.class)));
  }

  @Test
  public void getMarkerFactory() {
    serviceProvider.initialize();

    assertThat(serviceProvider.getMarkerFactory(), is(instanceOf(IMarkerFactory.class)));
  }

  @Test
  public void getMDCAdapter() {
    serviceProvider.initialize();

    assertThat(serviceProvider.getMDCAdapter(), is(instanceOf(MDCAdapter.class)));
  }

  @Test
  public void getRequestedApiVersion() {
    assertThat(serviceProvider.getRequestedApiVersion(), startsWith("2.0"));
  }

  @Test
  public void initialize() {
    assumeThat(serviceProvider.getLoggerFactory(), nullValue());
    assumeThat(serviceProvider.getMarkerFactory(), nullValue());
    assumeThat(serviceProvider.getMDCAdapter(), nullValue());

    serviceProvider.initialize();

    assertNotNull(serviceProvider.getLoggerFactory());
    assertNotNull(serviceProvider.getMarkerFactory());
    assertNotNull(serviceProvider.getMDCAdapter());
  }
}
