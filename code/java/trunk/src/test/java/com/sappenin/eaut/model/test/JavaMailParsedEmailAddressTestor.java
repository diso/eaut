package com.sappenin.eaut.model.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.sappenin.eaut.exceptions.InvalidEmailAddressException;
import com.sappenin.eaut.model.JavaMailParsedEmailAddressImpl;
import com.sappenin.eaut.model.ParsedEmailAddress;
import com.sappenin.eaut.model.ParsedEmailAddressImpl;

/**
 * 
 * @author David
 * 
 */
public class JavaMailParsedEmailAddressTestor extends TestCase
{
	ParsedEmailAddress pae1 = null;
	ParsedEmailAddress pae2 = null;

	protected void setUp() throws Exception
	{
		super.setUp();

		pae1 = new JavaMailParsedEmailAddressImpl();
		pae1.setLocalPart("user");
		pae1.setDomain("example.com");

		pae2 = new JavaMailParsedEmailAddressImpl();
		pae2.setLocalPart("user");
		pae2.setDomain("example.com");
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	/**
	 * Tests equality of the object.
	 */
	@Test
	public void testEquals()
	{
		assertTrue(pae1.equals(pae2));
		assertTrue(pae1.hashCode() == pae2.hashCode());
	}

	/**
	 * Tests getters/setters of the object.
	 */
	@Test
	public void testGettersSetters()
	{
		assertTrue(pae1.getLocalPart().equals("user"));
		assertTrue(pae1.getDomain().equals("example.com"));
		pae1.setLocalPart("user2");
		pae1.setDomain("mail.example.com");
		assertTrue(pae1.getLocalPart().equals("user2"));
		assertTrue(pae1.getDomain().equals("mail.example.com"));
	}

	@Test
	public void testParse() throws InvalidEmailAddressException
	{
		ParsedEmailAddress pae = JavaMailParsedEmailAddressImpl.parse("beth@example.com");

		assertTrue(pae.getLocalPart().equals("beth"));
		assertTrue(pae.getDomain().equals("example.com"));

		try
		{
			pae = ParsedEmailAddressImpl.parse("beth");
			assertFalse(true);
		}
		catch (Exception e)
		{
			// This should throw an exception.
			assertTrue(true);
		}

	}
}
