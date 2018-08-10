import pandas as pd
from selenium import webdriver
from bs4 import BeautifulSoup as bs
import time
url = 'https://www.glowpick.com/product/90571?gender=all&age=all&skin_type=all&rating=all&order=like_desc&contents='
browser = webdriver.Firefox(executable_path='geckodriver.exe')

browser.implicitly_wait(3)
browser.get(url)
getHtml = bs(browser.page_source, 'html.parser')

browser.maximize_window()

# SCROLL_PAUSE_TIME = 0.5
#
# # Get scroll height
# last_height = browser.execute_script("return document.body.scrollHeight")
#
# while True:
#     # Scroll down to bottom
#     browser.execute_script("window.scrollTo(0, document.body.scrollHeight);")
#
#     # Wait to load page
#     time.sleep(SCROLL_PAUSE_TIME)
#
#     # Calculate new scroll height and compare with last scroll height
#     new_height = browser.execute_script("return document.body.scrollHeight")
#     if new_height == last_height:
#         break
#     last_height = new_height

# url = getHtml.find_all('meta', {"itemprop":"url"})
# name = getHtml.find_all('meta', {"itemprop":"position"})
# for i, name in enumerate(name):
#     print(name['content'])
#     browser.get(url[i]['content'])





