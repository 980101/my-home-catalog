# 이미지 분류 결과에 따른 출력
: 이미지 분류 결과, 인테리어 스타일이 추출되면 해당하는 가구를 출력한다
## 선택된 사항 표시
현재 인테리어 스타일은 버튼색상을 변경하여, 표시

# 초기 화면 구현
: 앱 실행 시, 처음 실행되는 화면을 구현한다

## Screem Shot 📷
<img src="https://user-images.githubusercontent.com/47620950/144025410-b5e307ce-f5a4-404e-bd30-999e18152634.PNG" height="400"/>  (UI 디자인 적용 전)

## 프로젝트에 적용
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
: 인테리어 스타일 파악 후, 보게되는 가구 종류를 선택하는 액티비티

## 활성화 된 버튼의 색상 변경
```java
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ...
            }
        });
        
        if (itemIdx == holder.getPosition()) {
            holder.itemView.setBackgroundResource(R.drawable.btn_custom_clicked);;
        } else {
            holder.itemView.setBackgroundResource(R.drawable.btn_custom_unclicked);
        }
    }
}
```

## 선택된 가구의 값 추출
```java
/* CustomAdapter.java
   Interface 추가 */
   
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    public interface OnListItemSelectedInterface {
        void onItemSelected(View v, int position);
    }

    private OnListItemSelectedInterface mListener;

    public CustomAdapter(ArrayList<CustomData> arrayList, OnListItemSelectedInterface listener) {
        this.arrayList = arrayList;
        this.mListener = listener;
    }
}    
```

```java
// CustomActivity.java
   
public class CustomActivity extends AppCompatActivity implements CustomAdapter.OnListItemSelectedInterface{
    ...
    @Override
    public void onItemSelected(View v, int position) {
        CustomAdapter.CustomViewHolder viewHolder = (CustomAdapter.CustomViewHolder)recyclerView.findViewHolderForAdapterPosition(position);
        furniture = viewHolder.tv_name.getText().toString();
    }
}
```
