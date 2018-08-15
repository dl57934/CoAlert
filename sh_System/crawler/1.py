import pandas as pd
from selenium import webdriver
from bs4 import BeautifulSoup as bs
import time

url = 'https://www.glowpick.com/beauty/ranking?id=25'
browser = webdriver.Firefox(executable_path='geckodriver.exe')

browser.implicitly_wait(5)
browser.get(url)
browser.maximize_window()
time.sleep(5)
getHtml = bs(browser.page_source, 'html.parser')
SCROLL_PAUSE_TIME = 0.5

type = {2: '건성', 3: '지성', 4: '중성', 5: '복합성', 6: '민감성'}
url = getHtml.find_all('meta', {"itemprop": "url"})
name = getHtml.find_all('meta', {"itemprop": "position"})
data = []

for i, content in enumerate(name):
    print(i, content)

for i, cos_name in enumerate(name[1:]):  # 화장품 이름, 설명 url
    print(i, cos_name['content'])
    # 좋아요 많은 순이면 좋겠지만 그건 다음에 계속
    # browser.find_element_by_css_selector('div.ui-selectbox:nth-child(3) > button:nth-child(1)').click()
    # browser.find_element_by_css_selector('.ui-list > li:nth-child(3) > div:nth-child(1) > button:nth-child(1)').click()
    for j in range(2, 7):  # 설명 url을 통해서 타입별 좋아요 순 많은 순서대로
        browser.get(url[2 * i + 3]['content'])
        time.sleep(1)
        browser.find_element_by_css_selector('.type > div:nth-child(2) > button:nth-child(1)').click()
        time.sleep(1)
        browser.find_element_by_css_selector(
            '.ui-list > li:nth-child(' + str(j) + ') > div:nth-child(1) > button:nth-child(1)').click()
        for k in range(1, 5):  # 리뷰 따오기
            review_length = len(browser.find_elements_by_css_selector('.review-list-wrap > li'))
            if review_length < k:
                break
            css_selector = '.review-list-wrap > li:nth-child(' + str(k) + ') > div:nth-child(1) > p:nth-child(2)'
            review = browser.find_element_by_css_selector(css_selector).text
            rate = browser.find_element_by_css_selector('p.score').text

            data.append({'popId': i, 'name': cos_name['content'], 'type': j, 'review': review, 'rate': rate})

print(data)

df = pd.DataFrame(data)

df['type'] = df['type'].map(type)
df.to_csv('top.csv', mode='w')
