/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.consumer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sappenin.eaut.consumer.mappingservice.MappingServiceClientImpl;
import com.sappenin.eaut.consumer.template.TemplateMapperImpl;
import com.sappenin.eaut.discovery.model.DiscoveryInformation;
import com.sappenin.eaut.discovery.yadis.YadisEAUTDiscoverer;
import com.sappenin.eaut.exceptions.EAUTException;
import com.sappenin.eaut.model.JavaMailParsedEmailAddressImpl;
import com.sappenin.eaut.model.ParsedEmailAddress;

/**
 * This class is a concrete implementation of the EAUTConsumer.
 * 
 * @author David Fuelling (sappenin@gmail.com)
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public class EAUTConsumerImpl implements EAUTConsumer
{
	@SuppressWarnings("unused")
	private Log log = LogFactory.getLog(this.getClass());

	/**
	 * Default Constructor.
	 */
	public EAUTConsumerImpl()
	{
		super();
	}

	/**
	 * 1.) Parse the email address string into a ParsedEmailAddress.<BR>
	 * 2.) Retrieve the XRDS file by doing EAUT Discovery on the email address.<BR>
	 * 3.) Resolve the mapped URL via a Template or a Mapping Service.<BR>
	 * 
	 * @param emailAddress The emailAddress to perform EAUT transformation on.
	 * @return A String representing the resolved URL.
	 * @throws EAUTException
	 */
	@Override
	public final String doEAUT(String emailAddress) throws EAUTException
	{
		// 1.) Parse the email address to get a discovery URL.
		ParsedEmailAddress pae = JavaMailParsedEmailAddressImpl.parseEmailAddress(emailAddress);

		DiscoveryInformation discoveryInformation = new YadisEAUTDiscoverer().discover(pae);

		if (DiscoveryInformation.EAUT_SERVICE_TYPE.EAUT_MAPPING_SERVICE.equals(discoveryInformation.getEautType()))
		{
			return new MappingServiceClientImpl().queryMappingService(pae, discoveryInformation.getServiceURL());

		}
		else if (DiscoveryInformation.EAUT_SERVICE_TYPE.EAUT_TEMPLATE.equals(discoveryInformation.getEautType()))
		{
			return new TemplateMapperImpl().doMapping(pae, discoveryInformation.getServiceURL());
		}
		else
		{
			// EAUT is not supported on this emailAddress.
			throw new EAUTException("EAUT is not supported on the emailAddress '" + emailAddress + "'");
		}
	}
}
