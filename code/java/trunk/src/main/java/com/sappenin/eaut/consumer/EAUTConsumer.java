/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.consumer;

import com.sappenin.eaut.exceptions.EAUTException;

/**
 * This interface defines the functionality of a EAUT Processor, which acts as
 * the main Processor of all EAUT related workflow.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public interface EAUTConsumer
{
	/**
	 * Actually carries out the EAUT protocol on a paticular email address,
	 * ending up with a URL that the email address maps to.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws EAUTException
	 */
	public String doEAUT(String emailAddress) throws EAUTException;

}
