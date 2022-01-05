## 아이템 삭제 기능 (2)

adapter에 interface를 생성하여, 클릭된 아이템의 position을 전달한다.

### 코드
- FavoritesAdapter.java
```java
    public interface OnListItemLongSelectedInterface {
        void onItemLongSelected(View v, int position);
    }

    private OnListItemLongSelectedInterface mLongListener;
    
    public FavoritesAdapter(ArrayList<ItemData> arrayList, Context context,
                            OnListItemLongSelectedInterface longListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.mLongListener = longListener;
    }
    
    // '선택' 버튼 클릭 시
        @Override
    public void onBindViewHolder(FavoritesAdapter.ViewHolder holder, int position) {
    
        ...
        
        holder.btn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemIdx = holder.getAbsoluteAdapterPosition();
                mLongListener.onItemLongSelected(v, itemIdx);
                notifyDataSetChanged();
            }
        });
    }
```

- FavoritesActivity.java
```java
public class FavoritesActivity extends AppCompatActivity implements FavoritesAdapter.OnListItemLongSelectedInterface {

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
    public void onItemLongSelected(View v, int position) {
        selected = position;
    }
}
```

✔ 액티비티 리프레시 하는 법 → Stack Overflow 참고 [here](https://stackoverflow.com/questions/3053761/reload-activity-in-android)
