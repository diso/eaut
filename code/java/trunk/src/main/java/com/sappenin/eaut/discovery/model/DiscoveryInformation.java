/*
 * Copyright (C) 2008-2009 Sappenin Technologies LLC
 * http://www.sappenin.com/products/eaut
 */
package com.sappenin.eaut.discovery.model;

/**
 * Holds information about a particular Domain's discovered EAUT information.
 * 
 * @author David Fuelling
 * @license http://www.apache.org/licenses/LICENSE-2.0 Apache
 */
public interface DiscoveryInformation
{
	public final static String EAUT_SERVICE_URI__TEMPLATE = "http://specs.eaut.org/1.0/template";
	public final static String EAUT_SERVICE_URI__MAPPING_SERVICE = "http://specs.eaut.org/1.0/mapping";

	public static enum EAUT_SERVICE_TYPE
	{
		EAUT_TEMPLATE, EAUT_MAPPING_SERVICE
	};

	/**
	 * @return the serviceURL
	 */
	public String getServiceURL();

	/**
	 * @param serviceURL the serviceURL to set
	 */
	public void setServiceURL(String serviceURL);

	/**
	 * @return the eautType
	 */
	public EAUT_SERVICE_TYPE getEautType();

	/**
	 * @param eautType the eautType to set
	 */
	public void setEautType(EAUT_SERVICE_TYPE eautType);
}
