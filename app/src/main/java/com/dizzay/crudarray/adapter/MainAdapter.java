package com.dizzay.crudarray.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dizzay.crudarray.R;
import com.dizzay.crudarray.model.BarangModel;

import java.util.ArrayList;

/**
 * Created by root on 5/16/16.
 */
public class MainAdapter extends BaseAdapter {

    Context context;
    ArrayList<BarangModel> list;
    private LayoutInflater mLayoutInflater = null;

    public MainAdapter(Context context, ArrayList<BarangModel> list){
        this.context = context;
        this.list = list;
        mLayoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.item_listview, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        BarangModel barangModel = list.get(position);
        String nama = barangModel.getBarangNama();
        String stok = barangModel.getBarangStok();
        viewHolder.namaBarang.setText(nama);
        viewHolder.stokBarang.setText(stok);
        return convertView;
    }

    private class ViewHolder {
        public TextView namaBarang;
        public TextView stokBarang;
        public ViewHolder(View base) {
            namaBarang = (TextView) base.findViewById(R.id.textView);
            stokBarang = (TextView) base.findViewById(R.id.textView2);

        }
    }
}
