/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.discovery.xrd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sappenin.eaut.discovery.EAUTDiscoverer;
import com.sappenin.eaut.discovery.model.DiscoveryInformation;
import com.sappenin.eaut.exceptions.EAUTException;
import com.sappenin.eaut.model.ParsedEmailAddress;

/**
 * A Concrete EAUTDiscoverer that utilizes XRD to perform EAUT Discovery
 * (Draft 6 of the EAUT spec).
 * 
 * @author David Fuelling
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */

public class XRDEAUTDiscoverer implements EAUTDiscoverer
{
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * The Default Constructor
	 */
	public XRDEAUTDiscoverer()
	{
		super();
	}

	@Override
	public DiscoveryInformation discover(ParsedEmailAddress pae) throws EAUTException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
