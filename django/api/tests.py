



import sys
import logging
import pymysql
import json

def lambda_handler(event, context):
    conn = pymysql.connect(host='django.db.backends.mysql', user='bit04', passwd='1234', db='jarvis')
    curs = conn.cursor()
    sql = 'select now()'
    # sql2 = "insert into tbl_product(price, title) values (%s, %s)"
    curs.execute(sql)
    # conn.commit()
    # curs.execute(sql)
    rows = curs.fetchall()
    for row in rows:
	    print(row)
    # conn.close()
    # return {
    #     'statusCode': 200,
    #     'body': json.dumps('Hello from Lambda!')
    # }

