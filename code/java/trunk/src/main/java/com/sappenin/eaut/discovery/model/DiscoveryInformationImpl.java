/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.discovery.model;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Holds information about a particular Domain's discovered EAUT information.
 * 
 * @author David Fuelling
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public class DiscoveryInformationImpl implements DiscoveryInformation
{
	private Log log = LogFactory.getLog(this.getClass());

	private String serviceURL;
	private EAUT_SERVICE_TYPE eautType;

	/**
	 * Default Constructor.
	 */
	public DiscoveryInformationImpl()
	{
		super();
		// Default to the EmailToId Mapping Service.
		this.setEautType(DiscoveryInformation.EAUT_SERVICE_TYPE.EAUT_MAPPING_SERVICE);
	}

	/**
	 * @return the serviceURL
	 */
	public String getServiceURL()
	{
		return serviceURL;
	}

	/**
	 * @param serviceURL the serviceURL to set
	 */
	public void setServiceURL(String serviceURL)
	{
		this.serviceURL = this.decodePercent(serviceURL);
	}

	/**
	 * @return the eautType
	 */
	public EAUT_SERVICE_TYPE getEautType()
	{
		return eautType;
	}

	/**
	 * @param eautType the eautType to set
	 */
	public void setEautType(EAUT_SERVICE_TYPE eautType)
	{
		this.eautType = eautType;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "EAUT Type: " + this.getEautType().toString() + "; Service URL: " + this.getServiceURL();
	}

	/**
	 * Decodes the percent encoding scheme. <br/> For example:
	 * "an+example%20string" -> "an example string". See
	 * http://elonen.iki.fi/code/nanohttpd/NanoHTTPD.java for source.
	 */
	private String decodePercent(String str)
	{
		try
		{
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < str.length(); i++)
			{
				char c = str.charAt(i);
				switch (c)
				{
					case '+':
						sb.append(' ');
						break;
					case '%':
						sb.append((char) Integer.parseInt(str.substring(i + 1, i + 3), 16));
						i += 2;
						break;
					default:
						sb.append(c);
						break;
				}
			}
			return new String(sb.toString().getBytes());
		}
		catch (Exception e)
		{
			log.error("Bad percent-encoding: " + e.toString());
			return null;
		}
	}

}
