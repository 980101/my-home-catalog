## 아이템 삭제 기능 (2)

adapter에 interface를 생성하여, 클릭된 아이템의 position을 전달한다.

### 코드
- FavoritesAdapter.java
```java
    public interface OnListItemSelectedInterface {
        void onItemSelected(View v, int position);
    }

    private OnListItemSelectedInterface mListener;
    
    public FavoritesAdapter(ArrayList<ItemData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }
    
    // '선택' 버튼 클릭 시
     @Override
    public void onBindViewHolder(FavoritesAdapter.ViewHolder holder, int position) {
    
        ...
        
        holder.btn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemIdx = holder.getAbsoluteAdapterPosition();
                mListener.onItemSelected(v, itemIdx);
                notifyDataSetChanged();
            }
        });
    }
```

- FavoritesActivity.java
```java
public class FavoritesActivity extends AppCompatActivity implements FavoritesAdapter.OnListItemSelectedInterface {

    private int selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ...

    public void deleteItem(View view) {
        MyJson myJson = new MyJson();
        myJson.deleteData(this, selected);

        finish();
        startActivity(getIntent());
    }

    @Override
    public void onItemSelected(View v, int position) {
        selected = position;
    }
}
```

✔ 액티비티 리프레시 하는 법 → stack overflow 참고 [here](https://stackoverflow.com/questions/3053761/reload-activity-in-android)

✔ Toast 설정 시, context 설정 (getApplicationContext() VS this) 에 대한 궁금증이 생김 → stack overflow 참고 [here](https://stackoverflow.com/questions/22966601/what-is-different-between-mainactivity-this-vs-getapplicationcontext)

# 카메라 화면의 상단에 히스토리 출력
: 사용자가 이전에 사용한 인테리어 스타일을 표시한다.

💡 앱이 삭제되기 전까지 데이터가 유지돼야 하기 때문에 SharedPreferences Api를 사용한다.

1️⃣ '촬영하기' 버튼을 누르면, 감지한 스타일명을 키, 값으로 저장

※ 이때 동일한 데이터가 있을 경우 삭제 후, 추가

2️⃣ 다시 카메라 화면으로 이동 시, 변경된 리스트가 상단에 출력

3️⃣ 아이템을 클릭하면 메인 화면으로 이동

## 아이템 클릭 이벤트
CustomActivity의 변수인 type 값을 가져와서, 아이템명과 함께 Intent 한다.
