/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.parser;

import com.sappenin.eaut.exceptions.InvalidEmailAddressException;
import com.sappenin.eaut.model.ParsedEmailAddress;

/**
 * 
 * A Java String-based email address parser.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 * 
 * @deprecated Use the parse() function on the ParsedEmailAddress Model Ojbect
 *             instead.
 */
@Deprecated
public interface EmailAddressParser
{
	/**
	 * Takes an email address, parses it per the EAUT specification, and returns
	 * a ParsedEmailAddress that holds the localPart and domain part of the
	 * email address, per RFC2822 and the EAUT specification.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws InvalidEmailAddressException
	 */
	public ParsedEmailAddress parseEmailAddress(String emailAddress) throws InvalidEmailAddressException;
}
