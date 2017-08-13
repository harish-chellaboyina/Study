__author__ = 'kh1911'


class Product:

    def __init__(self):
        self.productName = None
        self.price = None
        self.imageURL = None

    def getProductName(self):
        return self.productName

    def setProductName(self, productName):
        self.productName = productName

    def getPrice(self):
        return self.price

    def setPrice(self, price):
        self.price = price

    def getImageURL(self):
        return self.imageURL

    def setImageURL(self, URL):
        self.imageURL = URL