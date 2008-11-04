/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.parser.impl;

import com.sappenin.eaut.exceptions.InvalidEmailAddressException;
import com.sappenin.eaut.model.ParsedEmailAddress;
import com.sappenin.eaut.model.ParsedEmailAddressImpl;

/**
 * A Java String-based email address parser.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 * 
 * @deprecated Use the parse() function on the ParsedEmailAddress Model Ojbect
 *             instead.
 */
@Deprecated
public class EmailAddressParserImpl
{
	/**
	 * Default Constructor
	 */
	public EmailAddressParserImpl()
	{
		super();
	}

	/**
	 * Takes an email address, parses it per the EAUT specification, and returns
	 * a ParsedEmailAddress that holds the localPart and domain part of the
	 * email address. This implementation only supports email addresses of the
	 * form "local-part@domain", and does not require that the email address be
	 * properly formatted except for the requirement that the supplied email
	 * address strign contain only 1 '@' character.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws InvalidEmailAddressException
	 */
	public ParsedEmailAddress parse(String emailAddress) throws InvalidEmailAddressException
	{
		if (emailAddress == null || emailAddress.length() <= 0)
		{
			throw new InvalidEmailAddressException("Unable to parse null/empty email address.");
		}

		try
		{
			String[] splits = emailAddress.split("@");
			if (splits.length != 2)
			{
				throw new InvalidEmailAddressException("Invalid Email-Address (" + emailAddress
					+ "):  Source Email Address MUST contain only 1 '@' character.");
			}

			String localPart = splits[0];
			String domain = splits[1];

			ParsedEmailAddress pae = new ParsedEmailAddressImpl(localPart, domain);

			return pae;
		}
		catch (Exception e)
		{
			throw new InvalidEmailAddressException(e.toString(), e);
		}
	}
}
