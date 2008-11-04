/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.exceptions;

/**
 * Thrown by EAUT code when a generic EAUT error occurs.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public class EAUTException extends Exception
{
	private static final long serialVersionUID = 8187632895495571130L;

	/**
	 * Default Constructor
	 */
	public EAUTException()
	{
		super();
	}

	/**
	 * @param message
	 */
	public EAUTException(String message)
	{
		super(message);
	}

	/**
	 * @param cause
	 */
	public EAUTException(Throwable cause)
	{
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public EAUTException(String message, Throwable cause)
	{
		super(message, cause);
	}

}
