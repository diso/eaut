/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.model;


/**
 * This interface defines the functionality of a model object which holds the
 * local-part and domain part of the addr-spec portion of an RFC-2822 email
 * address.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public interface ParsedEmailAddress
{
	/**
	 * @return the localPart
	 */
	public String getLocalPart();

	/**
	 * @param localPart the localPart to set
	 */
	public void setLocalPart(String localPart);

	/**
	 * @return the domain
	 */
	public String getDomain();

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain);
}
