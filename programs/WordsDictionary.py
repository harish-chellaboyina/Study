__author__ = 'kh1911'


def getAllWords():

    f= open("words.txt", "r")

    words = []

    while True:
        data = f.readline()
        data = data.rstrip()
        if data == '':
            break
        words.append(data)

    f.close()

    return words
