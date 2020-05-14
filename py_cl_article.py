
#https://cl.pr26.xyz/htm_data/2005/20/3919456.html
import requests
from bs4 import BeautifulSoup

_name_='_main_'
url='https://cl.pr26.xyz/htm_data/2005/20/3919456.html'
headers={
        'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36'
        }


def getContentByUrl(url):
    res = requests.get(url, headers=headers)
    # 设置编码方式防止乱码 https://blog.csdn.net/zb19941113/article/details/78461546
    res.encoding = res.apparent_encoding
    soup = BeautifulSoup(res.text, 'html.parser')
    div = soup.find_all('div',class_='t t2')
    
    author = div.find_all('tr',class_='tr1 do_not_catch')

    print(div)


#把内容保存到txt文档
def writeToTxt():

    return

if _name_ =='_main_':
    #省略获取总页数
    #https://cl.pr26.xyz/read.php?tid=3919456&page=2
    getContentByUrl(url)

    print("hello world")