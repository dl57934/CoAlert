import pandas as pd
from selenium import webdriver
from bs4 import BeautifulSoup as bs
import time

url = 'https://www.glowpick.com/beauty/ranking?id=25'
browser = webdriver.Firefox(executable_path='geckodriver.exe')

browser.implicitly_wait(3)
browser.get(url)
getHtml = bs(browser.page_source, 'html.parser')

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

type = {1: '건성', 2: '지성', 3: '중성', 4: '복합성', 5: '민감성'}
url = getHtml.find_all('meta', {"itemprop": "url"})
name = getHtml.find_all('meta', {"itemprop": "position"})
data = []
for i, name in enumerate(name[1:]):
    print(name['content'])
    browser.get(url[i+1]['content'])
    for j in range(1, 5):
        browser.find_element_by_css_selector('.type > div:nth-child(2) > button:nth-child(1)').click()
        browser.find_element_by_css_selector('.ui-list > li:nth-child(2) > div:nth-child(1) > button:nth-child(' + str(j) + ')').click()
        # browser.find_element_by_css_selector(
        #     '.ui-list > li:nth-child(2) > div:nth-child(1) > button:nth-child(' + str(j+1) + ')') \
        #     .click()
        for k in range(0, 5):
            review = browser.find_element_by_css_selector('.review - list - wrap > li: nth - child('+str(k)+') > div:nth - child(1) > p: nth - child(2)').text
            .review - list - wrap > li: nth - child(1) > div:nth - child(1) > p: nth - child(2)
            rate = browser.find_element_by_css_selector('p.score').text
            data.append({'popId':i, 'type':j, 'review': review, 'rate': rate})

print(data)