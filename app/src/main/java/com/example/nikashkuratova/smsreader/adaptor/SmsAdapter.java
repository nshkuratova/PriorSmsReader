package com.example.nikashkuratova.smsreader.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikashkuratova.smsreader.pojo.SmsMessage;
import com.example.nikashkuratova.smsreader.R;

import java.util.List;

public class SmsAdapter extends RecyclerView.Adapter<SmsAdapter.ViewHolder> {

    public SmsAdapter(List<SmsMessage> list) {
        this.list = list;
    }

    private List<SmsMessage> list;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SmsMessage message = list.get(position);
        holder.smsMessage.setText(message.getSmsText());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView smsMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            smsMessage = (TextView) itemView.findViewById(R.id.smsText);
        }

    }


}
