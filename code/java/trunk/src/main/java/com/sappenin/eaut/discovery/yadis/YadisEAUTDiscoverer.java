/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.discovery.yadis;

import java.net.MalformedURLException;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openid4java.discovery.yadis.YadisException;
import org.openid4java.discovery.yadis.YadisResolver;
import org.openid4java.discovery.yadis.YadisResult;
import org.openxri.xml.SEPUri;
import org.openxri.xml.Service;
import org.openxri.xml.XRD;
import org.openxri.xml.XRDS;

import com.sappenin.eaut.discovery.EAUTDiscoverer;
import com.sappenin.eaut.discovery.model.DiscoveryInformation;
import com.sappenin.eaut.discovery.model.DiscoveryInformationImpl;
import com.sappenin.eaut.exceptions.EAUTException;
import com.sappenin.eaut.model.ParsedEmailAddress;

/**
 * A Concrete EAUTDiscoverer that utilizes Yadis to perform EAUT Discovery
 * (Draft 5 of the EAUT spec).
 * 
 * @author David Fuelling
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public class YadisEAUTDiscoverer implements EAUTDiscoverer
{
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * The Default Constructor
	 */
	public YadisEAUTDiscoverer()
	{
		super();
	}

	/**
	 * Performs EAUT Discovery on a ParsedEmailAddress.
	 * 
	 * @param pae
	 * @return
	 * @throws EAUTException
	 */
	@Override
	public DiscoveryInformation discover(ParsedEmailAddress pae)
	{
		DiscoveryInformation discoveryInformation = null;

		try
		{
			// 1.) Determine the URL to use for EAUT via EAUT Discovery, and
			// retrieve the XRDS document at that location.
			XRDS xrds = this.discoverXRDS(pae);

			// 2.) Parse the XRDS and determine the type of EAUT that should be
			// performed.
			discoveryInformation = parseXRDS(xrds);

			if (discoveryInformation == null)
			{
				discoveryInformation = new DiscoveryInformationImpl();
				discoveryInformation.setServiceURL("http://emailtoid.net/mapper?email=" + pae.getLocalPart() + "@"
					+ pae.getDomain());
			}

		}
		catch (EAUTException e)
		{
			log.error(e.toString(), e);
		}
		// 3.) Return the discovered EAUT information.
		return discoveryInformation;
	}

	// //////////////////////////////////////////////////////////////////
	// PRIVATE UTIL FUNCTIONS
	// //////////////////////////////////////////////////////////////////

	/**
	 * Attempts to located and retrieve an XRDS document based upon the provided
	 * email address.
	 * 
	 * @param emailAddress
	 * @return
	 */
	private XRDS discoverXRDS(ParsedEmailAddress pae) throws EAUTException
	{
		XRDS returnableXRDS = null;
		try
		{
			String discoveryEndpointURL = "http://" + pae.getDomain();
			YadisResolver yadis = new YadisResolver();
			// Get the XRDS Document at this endpoint, if possible. Use 25
			// redirects, since it is unlikely that more than 25 redirects would
			// be need.
			YadisResult result = yadis.discover(discoveryEndpointURL, 25);
			returnableXRDS = result.getXrds();
		}
		catch (YadisException ye)
		{

			try
			{
				// Try this again, except with www. prepended to the domain.
				String discoveryEndpointURL = "http://www." + pae.getDomain();
				YadisResolver yadis = new YadisResolver();
				// Get the XRDS Document at this endpoint, if possible. Use 25
				// redirects, since it is unlikely that more than 25 redirects
				// would be need.
				YadisResult result = yadis.discover(discoveryEndpointURL, 25);
				returnableXRDS = result.getXrds();
			}
			catch (YadisException ye2)
			{
				log.error(ye2.toString(), ye2);
			}
		}

		return returnableXRDS;

	}

	/**
	 * Takes an XRDS document object and parses it to determine the
	 * DiscoveryInformation contained inside, if any.
	 * 
	 * @param xrds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private final DiscoveryInformation parseXRDS(XRDS xrds)
	{
		if (xrds == null)
			return null;

		XRD xrd = xrds.getFinalXRD();
		Service service = null;
		DiscoveryInformation discoveryInformation = null;

		// Figure out if there is a EAUT Template or EAUT Mapper Service.
		Iterator<Service> serviceIter = xrd.getPrioritizedServices().iterator();
		while (serviceIter.hasNext())
		{
			service = serviceIter.next();

			// iterate through all URIs in the service
			Iterator<SEPUri> sepUriIter = service.getPrioritizedURIs().iterator();
			SEPUri sepUri;
			while (sepUriIter.hasNext())
			{
				sepUri = sepUriIter.next();
				try
				{
					discoveryInformation = new DiscoveryInformationImpl();
					discoveryInformation.setServiceURL(sepUri.getURI().toURL().toString());
				}
				catch (MalformedURLException e)
				{
					log.warn("Ignoring malformed EAUT Service endpoint URL in XRDS file: " + sepUri.toString(), e);
					continue;
				}
				catch (IllegalArgumentException ee)
				{
					log.warn("Ignoring malformed EAUT Service endpoint URL in XRDS file: " + sepUri.toString(), ee);
					continue;
				}

				// /////////////////////////////////////
				// DETERMINE THE SERVICE URI TYPE
				// /////////////////////////////////////

				if (matchType(service, DiscoveryInformation.EAUT_SERVICE_URI__TEMPLATE))
				{
					discoveryInformation.setEautType(DiscoveryInformation.EAUT_SERVICE_TYPE.EAUT_TEMPLATE);
					if (log.isDebugEnabled())
					{
						log.debug("EAUT Server XRDS discovery result:\n" + discoveryInformation);
					}
					return discoveryInformation;
				}
				else if (matchType(service, DiscoveryInformation.EAUT_SERVICE_URI__MAPPING_SERVICE))
				{
					discoveryInformation.setEautType(DiscoveryInformation.EAUT_SERVICE_TYPE.EAUT_MAPPING_SERVICE);
					if (log.isDebugEnabled())
					{
						log.debug("EAUT Server XRDS discovery result:\n" + discoveryInformation);
					}
					return discoveryInformation;
				}
				else
				{
					log.error("No valid EAUT Service types were found in the supplied XRDS.  Ending EAUT processing.");
				}
			}// end while
		}// end while

		return discoveryInformation;

	}

	/**
	 * Determines if the specified service-type exists in the Service element.
	 * Deprecated in open-xri, copied here to avoid warnings.
	 * 
	 * @param service
	 * @param sVal
	 * @return
	 */
	private boolean matchType(Service service, String sVal)
	{
		for (int i = 0; i < service.getNumTypes(); i++)
		{
			if (service.getTypeAt(i).match(sVal))
				return true;
		}
		return false;
	}

}
