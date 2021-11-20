# 가구, 스타일 버튼의 상호 이벤트
: 특정 스타일 버튼을 클릭했을 때, 관련 가구 데이터가 출력되도록 구현

## 데이터베이스 수정
동작하는 순서와 유사하도록 스타일을 기준으로 하며, 그 하위에 가구 종류에 따라 데이터를 구분함

![DB수정](https://user-images.githubusercontent.com/47620950/141795726-d6153764-b679-4983-90cf-c92c3071e927.PNG)

## 데이터 출력 오류 해결
constraint가 적절히 되지 않아 RecyclerView 하단의 아이템이 보이지 않은 오류 발생 →  stack overflow의 글 참고 [here](https://stackoverflow.com/questions/51342917/last-item-in-recyclerview-is-cut-off) 😀

<img src="https://user-images.githubusercontent.com/47620950/141802543-c7395e51-ddbc-4a9e-a1cf-6782183ccb9d.jpg" width=300> < 변경 전 > <img src="https://user-images.githubusercontent.com/47620950/141802787-d6c72525-b562-43ff-97e3-68b86f96113d.jpg" width=300> < 변경 후 >

# 이미지 분류 결과에 따른 출력
: 이미지 분류 후, 버튼 클릭하면 메인화면으로 이동. 이때 분류 결과에 따라 해당하는 데이터가 출력

## 진행 과정
1️⃣ 이미지 분류 진행

2️⃣ 정확도 90% 이상의 클래스명을 추출

3️⃣ 메인 화면으로 이동 ( + 분류된 클래스명 )

4️⃣ 데이터 출력

## 메인 화면으로 이동
```java
Button btn_capture = findViewById(R.id.btn_capture);
    btn_capture.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!TextUtils.isEmpty(recognitionStyle.getText())) {
          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
          // style : 분류된 클래스명
          intent.putExtra("style", recognitionStyle.getText().toString());
          startActivity(intent);
        }
      }
    });
```

## 버튼 클릭 이벤트 리스너 수정
익명 클래스를 만들고, 이를 참조하는 형식으로 수정하여 가독성을 증가

```java
        // 이벤트 리스너 : 스타일 버튼
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrays.fill(styles, null);

                switch (v.getId()) {
                    case R.id.btn_style_natural:
                        styles[0] = "natural";
                        break;
                    case R.id.btn_style_modern:
                        styles[0] = "modern";
                        break;
                    case R.id.btn_style_classic:
                        styles[0] = "classic";
                        break;
                    case R.id.btn_style_industrial:
                        styles[0] = "industrial";
                        break;
                    case R.id.btn_style_zen:
                        styles[0] = "zen";
                        break;
                }
                ...
            }
        };

        // 이벤트 처리
        Button btn_style_natural = findViewById(R.id.btn_style_natural);
        btn_style_natural.setOnClickListener(onClickListener);
```
