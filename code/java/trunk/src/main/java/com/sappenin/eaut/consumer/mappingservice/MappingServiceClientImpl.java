/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.consumer.mappingservice;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sappenin.eaut.exceptions.EAUTException;
import com.sappenin.eaut.model.ParsedEmailAddress;

/**
 * This class is a concrete implementation of the MappingServiceClient that
 * takes an email address as an Http GET parameter, and returns a URL that the
 * email address maps to via the EAUT protocol.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public class MappingServiceClientImpl implements MappingServiceClient
{
	private static Log log = LogFactory.getLog(MappingServiceClientImpl.class);

	/**
	 * Default Constructor
	 */
	public MappingServiceClientImpl()
	{
		super();
	}

	/**
	 * This function communicates with an EAUT Mapping service via an HTTP get,
	 * provides the service an email address, and return the resulting URL, if
	 * found.
	 * 
	 * @param pae
	 * @return The URL that the specified Mapping Service indicates is the URL
	 *         of the specified email address.
	 * @throws EAUTException
	 */
	@Override
	public String queryMappingService(ParsedEmailAddress pae, String getUrl) throws EAUTException
	{
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(getUrl);
		get.setFollowRedirects(true);

		if (log.isDebugEnabled())
			log.debug("Performing HTTP GET on: " + getUrl + " ...");

		int statusCode;
		try
		{
			statusCode = client.executeMethod(get);
			if (statusCode != HttpStatus.SC_OK)
			{
				if (statusCode == HttpStatus.SC_BAD_REQUEST)
					throw new EAUTException("The Email Identifier supplied by '" + getUrl
						+ "' is not properly formatted per the EAUT spec and/or RFC2822 (Http Error 400)");
				else if (statusCode == HttpStatus.SC_INTERNAL_SERVER_ERROR)
					throw new EAUTException(
						"The EAUT Mapping Service was unable to complete a mapping request for URL: '" + getUrl
							+ "'.  Please try this request again at a later time.  (Http Error 500)");
				else
					throw new EAUTException("GET failed on " + getUrl + "(Http Error " + statusCode + ")");
			}
			else
			{
				String redirectLocation = get.getURI().toString();
				if (redirectLocation != null)
				{
					return redirectLocation;
				}
				else
				{
					// The response is invalid and did not provide the new
					// location
					// for the resource. Report an error or possibly handle the
					// response like a 404 Not Found error.
					throw new EAUTException("GET failed on " + getUrl + "(Http Error " + statusCode + ")");
				}

			}
		}
		catch (EAUTException ee)
		{
			throw ee;
		}
		catch (HttpException he)
		{
			throw new EAUTException(he);
		}
		catch (IOException ioe)
		{
			throw new EAUTException(ioe);
		}
	}
}
