/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.consumer.template;

import com.sappenin.eaut.exceptions.EAUTException;
import com.sappenin.eaut.model.ParsedEmailAddress;

/**
 * A concrete impl of a TemplateMapper utilizes a Template Mapping URI and an
 * associated email address to create a EAUT resulting URL.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public class TemplateMapperImpl implements TemplateMapper
{
	/**
	 * Default Constructor
	 */
	public TemplateMapperImpl()
	{
		super();
	}

	/**
	 * This function transforms an email address into a URL by utilizing a
	 * templateURI.
	 * 
	 * @param pae
	 * @param templateURI
	 * @return
	 */
	@Override
	public String doMapping(ParsedEmailAddress pae, String templateURI) throws EAUTException
	{
		// Merely replace the '{userid}' string with the actual username from
		// the pae.
		// Example: http://{userid}.example.com ==> http://usernam.example.com

		if (templateURI == null || templateURI.length() <= 0)
		{
			throw new EAUTException("Unable to do EAUT mapping with a null/empty EAUT Mapping Template.");
		}

		String returnable = templateURI.replace("{username}", pae.getLocalPart());
		return returnable;
	}

}
