/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.parser.impl;

import javax.mail.internet.InternetAddress;

import com.sappenin.eaut.exceptions.InvalidEmailAddressException;
import com.sappenin.eaut.model.ParsedEmailAddress;

/**
 * A JavaMail based email address parser.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 * 
 * @deprecated Use the parse() function on the ParsedEmailAddress Model Ojbect
 *             instead.
 */
@Deprecated
public class JavaMailEmailAddressParserImpl extends EmailAddressParserImpl
{

	/**
	 * Takes an email address, parses it per the EAUT specification, and returns
	 * a ParsedEmailAddress that holds the localPart and domain part of the
	 * email address. This implementation utilizes the JavaMail libraries to do
	 * initial parsing of the supplied string, as well as handle any email
	 * validation. Using JavaMail, callers of this function can utilize email
	 * addresses that contain personal-name information, as well as other
	 * portions of the full RFC2822 email address.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws InvalidEmailAddressException
	 */
	public ParsedEmailAddress parseEmailAddress(String emailAddress) throws InvalidEmailAddressException
	{
		if (emailAddress == null || emailAddress.length() <= 0)
		{
			throw new InvalidEmailAddressException("Unable to parse null/empty email address.");
		}

		try
		{
			InternetAddress internetAddress = new InternetAddress(emailAddress);
			String validEmailAddress = internetAddress.getAddress();

			return super.parse(validEmailAddress);
		}
		catch (Exception e)
		{
			throw new InvalidEmailAddressException(e.toString(), e);
		}
	}
}
