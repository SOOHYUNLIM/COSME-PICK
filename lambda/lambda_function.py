
import sys
import logging
import pymysql
import urllib.request
import requests
from bs4 import BeautifulSoup

def lambda_handler(event, context):
    conn = pymysql.connect(host='django.db.backends.mysql', user='bit04', passwd='1234', db='jarvis')
    curs = conn.cusor()
    sql = 'select now()'
    curs.execute(sql)
    rows = curs.fetchall()
    for row in rows:
         item = NaverAPI.findNaverAPI(row)
         print(item)
   return {
        'statusCode': 200,
        'body': json.dumps('Hello from Lambda!')
    }



class CrawlingUtil:
    def crawl(url):
        req = requests.get(url)
        html = req.text
        return BeautifulSoup(html, 'html.parser')

    def findApplyShop(input):
        inputShoppingMall = input.split(".")[1]
        if inputShoppingMall in applyShoppingMall:
            soup = CrawlingUtil.crawl(input)
            itemNameAndImg = None
            if inputShoppingMall == "11st":
                itemNameAndImg = {'title': soup.select_one('.heading > h2').text, 'img': soup.select_one('.v-align > img').get('src')}
            elif inputShoppingMall == "gmarket":
                itemNameAndImg = soup.select_one('.itemtit').text
            elif inputShoppingMall == "auction":
                itemNameAndImg = soup.select_one('.text__item-title').text
            elif inputShoppingMall == "wemakeprice":
                itemNameAndImg = soup.select_one('.deal_tit').text
            return itemNameAndImg


class NaverAPI:
    def findSearchNaver(item):
        url = 'https://search.shopping.naver.com/' + CrawlingUtil.crawl(item['link']).select_one('script').text.strip().split("'")[1]
        info = Crawling.crawl(url).select_one('._priceListMallLogo')
        return {'title':item['title'], 'lprice':item['lprice'], 'mallName':info.get('data-mall-name'), 'link':info.get('href')}

    def findNaverAPI(itemName):
        url = "https://openapi.naver.com/v1/search/shop?query=" + urllib.parse.quote(itemName)  # json 결과
        request = urllib.request.Request(url)
        request.add_header("X-Naver-Client-Id", "NrksAQQEffia0Ek4iYdi")
        request.add_header("X-Naver-Client-Secret", "dopvU8BXFH")
        response = urllib.request.urlopen(request)
        rescode = response.getcode()
        if (rescode == 200):
            response_body = response.read()
        else:
            print("Error Code:" + rescode)

        #예외처리 필요
        items = json.loads(response_body)["items"]
        result = None

        for item in items:
            if result is None:
                result = item
            result = item if int(item["lprice"]) < int(result["lprice"]) else result

        if result["productType"] == "1":
            result = NaverAPI.findSearchNaver(result)
        result["title"] = result["title"].replace("<b>", "").replace("</b>", "")
        return result


applyShoppingMall = ["11st", "gmarket", "auction", "wemakeprice"]


