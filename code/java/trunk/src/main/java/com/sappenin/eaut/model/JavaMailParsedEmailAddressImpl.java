/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.model;

import javax.mail.internet.InternetAddress;

import com.sappenin.eaut.exceptions.InvalidEmailAddressException;

/**
 * This class holds the local-part and domain part of the addr-spec portion of
 * an RFC-2822 email address.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public class JavaMailParsedEmailAddressImpl extends ParsedEmailAddressImpl implements ParsedEmailAddress
{
	/**
	 * The Default Constructor
	 */
	public JavaMailParsedEmailAddressImpl()
	{
		super();
	}

	/**
	 * The parameter constructor.
	 * 
	 * @param domain
	 * @param localPart
	 */
	public JavaMailParsedEmailAddressImpl(String localPart, String domain)
	{
		super(localPart, domain);
	}

	/**
	 * The Copy-Constructor.
	 * 
	 * @param parsedEmailAddress
	 */
	public JavaMailParsedEmailAddressImpl(ParsedEmailAddress parsedEmailAddress)
	{
		super(parsedEmailAddress);
	}

	// ////////////////////////////////////////////////////////////////
	// UTIL METHODS
	// ////////////////////////////////////////////////////////////////

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
	public static final ParsedEmailAddress parseEmailAddress(String emailAddress) throws InvalidEmailAddressException
	{
		if (emailAddress == null || emailAddress.length() <= 0)
		{
			throw new InvalidEmailAddressException("Unable to parse null/empty email address.");
		}

		try
		{
			InternetAddress internetAddress = new InternetAddress(emailAddress);
			String validEmailAddress = internetAddress.getAddress();

			return ParsedEmailAddressImpl.parse(validEmailAddress);
		}
		catch (Exception e)
		{
			throw new InvalidEmailAddressException(e.toString(), e);
		}
	}

}
