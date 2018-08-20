import pandas as pd
from selenium import webdriver
from bs4 import BeautifulSoup as bs
import time
from urllib import request
import os


if not os.path.exists('./img'):
    os.mkdir('img')

url = 'https://www.glowpick.com/beauty/ranking?id=25'
browser = webdriver.Firefox(executable_path='geckodriver.exe')

browser.get(url)
time.sleep(5)

getHtml = bs(browser.page_source, 'html.parser')


imgTag = getHtml.find_all('img')[2:102]
print(len(imgTag))

for i, img in enumerate(imgTag):
    cosmeticName = str(img['alt']).replace('|', '_')
    cosmeticName = cosmeticName.replace('/', ' ')
    request.urlretrieve(img.get('src'), './img/'+str(i)+'. '+cosmeticName+'.jpg')

