__author__ = 'kh1911'

import DataFetcher
import APIs.AmazonAPI

data = DataFetcher.getPageContent("http://www.flipkart.com/search?q=shirts&as=off&as-show=off&otracker=start")
print data
