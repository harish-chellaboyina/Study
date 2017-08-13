__author__ = 'kh1911'

import DataFetcher
import APIs.AmazonAPI
import thread
import time
import threading

keywords = ["wallets"]
pageRange = 2
threads = []

threadsCompleted = 0

finalDB = {}
failedURLS = []

startTime =  time.time()
def perform(keyword):
    print "Received keyword : " + keyword
    keywordDB = {}

    page = 1

    while page <= pageRange:
        if page == 1:
            searchURL = "http://www.amazon.in/s/?url=search-alias%3Daps&field-keywords=" + keyword
        else:
            searchURL = "http://www.amazon.in/s/?url=search-alias%3Daps&field-keywords=" + keyword + "&page=" + str(page)

        response = DataFetcher.getPageContent(searchURL)

        urls = APIs.AmazonAPI.getListURLSofListPage(response)

        def something(pageURL):
            pageContent = DataFetcher.getPageContent(pageURL)
            productDetails =  APIs.AmazonAPI.getProductDetails(pageContent)
            if productDetails and productDetails.has_key("name"):
                keywordDB[productDetails["name"]] = productDetails["price"]
                print productDetails
                finalDB[keyword] = keywordDB
            else:
                failedURLS.append(pageURL)

        for each in urls:
            something(each)
            """
            t = threading.Thread(target=something, args=(each, ))
            threads.append(t)
            t.start()"""

        page += 1

    print keywordDB




for keyword in keywords:
    perform(keyword)
    """t = threading.Thread(target=perform, args=(keyword,))
    threads.append(t)
    t.start()"""

"""
while True:
    c = 0
    for each in threads:
        if each.isAlive():
            break
        else:
            c += 1
    if c == len(threads):
        break
    else:
        c = 0"""

f = open("DB.txt", "w")
f.write(str(finalDB))
f.close()
print finalDB
print "Time taken : " + str(time.time() - startTime)
print failedURLS
