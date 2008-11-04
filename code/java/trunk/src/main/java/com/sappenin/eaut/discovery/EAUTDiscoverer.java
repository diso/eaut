/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.discovery;

import com.sappenin.eaut.discovery.model.DiscoveryInformation;
import com.sappenin.eaut.exceptions.EAUTException;
import com.sappenin.eaut.model.ParsedEmailAddress;

/**
 * An interface that defines the functionality of a EAUTDiscoverer, which
 * performs EAUT Discovery on a particular email address.
 * 
 * @author David Fuelling
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public interface EAUTDiscoverer
{

	/**
	 * Performs EAUT Discovery on a ParsedEmailAddress.
	 * 
	 * @param pae
	 * @return
	 * @throws EAUTException
	 */
	public DiscoveryInformation discover(ParsedEmailAddress pae) throws EAUTException;

}
