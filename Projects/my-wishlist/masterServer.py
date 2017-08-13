__author__ = 'kh1911'

import socket

slaveNodeIPs = [{'ip':'192.168.1.2', 'port': 8010}]
connectedSlaveNodes = []



def connectServer(tcp_ip,tcp_port):
    print "Trying to connect to " + tcp_ip + ":" + str(tcp_port)
    tcp_port = int(tcp_port)
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((tcp_ip,tcp_port))
    return s

def stopAllSlaveNodes():
    for each in connectedSlaveNodes:
        connection = each["connection"]
        task = '{"task":"StopServer"}'
        connection.send(str(len(task)) + "," + task)

def connectToSlaveNodes():
    for each in slaveNodeIPs:
        s = connectServer(each["ip"], each["port"])
        connectedSlaveNodes.append({"connection": s, "ip": each["ip"], "port": each["port"]})
        print "Connected successfully to " + each["ip"] + ":" + str(each["port"])