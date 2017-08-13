__author__ = 'kh1911'

import sys

def getPrice(data):
    try:
        i = data.index('"priceblock_ourprice"')
        #j = data.index("regularprice_savings")
        reduced = data[i:]
        t = reduced.index('</span>')
        reduced = reduced[t + 7:]
        i = reduced.index('</span')
        return reduced[0:i].lstrip().split(" ")[0]
    except ValueError as ve:
        try:
            i = data.index('"priceblock_dealprice"')
            #j = data.index("dealprice_savings")
            reduced = data[i:]
            t = reduced.index('</span>')
            reduced = reduced[t + 7:]
            i = reduced.index('</span')
            return reduced[0:i].lstrip().split(" ")[0]

        except:
            try:
                i = data.index('"priceblock_saleprice"')
                #j = data.index("regularprice_savings")
                reduced = data[i:]
                t = reduced.index('</span>')
                reduced = reduced[t + 7:]
                i = reduced.index('</span')
                return reduced[0:i].lstrip().split(" ")[0]

            except:
                print "Invalid page content passed to getPrice"
    except:
        print "Invalid page content passed to getPrice"

def getProductName(data):
    try:
        i = data.index('"productTitle"')
        j = data.index('</span>', i)
        reduced = data[i:j]
        t = reduced.index('>')
        reduced = reduced[t + 1:]
        reduced = reduced.lstrip()
        return reduced
    except:
        print "Invalid page content passed to getProductName"


def getProductImage(data):
    try:
        i = data.index('"imgTagWrapperId"')
        data = data[i:]
        i = data.index('img alt="')
        reduced = data[i:]
        j = reduced.index('src="')
        reduced = reduced[j+5:]
        j = reduced.index('"')
        reduced = reduced[:j]
        return reduced
    except:
        print "Invalid page content passed to getProductImage"

def getProductDetails(pageContent):
    name = getProductName(pageContent)
    price = getPrice(pageContent)
    image = getProductImage(pageContent)
    if name and price and image:
        return {"name":name,
                "price": price,
                "image": image}


    """
        1. Fetch the page content from the URL
        2. Validate the content. It is required when the user gives a wrong url. Throw an error to the user if a wrong url is provided
        3. If the validation is passed, parse the data and create and return the product object with product name,  price, imageURL. """


def getTop5Products(productOBJ):
    """
        Creates a url with product name, gets the data, parses and gets the top 5 products.
         Return them as product objects
    """



def getListURLSofListPage(page):
    index = 0
    urls = []
    print page
    while index < len(page):
        try:
            index = page.index("result_", index)

            try:
                a = int(page[index + 7])
                startIndex = index + 7
                try:
                    endIndex = page.index("result_", startIndex)
                    if endIndex < startIndex:
                        endIndex = len(page)
                except:
                    endIndex = len(page)

                itemString = page[startIndex:endIndex]
                try:
                    a = itemString.index("a-link-normal a-text-normal")
                    urlStart = itemString.index("href=", a) + 6
                    urlEnd = itemString.index('"', urlStart)
                    url = itemString[urlStart: urlEnd]
                    print "Adding url : " + url
                    urls.append(url)
                    index = endIndex
                    continue
                    #print itemString
                except:
                    index = endIndex
                    pass

            except:
                #Not a number
                index += 1
                pass
        except:
            #Done with the page
            return urls

    print "Returning URLs"
    return urls