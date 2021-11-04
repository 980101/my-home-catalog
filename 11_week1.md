# DB에 데이터 추가
: 링크 데이터를 수집하여, 추가함

## 추가 크롤링 진행
일부 데이터는 크롤링을 사용하여, 진행하였다.

### 사용한 코드
```python
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import urllib.request
import time

driver = webdriver.Chrome()

# 오늘의 집(특정 가구 목록)으로 이동
driver.get("https://ohou.se/store/category?category=0_2_5")

# 가구 선택
funitures = driver.find_elements_by_css_selector(
    ".virtualized-list .category-feed__content__item-wrap")

funitures[0].click()

# 사진 수집
time.sleep(2)
image = driver.find_element_by_css_selector(
    "img.production-selling-cover-image__entry__image").get_attribute("src")
try:
    urllib.request.urlretrieve(image, "dresser1.png")
except:
    pass

# 링크 데이터 수집
time.sleep(3)
print(driver.current_url)

# 이름 데이터 수집
time.sleep(2)
name = driver.find_element_by_css_selector(
    "span.production-selling-header__title__name").get_attribute("innerHTML")
print(name)

# 가격 데이터 수집
time.sleep(2)
price = driver.find_element_by_css_selector(
    "span.production-selling-header__price__price .number").get_attribute("innerHTML")
print(price + "원")

driver.close()
```

## 적용
: 이미지, 이름, 가격 데이터를 출력함. 링크 데이터는 추후 적용

<img width="350" src="https://user-images.githubusercontent.com/47620950/139801959-71733f69-31cf-4604-8612-203d4871ddf9.jpg">


# 가구 별 데이터 출력
: 가구 버튼 중 하나를 클릭했을 때, 해당 가구의 데이터만 출력되도록 한다

## 데이터베이스 변경
데이터 참조를 고려하여 가구를 기준으로 데이터가 분류되도록 구조를 변경하였다

<img src="https://user-images.githubusercontent.com/47620950/140290093-65cf4d58-c4ed-4e23-b757-b49ab7a2c05d.PNG" width="600">
