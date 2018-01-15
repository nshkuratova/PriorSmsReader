package com.example.nikashkuratova.smsreader.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nikashkuratova.smsreader.R;
import com.example.nikashkuratova.smsreader.listener.RecyclerViewClickListener;
import com.example.nikashkuratova.smsreader.pojo.SmsCategory;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private static RecyclerViewClickListener itemListener;
    private List<SmsCategory> list;
    private Boolean isEditIconVisible = false;
    private Boolean isRemoveIconVisible = false;

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
        if (isEditIconVisible) {
            holder.editIcon.setVisibility(View.VISIBLE);
            holder.removeIcon.setVisibility(View.VISIBLE);
        } else {
            holder.editIcon.setVisibility(View.GONE);
            holder.removeIcon.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void enableEditAndRemoveOption(boolean newValue) {
        this.isEditIconVisible = newValue;
        this.isRemoveIconVisible = newValue;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView smsCategory;
        ImageButton editIcon;
        ImageButton removeIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            smsCategory = (TextView) itemView.findViewById(R.id.smsText);
            editIcon = (ImageButton) itemView.findViewById(R.id.edit_btn);
            removeIcon = (ImageButton) itemView.findViewById(R.id.delete_btn);
            smsCategory.setOnClickListener(this);
            removeIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    list.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    notifyItemRangeChanged(getAdapterPosition(), list.size());
                }
            });
        }

        @Override
        public void onClick(View view) {
            itemListener.recyclerViewListClicked(view, this.getLayoutPosition());
        }
    }
}


