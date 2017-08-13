from flask import Flask, jsonify

from flask import request

from flask import abort

from flask import make_response

from flask import Response
import DataFetcher
import json
from HTMLParser import HTMLParser
import APIs.AmazonAPI

app = Flask(__name__)




def convertAsciiTOString(ascii):
    ascii = ascii.split(',')
    result = ""
    for each in ascii:
        result += chr(int(each))
    return result

@app.route('/calculator/calculate/<string:taskname>', methods=['POST'])
def create_task(taskname):

    print "Received task - " + taskname


    values =  request.form.keys()[0]

    values = json.loads(values)
    url = values["url"]
    url = convertAsciiTOString(url)
    print "Received url : " + url
    data = DataFetcher.getPageContent(url)
    result = {}
    result["ProductName"] = APIs.AmazonAPI.getProductName(data)
    result["ProductPrice"] = APIs.AmazonAPI.getPrice(data)
    result["ProductImage"] = APIs.AmazonAPI.getProductImage(data)

    result = json.dumps(result)


    print result
    resp = Response(result)
    resp.headers['Access-Control-Allow-Origin'] = '*'
    return resp


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')