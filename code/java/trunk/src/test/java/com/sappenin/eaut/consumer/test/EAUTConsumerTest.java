/**
 * 
 */
package com.sappenin.eaut.consumer.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sappenin.eaut.consumer.EAUTConsumer;
import com.sappenin.eaut.consumer.EAUTConsumerImpl;
import com.sappenin.eaut.exceptions.EAUTException;

/**
 * @author David
 * 
 */
public class EAUTConsumerTest
{

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testEAUTTemplate() throws EAUTException
	{
		String testEmailAddress = "beth@sappenin.com";

		EAUTConsumer consumer = new EAUTConsumerImpl();
		String url = consumer.doEAUT(testEmailAddress);

		assertTrue(url.equals("http://openid.sappenin.com/beth"));

	}

	@Test
	public void testEAUTMappingService() throws EAUTException
	{
		String testEmailAddress = "will@norris.name";
		EAUTConsumer consumer = new EAUTConsumerImpl();
		String url = consumer.doEAUT(testEmailAddress);

		assertTrue(url.equals("http://will.norris.name/"));

	}
}
