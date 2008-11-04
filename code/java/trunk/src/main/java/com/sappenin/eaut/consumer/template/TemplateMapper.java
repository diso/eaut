/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.consumer.template;

import com.sappenin.eaut.exceptions.EAUTException;
import com.sappenin.eaut.model.ParsedEmailAddress;

/**
 * This interface defines the functionality for utilizing a Template Mapping URI
 * and an associated email address to create a EAUT resulting URL.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public interface TemplateMapper
{
	/**
	 * This function transforms an email address into a URL by utilizing a
	 * templateURI.
	 * 
	 * @param pae
	 * @param templateURI
	 * @return
	 */
	public String doMapping(ParsedEmailAddress pae, String templateURI) throws EAUTException;
}
