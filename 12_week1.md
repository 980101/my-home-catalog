# DB 보안규칙 변경
: 원활한 프로젝트 진행을 위해, 허용 모드로 설정한다

✔ stack overflow의 글 참고 [here](https://stackoverflow.com/questions/37403747/firebase-permission-denied)

![보안규칙변경](https://user-images.githubusercontent.com/47620950/144175979-e0aefa1e-603c-4f5a-bb33-76d9cf7fb108.PNG)

# 즐겨찾기 액티비티 구현
: 사용자가 물품을 추후 확인할 수 있는 즐겨찾기 기능을 구현한다

## Intent 적용
<img src="https://user-images.githubusercontent.com/47620950/144253975-4f2bb768-5e5b-4f90-bc95-1032ddff8888.png" width="400" />

## 데이터 저장할 방법
1. Shared Preferences : 키-값 쌍으로 데이터를 저장한다. 비교적 적은 데이터를 다룰 때 사용한다.
2. JSON file : JSON 파일을 앱에 저장하여 데이터를 사용한다. 1번이 방법보다 복잡한 데이터를 다룰 수 있다.

✔ 하나의 데이터에 이미지 URL, 이름, 가격을 저장하므로 2번 방법을 선택
