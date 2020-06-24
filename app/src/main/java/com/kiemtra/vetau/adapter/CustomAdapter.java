package com.kiemtra.vetau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.kiemtra.vetau.R;
import com.kiemtra.vetau.model.VeTau;

import java.util.List;

public class CustomAdapter extends ArrayAdapter {
    private Context context;
    private int resoure;
    private List<VeTau> veTauList;

    public CustomAdapter(@NonNull Context context, int resource, @NonNull List<VeTau> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resoure = resource;
        this.veTauList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.items_vetau,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvGaDi = (TextView)convertView.findViewById(R.id.item_gadi);
            viewHolder.tvGaDen = (TextView)convertView.findViewById(R.id.item_gaDen);
            viewHolder.tvDonGia = (TextView)convertView.findViewById(R.id.item_Gia);
            viewHolder.tvLoaiVe = (TextView)convertView.findViewById(R.id.item_loaiVe);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        VeTau veTau = veTauList.get(position);
        viewHolder.tvGaDi.setText(veTau.getmGaDi());
        viewHolder.tvGaDen.setText(veTau.getmGaDen());
        viewHolder.tvDonGia.setText(String.valueOf(veTau.getmDonGia()));
        if (veTau.ismLoaiVe()){
            viewHolder.tvLoaiVe.setText("Một chiều");
        }
        if (!veTau.ismLoaiVe()){
            viewHolder.tvLoaiVe.setText("Khứ hồi");
        }

        return convertView;
    }

    public class ViewHolder{
        private TextView tvGaDi;
        private TextView tvGaDen;
        private TextView tvDonGia;
        private TextView tvLoaiVe;

    }
}

