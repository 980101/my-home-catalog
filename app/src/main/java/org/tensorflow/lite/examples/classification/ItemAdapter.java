package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<String> mData = null;

    // 아이템 뷰를 저장하는 뷰홀더 클래스
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView1;
        private final TextView textView2;
        public ViewHolder(View view) {
            super(view);

            // 뷰 객체에 대한 참조
            textView1 = (TextView) view.findViewById(R.id.tv_name);
            textView2 = (TextView) view.findViewById(R.id.tv_price);
        }

//        public TextView getTextView() {
//            return textView;
//        }
    }

    //생성자에서 데이터 리스트 객체를 전달받음
    public ItemAdapter(ArrayList<String> list) {
        mData = list;
    }

    // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_funiture, parent, false);

        return new ItemAdapter.ViewHolder(view);
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//        String text = mData.get(position);
//
//        viewHolder.textView1.setText(text);
    }

    // getItemCount() - 전체 데이터 개수 리턴
    @Override
    public int getItemCount() {
        return mData.size();
    }
}
