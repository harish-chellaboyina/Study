__author__ = 'kh1911'

import socket
import json
import DataFetcher
import APIs.AmazonAPI
import threading

threads = []
failedURLS = []
finalDB = {}
tcp_ip = "192.168.1.115"
tcp_port = "8010"


def getDataLength(connection):
    length = ""
    a = connection.recv(1)
    while a != ",":
        length += a
        a = connection.recv(1)
    return int(length)


def perform(keyword, pageRange):

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
            t = threading.Thread(target=something, args=(each, ))
            threads.append(t)
            t.start()

        page += 1

    print keywordDB


def executeTask(task):
    nextTask = None
    if task.has_key("task"):
        nextTask = task["task"]
    if nextTask == "StopServer":
        return "StopServer"
    keyword = task["keyword"]
    pageRange = task["pageRange"]
    perform(keyword, pageRange)
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
            c = 0
    return

def receiveTask(connection):
    length = getDataLength(connection)
    task = connection.recv(length)
    a = executeTask(json.loads(task))
    if a == "StopServer":
        return a
    output = json.dumps(finalDB)
    connection.send(str(len(output)) + "," + output)

def startServer():
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((tcp_ip,tcp_port))
    s.listen(1)
    conn, addr = s.accept()
    a = receiveTask(conn)
    if a == "StopServer":
        return
    else:
        startServer()


startServer()