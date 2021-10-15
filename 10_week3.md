# 모델 정확도 향상
각 인테리어 스타일의 이미지 데이터를 점검하여 정확도를 향상

## 네이버 웹 페이지 크롤링
: zen, industrial 스타일의 이미지 데이터를 추가로 수집하여 데이터의 양을 증가시킨다.

✔ 이미지 클릭이 안되는 오류 발생 → stack overflow의 글 참고 [here](https://stackoverflow.com/questions/48614856/python-webdriver-is-not-working-with-css-selector)

### 사용한 코드
```python
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import urllib.request

driver = webdriver.Chrome()

# 네이버 웹 페이지로 이동
driver.get("https://search.naver.com")

# 키워드로 검색
elem = driver.find_element_by_name("query")
elem.send_keys("젠 스타일 인테리어")
elem.send_keys(Keys.RETURN)

# 이미지 탭 선택
tab = driver.find_element_by_xpath(
    "/html/body/div[3]/div[1]/div/div[2]/div[1]/div/ul/li[3]/a")
tab.click()

# 스크롤 끝까지 이동
SCROLL_PAUSE_TIME = 1

# Get scroll height
last_height = driver.execute_script("return document.body.scrollHeight")

while True:
    # Scroll down to bottom
    driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")

    # Wait to load page
    time.sleep(SCROLL_PAUSE_TIME)

    # Calculate new scroll height and compare with last scroll height
    new_height = driver.execute_script("return document.body.scrollHeight")
    if new_height == last_height:
        break
    last_height = new_height

# 이미지 저장
time.sleep(2)
images = driver.find_elements_by_css_selector("img._image._listImage")

count = 290
for image in images:
    try:
        image.click()
        time.sleep(2)
        imgUrl = driver.find_element_by_xpath(
            "/html/body/div[3]/div[2]/div/div[1]/section/div/div[2]/div/div[1]/div[1]/div[1]/div/div/div[1]/div[1]/img").get_attribute("src")
        urllib.request.urlretrieve(imgUrl, "zen" + str(count) + ".png")
        count = count + 1
    except:
        pass

driver.close()
```
