# 이미지 분류 결과에 따른 출력

## 선택된 사항 표시
이미지 분류 결과에 맞게 출력된 내용은 버튼을 다른 색으로 표시한다

# 초기 화면 구현
: 앱 실행 시, 처음 실행되는 화면을 구현

## 레이아웃 구현
(적용 사진)
### 적용
```xml
<!-- 시작 화면으로 설정 -->
<activity
    android:name=".InitialActivity"
    android:label="@string/tfe_ic_app_name"
    android:screenOrientation="portrait"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```
## Intent 적용
(사진)

# 맞춤가구 선택 화면

## 버튼 이벤트 적용
선택된 버튼의 색상을 변경
```java
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ...
            }
        });
        
        if (itemIdx == holder.getPosition()) {
            holder.itemView.setBackgroundColor(Color.parseColor("#F2A213"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F3F3F3"));
        }
    }
```
