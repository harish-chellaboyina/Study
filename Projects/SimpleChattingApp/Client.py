from socket import *
import thread
import json

serverName = 'localhost'
serverPort = 8041

def receiveMessage(connectionSocket):
    msgLen = ""
    temp = connectionSocket.recv(1)
    while temp != "$":
        msgLen += temp
        temp = connectionSocket.recv(1)

    message = connectionSocket.recv(int(msgLen))
    print message
    receiveMessage(connectionSocket)


def sendMessage(connection, message, whisperChatName):

    messageJSON = {"message": message}
    if whisperChatName != None:
        messageJSON["whisperChatName"] = whisperChatName
    messageJSON = str(len(json.dumps(messageJSON))) + "$" + json.dumps(messageJSON)
    connection.send(messageJSON)

def sendMessage(connection, message, whisperChatName):

    messageJSON = {"message": message}
    if whisperChatName != None:
        messageJSON["whisperChatName"] = whisperChatName
    messageJSON = str(len(json.dumps(messageJSON))) + "$" + json.dumps(messageJSON)
    connection.send(messageJSON)


clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort))
chatName = raw_input("Enter chat name : ")
sendMessage(clientSocket, chatName, None)

thread.start_new_thread( receiveMessage, (clientSocket, ) )

while 1:
    msg = raw_input()
    if msg.startswith("@"):
        whisperChatName = msg[1:msg.index(" ")]
        whisperChatName.strip()
        if whisperChatName != "":
            choice = raw_input("Do you want to send the message only to " + whisperChatName + "?? (Y/N) ")
            if choice == 'Y' or choice == 'y':
                msg = msg[len(whisperChatName) + 2:]
                sendMessage(clientSocket, msg, whisperChatName)
                continue

    if msg == "quit":
        sendMessage(clientSocket, msg, None)
        clientSocket.close()
        break

    sendMessage(clientSocket, msg, None)
