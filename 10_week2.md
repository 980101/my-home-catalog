# 이미지 분류 모델 생성
Teachable Machine을 활용하여, 총 5개의 클래스를 가지는 학습 모델 생성

## 진행 과정
<img src="https://user-images.githubusercontent.com/47620950/137279687-a8461bf0-9060-44fd-841e-be5f5c42db0b.PNG" width=900>

## 모델 테스트
: 각 스타일을 대표하는 인테리어 뿐만 아니라 일반 가정의 인테리어도 잘 분류하는 것을 볼 수 있다.

<img src="https://user-images.githubusercontent.com/47620950/137280250-bc648520-2d4d-489e-bd57-28595862c57d.PNG" width=200> <img src="https://user-images.githubusercontent.com/47620950/137280579-2e2428fb-7709-48da-ad5b-3e3f473662ed.PNG" width=200> <img src="https://user-images.githubusercontent.com/47620950/137280695-c520e3cf-3e6c-47bf-a821-e9a6218ebb8b.PNG" width=200>


# 학습 모델 적용
TensorFlow Lite의 image classification 예제에 모델 적용하여 진행한다.

## 모듈 이름 재설정
: stack overflow의 글 참고 [here](https://stackoverflow.com/questions/38886417/rename-root-module-in-android-studio) 😀

### settings.gradle 에서 ..
```
// 변경 전 : rootProject.name = 'TFLite Flower Classification Codelab (start) App'
rootProject.name = 'House Server App'
```
## 결과 화면
<img src="https://user-images.githubusercontent.com/47620950/137278682-8073ea80-ea35-4fab-8fa6-166d1e91d404.PNG" width=416 height=666> <img src="https://user-images.githubusercontent.com/47620950/137279086-a4a6987d-bf30-4ad4-96a8-35ef0b856d8c.PNG" width=416 height=666>
