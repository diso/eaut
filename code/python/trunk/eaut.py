"""python-eaut

Utilities for transforming an email address into an OpenID following the
Email-to-OpenID translation specification.

Requirements: OpenID 2.0 library, downloadable from:
http://openidenabled.com/python-openid/
"""

__version__ = "0.1"
__author__ = 'Michael Richardson'
__email__ = "michael.richardson@vidoop.com"

import urllib
from urlparse import urlparse, urlunparse

from openid.yadis.services import getServiceEndpoints
from openid.yadis.discover import DiscoveryFailure
from openid.fetchers import HTTPFetchingError

TEMPLATE_TYPE = 'http://specs.eaut.org/1.0/template'
MAPPER_TYPE = 'http://specs.eaut.org/1.0/mapping'
FALLBACK_SERVICE = 'http://emailtoid.net/'

VALID_TYPES = [TEMPLATE_TYPE, MAPPER_TYPE]

class NoValidEndpoints(Exception):
    """ Exception for if a site has no valid email translation endpoints. """


class OpenIDTranslationError(Exception):
    """ OpenID translation exception """


def get_valid_services(url):
    """ 
    Discover an XRDS document and return valid email transformation endpoints.
    
    If none are found, raise a NoValidEndpoints exception.
    
    """
    try:
        domain, service_endpoints = getServiceEndpoints(url)
        matched_endpoints = [i for i in service_endpoints if i.matchTypes(VALID_TYPES)]
    except (HTTPFetchingError, DiscoveryFailure):
        matched_endpoints = None
    if not matched_endpoints:
        raise NoValidEndpoints('No valid endpoints found at %s' %(url))
    return matched_endpoints

def return_transform_information(matched_endpoints):
    """ 
    Take the first service endpoint from a list of matched endpoints and 
    return its Transformation Type and URI.
    
    """
    transform_type = matched_endpoints[0].matchTypes(VALID_TYPES)[0]
    uri = matched_endpoints[0].uri
    return transform_type, uri

def return_openid(email_address, site_name='', fallback=FALLBACK_SERVICE):
    """ Transform an email address into an OpenID.
    
    Accept a well-formed email address and return a string of the resulting
    OpenID.
    
    If an XRDS document is not found from the email address's domain, or if
    there is no eaut type, use the fallback service of
    http://emailtoid.net.
    
    If the fallback service is used, the optional keyword argument of
    site_name will be appended to the GET request which makes for a more
    customized user experience.
    
    """
    email_username, email_domain = email_address.split('@')
    basic_url = 'http://%s' % email_domain
    www_url = 'http://www.%s' % email_domain
    for url in [basic_url, www_url, fallback]:
        try:
            matched_endpoints = get_valid_services(url)
            break
        except (DiscoveryFailure, NoValidEndpoints):
            continue
    transform_type, uri = return_transform_information(matched_endpoints)
    openid_url = ''
    if transform_type == TEMPLATE_TYPE:
        encoded_uri = urllib.unquote(uri)
        openid_url = encoded_uri.replace('{username}', email_username)
    elif transform_type == MAPPER_TYPE:
        scheme, netloc, path, params, query, fragment = urlparse(uri)
        if query:
            openid_url = '%s&email=%s' %(uri, email_address)
        else:
            openid_url = '%s?email=%s' %(uri, email_address)
        if site_name and FALLBACK_SERVICE in openid_url:
            openid_url += '&site_name=%s' % site_name
    if not openid_url:
        raise OpenIDTranslationError('No transform type found.')
    return openid_url
