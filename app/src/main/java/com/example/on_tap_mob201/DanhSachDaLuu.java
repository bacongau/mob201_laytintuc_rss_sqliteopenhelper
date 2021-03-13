package com.example.on_tap_mob201;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.on_tap_mob201.Adapter.SavedTinTucAdapter;
import com.example.on_tap_mob201.DAO.TintucDAO;
import com.example.on_tap_mob201.Model.TinTuc;

import java.util.ArrayList;
import java.util.List;

public class DanhSachDaLuu extends AppCompatActivity {
    ListView listView;
    TintucDAO tintucDAO;
    List<TinTuc> tinTucList;
    SavedTinTucAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_da_luu);

        listView = findViewById(R.id.listview_danhsach_luu);

        tintucDAO = new TintucDAO(this);
        tinTucList = new ArrayList<>();
        tinTucList = tintucDAO.getAllData();

        adapter = new SavedTinTucAdapter(this, R.layout.item_saved_tintuc, tinTucList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog dialog = new Dialog(DanhSachDaLuu.this);

                dialog.setContentView(R.layout.dialog_thongtin_baibao);
                TextView textView_title = dialog.findViewById(R.id.textView_dialog_title);
                TextView textView_pubdate = dialog.findViewById(R.id.textView_dialog_pubdate);
                Button button = dialog.findViewById(R.id.button_dialog_dong);

                textView_title.setText(tinTucList.get(position).title);
                textView_pubdate.setText(tinTucList.get(position).pubDate);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String a = tinTucList.get(position).id;
                Log.e("AaAA", a + "");
                Log.e("AaAA", "id: "+a + "\n" + "title" + tinTucList.get(position).title + "\n" + "desc" + tinTucList.get(position).description + "\n" + "pubdate: " + tinTucList.get(position).pubDate ) ;

                if (tintucDAO.delete(tinTucList.get(position).description) > 0){
                    Toast.makeText(DanhSachDaLuu.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                    tinTucList = tintucDAO.getAllData();
                    adapter = new SavedTinTucAdapter(DanhSachDaLuu.this, R.layout.item_saved_tintuc, tinTucList);
                    listView.setAdapter(adapter);
                }else {
                    Toast.makeText(DanhSachDaLuu.this, "Loi", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });


    }
}