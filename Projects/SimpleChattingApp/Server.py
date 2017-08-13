from socket import *

import thread
import json

serverName = 'localhost'
serverPort = 8041
availableClientsList = {}


def chatNameExists(chatName):
    if chatName in availableClientsList.values():
        return True
    return False

def sendToOneChat(chatName, message, senderConnection):
    senderChatName = availableClientsList[senderConnection]
    for conn in availableClientsList:
        if availableClientsList[conn] == chatName:
            sendMessage(conn, "[Whisper] " + senderChatName + " ----- " + message)
            return

def sendMsgToAll(senderConnection, message):
    if senderConnection != None:
        chatName = availableClientsList[senderConnection]
    else:
        chatName = "@@Server@@"
    for conn in availableClientsList.keys():
        if conn != senderConnection:
            sendMessage(conn, chatName + "  -----  " + message)

def receiveMessage(connectionSocket, sendToAll):
    msgLen = ""
    temp = connectionSocket.recv(1)
    while temp != "$":
        msgLen += temp
        temp = connectionSocket.recv(1)

    messageJSON = connectionSocket.recv(int(msgLen))
    messageJSON = json.loads(messageJSON)
    message = messageJSON["message"]
    whisperChatName = None
    if messageJSON.has_key("whisperChatName"):
        whisperChatName = messageJSON["whisperChatName"]



    if message == "quit":
        print availableClientsList[connectionSocket] + " left the chat"
        connectionSocket.close()
        leftChatName = availableClientsList[connectionSocket]
        availableClientsList.pop(connectionSocket)
        sendMsgToAll(None, leftChatName + " left the chat")
        return

    if whisperChatName != None and chatNameExists(whisperChatName):
        print "Sending message only to  " + whisperChatName
        sendToOneChat(whisperChatName, message, connectionSocket)
        receiveMessage(connectionSocket, True)

    if sendToAll:
        if availableClientsList.has_key(connectionSocket):
            print "Message received from  " + availableClientsList[connectionSocket] + " --  " + message
            sendMsgToAll(connectionSocket, message)
            receiveMessage(connectionSocket, True)
    else:
        return message


def sendMessage(connection, message):
    message = str(len(message)) + "$" + message
    connection.send(message)



serverSocket = socket(AF_INET, SOCK_STREAM)
serverSocket.bind((serverName, serverPort))
print "Server started... Listening at " + str(serverPort)

while 1:
    serverSocket.listen(1)
    connectionSocket, addr = serverSocket.accept()
    chatName = receiveMessage(connectionSocket, False)
    print "Chat added -- " + chatName
    sendMsgToAll(None, chatName + " added to chat")
    availableClientsList[connectionSocket] = chatName
    thread.start_new_thread( receiveMessage, (connectionSocket, True) )


def takeInput():
    print "Enter input : "
    a = raw_input()
    print a

for i in range(10):
    thread.start_new_thread(takeInput, ())
