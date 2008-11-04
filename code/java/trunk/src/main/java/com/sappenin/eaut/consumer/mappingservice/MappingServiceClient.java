/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.consumer.mappingservice;

import com.sappenin.eaut.exceptions.EAUTException;
import com.sappenin.eaut.model.ParsedEmailAddress;

/**
 * This interface defines the functionality of a EAUT Mapping Service, which
 * takes an email address as an Http GET parameter, and returns a URL that the
 * email address maps to via the EAUT protocol.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public interface MappingServiceClient
{
	/**
	 * This function communicates with an EAUT Mapping service via an HTTP get,
	 * provides the service an email address, and return the resulting URL, if
	 * found.
	 * 
	 * @param pae
	 * @return The URL that the specified Mapping Service indicates is the URL
	 *         of the specified email address.
	 * @throws EAUTException
	 */
	public String queryMappingService(ParsedEmailAddress pae, String getUrl) throws EAUTException;

}
