package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<ItemData> arrayList;
    private Context context;

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView iv_photo;
        private final TextView tv_name;
        private final TextView tv_price;

        public ViewHolder(View view) {
            super(view);

            // 뷰 객체에 대한 참조
            iv_photo = view.findViewById(R.id.iv_photo);
            tv_name = view.findViewById(R.id.tv_name);
            tv_price = view.findViewById(R.id.tv_price);
        }
    }

    // 생성자에서 데이터 리스트 객체를 전달받음
    public ItemAdapter(ArrayList<ItemData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        /*
        아래와 같다.
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_funiture, parent, false);
        ViewHolder holder = new ViewHolder(view);
        */

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_furniture, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ItemData data = arrayList.get(position);

        Glide.with(viewHolder.iv_photo)
                .load(data.getImage())
                .into(viewHolder.iv_photo);
        viewHolder.tv_name.setText(data.getName());
        viewHolder.tv_price.setText(data.getPrice());
    }

    // getItemCount() - 전체 데이터 개수 리턴
    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }
}
