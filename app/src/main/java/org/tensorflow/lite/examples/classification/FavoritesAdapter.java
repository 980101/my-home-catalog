package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

    private ArrayList<ItemData> arrayList;
    private Context context;

    public interface OnListItemSelectedInterface {
        void onItemSelected(View v, int position);
    }

    private OnListItemSelectedInterface mListener;

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView iv_photo;
        protected TextView tv_name;
        protected TextView tv_price;
        protected Button btn_item;

        public ViewHolder(View view) {
            super(view);

            // 뷰 객체에 대한 참조
            iv_photo = view.findViewById(R.id.iv_item_favorites);
            tv_name = view.findViewById(R.id.tv_item_favorites_name);
            tv_price = view.findViewById(R.id.tv_item_favorites_price);
            btn_item = view.findViewById(R.id.btn_item_favorites);
        }
    }

    public FavoritesAdapter(ArrayList<ItemData> arrayList, Context context, OnListItemSelectedInterface onListItemSelectedInterface) {
        this.arrayList = arrayList;
        this.context = context;
        this.mListener = onListItemSelectedInterface;
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
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToDetail = new Intent(context.getApplicationContext(), DetailActivity.class);
                intentToDetail.putExtra("image", data.getImage());
                intentToDetail.putExtra("name", data.getName());
                intentToDetail.putExtra("price", data.getPrice());
                intentToDetail.putExtra("link", data.getLink());
                context.startActivity(intentToDetail);
            }
        });

        holder.btn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int itemIdx = holder.getAbsoluteAdapterPosition();
                mListener.onItemSelected(v, itemIdx);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (arrayList != null ? arrayList.size() : 0);
    }
}
