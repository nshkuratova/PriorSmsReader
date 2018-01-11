package com.example.nikashkuratova.smsreader.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikashkuratova.smsreader.listener.RecyclerViewClickListener;
import com.example.nikashkuratova.smsreader.pojo.SmsCategory;
import com.example.nikashkuratova.smsreader.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private static RecyclerViewClickListener itemListener;
    private List<SmsCategory> list;

    public CategoryAdapter(List<SmsCategory> list, RecyclerViewClickListener listener) {
        this.list = list;
        this.itemListener = listener;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new CategoryAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {
        SmsCategory category = list.get(position);
        holder.smsCategory.setText(category.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView smsCategory;

        public ViewHolder(View itemView) {
            super(itemView);
            smsCategory = (TextView) itemView.findViewById(R.id.smsText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());
        }
    }
}


