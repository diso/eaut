/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.exceptions;

/**
 * Thrown by EAUT code when an Invalid EmailAddress is encountered.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public class InvalidEmailAddressException extends EAUTException
{
	private static final long serialVersionUID = 8187632895495571130L;

	/**
	 * Default Constructor
	 */
	public InvalidEmailAddressException()
	{
		super();
	}

	/**
	 * @param message
	 */
	public InvalidEmailAddressException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidEmailAddressException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidEmailAddressException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
