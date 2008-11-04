/**
 * 
 */
package com.sappenin.eaut.discovery.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sappenin.eaut.discovery.model.DiscoveryInformation;
import com.sappenin.eaut.discovery.yadis.YadisEAUTDiscoverer;
import com.sappenin.eaut.exceptions.EAUTException;
import com.sappenin.eaut.model.ParsedEmailAddress;
import com.sappenin.eaut.model.ParsedEmailAddressImpl;

/**
 * @author David
 * 
 */
public class EAUTDiscovererTest
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

	/**
	 * @throws EAUTException
	 * 
	 */
	@Test
	public void testSappeninDiscovery() throws EAUTException
	{
		ParsedEmailAddress pae = ParsedEmailAddressImpl.parse("beth@sappenin.com");
		assertTrue(pae != null);

		DiscoveryInformation discoveryInfo = new YadisEAUTDiscoverer().discover(pae);
		assertTrue(discoveryInfo != null);

		// Assert that the Discovery Type is correct.
		assertTrue(discoveryInfo.getEautType().equals(DiscoveryInformation.EAUT_SERVICE_TYPE.EAUT_TEMPLATE));

		// Assert that the Discovery URL is correct.
		assertTrue(discoveryInfo.getServiceURL().equals("http://openid.sappenin.com/{username}"));

	}

	/**
	 * @throws EAUTException
	 * 
	 */
	@Test
	public void testNorrisDotNameDiscovery() throws EAUTException
	{
		ParsedEmailAddress pae = ParsedEmailAddressImpl.parse("beth@norris.name");
		assertTrue(pae != null);

		DiscoveryInformation discoveryInfo = new YadisEAUTDiscoverer().discover(pae);
		assertTrue(discoveryInfo != null);

		// Assert that the Discovery Type is correct.
		assertTrue(discoveryInfo.getEautType().equals(DiscoveryInformation.EAUT_SERVICE_TYPE.EAUT_MAPPING_SERVICE));

		// Assert that the Discovery URL is correct.
		assertTrue(discoveryInfo.getServiceURL().equals("http://emailtoid.net/mapper?email=beth@norris.name"));

	}

}
