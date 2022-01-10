package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<String> mData = null;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tv_history;
        Button btn_delete;

        ViewHolder(View itemView) {
            super(itemView);

            tv_history = itemView.findViewById(R.id.tv_item_history);
            btn_delete = itemView.findViewById(R.id.btn_item_history);

            // 클릭 이벤트
            itemView.setOnClickListener(this);

            // 삭제 버튼 이벤트
            btn_delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.equals(btn_delete)) {
                String style = tv_history.getText().toString();
                removeAt(getAbsoluteAdapterPosition(), style);
            } else if (v.equals(itemView)) {
                String style = tv_history.getText().toString();
                goMain(v, getAbsoluteAdapterPosition(), style);
            }
        }
    }

    public void goMain (View v, int position, String style) {
        if (position != RecyclerView.NO_POSITION) {
            // style 값 받아오기
            String furniture = ((CustomActivity)CustomActivity.mContext).furniture;

            Intent intentToMain = new Intent(v.getContext(), MainActivity.class);
            intentToMain.putExtra("style", style);
            intentToMain.putExtra("type", furniture);
            v.getContext().startActivity(intentToMain);
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

    HistoryAdapter(ArrayList<String> list) {
        this.mData = list;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_history, parent, false);
        HistoryAdapter.ViewHolder vh = new HistoryAdapter.ViewHolder(view);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        String text = mData.get(position);
        holder.tv_history.setText(text);
    }

    @Override
    public int getItemCount() {
        return (mData != null ? mData.size() : 0);
    }
}
