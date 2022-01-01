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

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private ArrayList<ItemData> arrayList;
    private Context context;

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public static class ViewHolder extends  RecyclerView.ViewHolder {
        public final ImageView iv_photo;
        public final TextView tv_name;
        public final TextView tv_price;

        public ViewHolder(View view) {
            super(view);

            // 뷰 객체에 대한 참조
            iv_photo = view.findViewById(R.id.iv_item_favorites);
            tv_name = view.findViewById(R.id.tv_item_favorites_name);
            tv_price = view.findViewById(R.id.tv_item_favorites_price);
        }
    }

    public FavoritesAdapter(ArrayList<ItemData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorites, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(FavoritesAdapter.ViewHolder holder, int position) {
        ItemData data = arrayList.get(position);

        Glide.with(holder.iv_photo)
                .load(data.getImage())
                .into(holder.iv_photo);
        holder.tv_name.setText(data.getName());
        holder.tv_price.setText(data.getPrice());

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }
}
