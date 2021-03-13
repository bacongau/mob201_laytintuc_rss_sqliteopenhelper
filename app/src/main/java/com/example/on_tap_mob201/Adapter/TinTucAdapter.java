package com.example.on_tap_mob201.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.on_tap_mob201.DAO.TintucDAO;
import com.example.on_tap_mob201.Database.DbHelper;
import com.example.on_tap_mob201.Model.TinTuc;
import com.example.on_tap_mob201.R;

import java.util.List;

public class TinTucAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<TinTuc> tinTucList;

    public TinTucAdapter(Context context, int layout, List<TinTuc> tinTucList) {
        this.context = context;
        this.layout = layout;
        this.tinTucList = tinTucList;
    }

    @Override
    public int getCount() {
        return tinTucList.size();
    }

    @Override
    public Object getItem(int position) {
        return tinTucList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView textView;
        Button button_save;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder.textView = convertView.findViewById(R.id.textView_title);
            viewHolder.button_save = convertView.findViewById(R.id.button_save);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(tinTucList.get(position).title);
        viewHolder.button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TintucDAO tintucDAO = new TintucDAO(context);
//                TinTuc tinTuc = new TinTuc();
//                tinTuc.title = tinTucList.get(position).title;
//                tinTuc.id = tinTucList.get(position).id;
//                tinTuc.description = tinTucList.get(position).description;
//                tinTuc.pubDate = tinTucList.get(position).pubDate;
//                if (tintucDAO.insert(tinTuc) > 0){
//                    Toast.makeText(context, "Luu thanh cong", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(context, "Ban da luu roi", Toast.LENGTH_SHORT).show();
//                }



            }
        });

        return convertView;
    }
}
