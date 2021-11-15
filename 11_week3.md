# 가구, 스타일 버튼의 상호 이벤트
: 특정 스타일 버튼을 클릭했을 때, 관련 가구 데이터가 출력되도록 구현

## 데이터베이스 수정
동작하는 순서와 유사하도록 스타일을 기준으로 하며, 그 하위에 가구 종류에 따라 데이터를 구분함

![DB수정](https://user-images.githubusercontent.com/47620950/141795726-d6153764-b679-4983-90cf-c92c3071e927.PNG)

## 데이터 출력 오류 해결
적절히 constraint 되지 않아 RecyclerView 하단의 아이템이 보이지 않은 오류를 해결

✔ recyclerView의 마지막 아이템이 잘리는 오류 발생 →  stack overflow의 글 참고 [here](https://stackoverflow.com/questions/51342917/last-item-in-recyclerview-is-cut-off)
