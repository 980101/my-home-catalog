## 아이템 삭제
히스토리 리스트의 아이템을 'X' 버튼을 누르면 삭제한다.

✔ holder 클래스의 코드 길이가 길어져 가독성이 떨어지는 문제점이 발생 → stack overflow 참고 [here](https://stackoverflow.com/questions/26076965/android-recyclerview-addition-removal-of-items)

```java
public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tv_history;
    Button btn_delete;

    ViewHolder(View itemView) {
        super(itemView);

        tv_history = itemView.findViewById(R.id.tv_item_history);
        btn_delete = itemView.findViewById(R.id.btn_item_history);

        ...

        // 삭제 버튼 이벤트
        btn_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(btn_delete)) {
            String style = tv_history.getText().toString();
            removeAt(getAbsoluteAdapterPosition(), style);
        } else if (v.equals(itemView)) {
            ...
        }
    }
 }

public void removeAt(int position, String style) {
    // 데이터 삭제 : View 부분
    mData.remove(position);
    notifyItemRemoved(position);
    notifyItemRangeChanged(position, mData.size());
    // 데이터 삭제 : Data 부분
    SharedPreferences mPreferences = ((CameraActivity)CameraActivity.mContext).mPreferences;
    SharedPreferences.Editor editor = mPreferences.edit();
    editor.remove(style);
    editor.commit();
}
```
