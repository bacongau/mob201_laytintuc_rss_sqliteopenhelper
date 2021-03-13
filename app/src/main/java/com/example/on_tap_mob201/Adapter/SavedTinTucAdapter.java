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
import com.example.on_tap_mob201.Model.TinTuc;
import com.example.on_tap_mob201.R;

import java.util.List;

public class SavedTinTucAdapter extends BaseAdapter {
    Context context;
    int layout;
    List<TinTuc> tinTucList;

    public SavedTinTucAdapter(Context context, int layout, List<TinTuc> tinTucList) {
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
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            viewHolder.textView = convertView.findViewById(R.id.textView_title_saved_tintuc);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(tinTucList.get(position).title);

        return convertView;
    }
}
