package org.tensorflow.lite.examples.classification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private ArrayList<String> mData = null;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_history;

        ViewHolder(View itemView) {
            super(itemView);

            tv_history = itemView.findViewById(R.id.tv_item_history);
        }
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
