/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.model;

import com.sappenin.eaut.exceptions.InvalidEmailAddressException;

/**
 * This class holds the local-part and domain part of the addr-spec portion of
 * an RFC-2822 email address.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public class ParsedEmailAddressImpl implements ParsedEmailAddress
{
	private String localPart;
	private String domain;

	/**
	 * The Default Constructor
	 */
	public ParsedEmailAddressImpl()
	{
		super();
	}

	/**
	 * The parameter constructor.
	 * 
	 * @param domain
	 * @param localPart
	 */
	public ParsedEmailAddressImpl(String localPart, String domain)
	{
		super();
		this.setLocalPart(localPart);
		this.setDomain(domain);
	}

	/**
	 * The Copy-Constructor.
	 * 
	 * @param parsedEmailAddress
	 */
	public ParsedEmailAddressImpl(ParsedEmailAddress parsedEmailAddress)
	{
		this(parsedEmailAddress.getLocalPart(), parsedEmailAddress.getDomain());
	}

	/**
	 * @return the localPart
	 */
	public String getLocalPart()
	{
		return localPart;
	}

	/**
	 * @param localPart the localPart to set
	 */
	public void setLocalPart(String localPart)
	{
		this.localPart = localPart;
	}

	/**
	 * @return the domain
	 */
	public String getDomain()
	{
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain)
	{
		this.domain = domain;
	}

	/**
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString()
	{
		return this.getLocalPart() + "@" + this.getDomain();
	}

	/**
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((domain == null) ? 0 : domain.hashCode());
		result = prime * result + ((localPart == null) ? 0 : localPart.hashCode());
		return result;
	}

	/**
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ParsedEmailAddressImpl other = (ParsedEmailAddressImpl) obj;
		if (domain == null)
		{
			if (other.domain != null)
				return false;
		}
		else if (!domain.equals(other.domain))
			return false;
		if (localPart == null)
		{
			if (other.localPart != null)
				return false;
		}
		else if (!localPart.equals(other.localPart))
			return false;
		return true;
	}

	// ////////////////////////////////////////////////////////////////
	// UTIL METHODS
	// ////////////////////////////////////////////////////////////////

	/**
	 * Takes an email address in String format, parses it per the EAUT
	 * specification, and returns a ParsedEmailAddress that holds the localPart
	 * and domain part of the email address. This implementation only supports
	 * email addresses of the form "local-part@domain", and does not require
	 * that the email address be properly formatted except for the requirement
	 * that the supplied email address string contain a '@' character.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws InvalidEmailAddressException
	 */
	public static ParsedEmailAddress parse(String emailAddress) throws InvalidEmailAddressException
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
