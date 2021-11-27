package org.tensorflow.lite.examples.classification;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    // 선택된 아이템의 인덱스 추출
    public interface OnListItemSelectedInterface {
        void onItemSelected(View v, int position);
    }

    private OnListItemSelectedInterface mListener;

    // 리사이클러뷰의 아이템을 담는 배열
    private ArrayList<CustomData> arrayList;

    public CustomAdapter(ArrayList<CustomData> arrayList, OnListItemSelectedInterface listener) {
        this.arrayList = arrayList;
        this.mListener = listener;
    }

    // 아이템이 처음 생성될 때의 생명 주기
    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_button, parent, false);

        // 아이템의 크기를 동적으로 변경
        int width = parent.getMeasuredWidth() / 2;
        int height = parent.getMeasuredHeight() / 3;
        view.setMinimumWidth(width);
        view.setMinimumHeight(height);

        CustomViewHolder holder = new CustomViewHolder(view);

        return holder;
    }

    // 아이템이 추가될 때의 생명 주기
    int itemIdx = -1;

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        holder.iv_icon.setImageResource(arrayList.get(position).getIv_icon());
        holder.tv_name.setText(arrayList.get(position).getTv_name());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemIdx = holder.getAdapterPosition();
                mListener.onItemSelected(v,itemIdx);
                notifyDataSetChanged();
            }
        });

        if (itemIdx == holder.getPosition()) {
            holder.itemView.setBackgroundResource(R.drawable.btn_custom_clicked);;
        } else {
            holder.itemView.setBackgroundResource(R.drawable.btn_custom_unclicked);
        }
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    // 생성한 클래스
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView iv_icon;
        protected TextView tv_name;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            this.iv_icon = itemView.findViewById(R.id.iv_custom_item);
            this.tv_name = itemView.findViewById(R.id.tv_custom_item);
        }
    }
}
