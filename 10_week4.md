# 상품 데이터 출력
인테리어 스타일 별 가구 데이터를 출력하자

✔ 현재 메인 화면인 activity_camera 에 버튼을 사용하여 activity_main을으로 이동한 후, 샘플 프로그래밍을 진행

##  진행 과정
1️⃣ 인테리어 스타일 파악

2️⃣ 구축한 DB에서 이미지가 해당 스타일과 지정한 유사도를 가진다면, 리스트에 저장

3️⃣ 리스트 출력

## 데이터 세트 수집
: 5가지 품목 ( 소파, 의자, 침대, 서랍장, 식탁 ) 에 해당하는 가구를 '오늘의 집'에서 이미지, 이름, 가격, 구매 링크의 데이터를 수집한다. → 크롤링 진행 ❕

### 사용한 코드
```python
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import urllib.request

driver = webdriver.Chrome()

# 오늘의 집(특정 가구 목록)으로 이동
driver.get("https://ohou.se/store/category?category=0_6_7")

# 이미지 저장
images = driver.find_elements_by_css_selector(".virtualized-list .image")

count = 1
for image in images:
    try:
        urllib.request.urlretrieve(image.get_attribute(
            "src"), "table" + str(count) + ".png")
        count = count + 1
    except:
        pass

driver.close()

```
## 결과
: 테스트를 위해 각 6개의 이미지 데이터와 나머지 데이터를 수집하였다.
