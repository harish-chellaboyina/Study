__author__ = 'kh1911'

import requests

import urllib2

def getPageContent(url):

    if url:
        try:
            print "Fetching Page Content for " + url
            response = requests.get(url)
            html = response.text
            return html

        except urllib2.HTTPError as err:
            return err
