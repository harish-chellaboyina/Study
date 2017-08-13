__author__ = 'kh1911'

import masterServer
import threading
import json

keywords = ["laptops", "mobiles"]
keywordIndex = 0
pageRange = 2
KI = []
finalDB = {}
runningThreads = []


masterServer.connectToSlaveNodes()
slaveNodesLoad = {}

def getDataLength(connection):
    length = ""
    a = connection.recv(1)
    while a != ",":
        length += a
        a = connection.recv(1)
    return int(length)

def isSlaveNodeISFree(ip):
    if slaveNodesLoad.has_key(ip):
        return slaveNodesLoad[ip]["isFree"]
    return True

def freeSlaveNode(ip):
    if slaveNodesLoad.has_key(ip):
        slaveNodesLoad[ip]["isFree"] = True


def assignTaskToSlaveNode(connectionDetails, keyword, pageRange):
    connection = connectionDetails["connection"]
    task = '{"keyword": "'+ keyword +'", "pageRange": '+ str(pageRange) + '}'
    #task = str(task)
    print "Sending task " + task + "for " + connectionDetails["ip"]
    connection.send(str(len(task)) + "," + task)
    length = getDataLength(connection)
    print "Received length : ", length
    receivedData = connection.recv(length)
    print "Received data " + receivedData
    try:
        receivedData = json.loads(receivedData)
        keyword = receivedData["keyword"]
        finalDB[keyword] = receivedData["result"]
    except:
        print "Invalid data received from " + connectionDetails["ip"]
        pass
    freeSlaveNode(connectionDetails["ip"])

def assignTasksToSlaveNodes(keywordIndex):
    for each in masterServer.connectedSlaveNodes:
        if not isSlaveNodeISFree(each["ip"]):
            continue

        if len(keywords) > keywordIndex:
            slaveNodesLoad[each["ip"]] = {}
            slaveNodesLoad[each["ip"]]["isFree"] = False
            t = threading.Thread(target=assignTaskToSlaveNode, args=(each, keywords[keywordIndex], pageRange, ))
            runningThreads.append(t)
            t.start()
            #assignTaskToSlaveNode(each, keywords[keywordIndex], pageRange)
            keywordIndex += 1
    return keywordIndex


while len(keywords) != keywordIndex:
    keywordIndex = assignTasksToSlaveNodes(keywordIndex)

while True:
    c = 0
    for each in runningThreads:
        if each.isAlive():
            break
        else:
            c += 1
    if c == len(runningThreads):
        break
    else:
        c = 0

masterServer.stopAllSlaveNodes()
print json.dumps(finalDB)