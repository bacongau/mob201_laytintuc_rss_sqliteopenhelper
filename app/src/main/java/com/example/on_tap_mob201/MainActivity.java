package com.example.on_tap_mob201;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.on_tap_mob201.Adapter.TinTucAdapter;
import com.example.on_tap_mob201.Model.TinTuc;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button,button_dsbaibaodaluu;
    String link = "https://ngoisao.net/rss/showbiz-viet.rss";
    // String link = "https://thanhnien.vn/rss/thoi-su/phap-luat.rss";
    List<TinTuc> tinTucList = new ArrayList<>();
    ListView listView;
    EditText edt_url;
    ArrayList<String> arrayListLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button_loadtrang);
        button_dsbaibaodaluu = findViewById(R.id.button_danhsachluu);
        listView = findViewById(R.id.listview_tintuc);
        edt_url = findViewById(R.id.editText_rss);
        edt_url.setText(link);
        arrayListLink = new ArrayList<>();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AsyncTask asyncTask = new AsyncTask() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        loadData();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        super.onPostExecute(o);
                        // duoc goi khi luong ket thuc
                        // khai bao adapter
                        // bo  array vao adapter
                        // set adapter cho listview

                        TinTucAdapter adapter = new TinTucAdapter(MainActivity.this,R.layout.item_tintuc,tinTucList);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                //  Toast.makeText(MainActivity.this, ""+arrayListLink.get(position), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, TinTucActivity.class);
                                intent.putExtra("linktintuc",arrayListLink.get(position));
                                startActivity(intent);
                            }
                        });

                    }
                };
                asyncTask.execute();

            }
        });


        button_dsbaibaodaluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DanhSachDaLuu.class));
            }
        });
    }

    ////
    private void loadData() {

        try {
            // kiem tra link dung hay sai
            URL url = new URL(edt_url.getText().toString());

            // mo ket noi
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(false);

            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(inputStream, "utf-8");

            int event = xmlPullParser.getEventType();

            TinTuc tinTuc = null;

            String text = null;
            // neu su kien khac the dong cuoi cung thi se doc tiep
            while (event != xmlPullParser.END_DOCUMENT) {

                String tag = xmlPullParser.getName();

                switch (event) {
                    case XmlPullParser.START_TAG: {
                        if (tag.equalsIgnoreCase("item")) {
                            tinTuc = new TinTuc();
                        }

                        break;
                    }

                    case XmlPullParser.TEXT: {
                        text = xmlPullParser.getText();
                        break;
                    }

                    case XmlPullParser.END_TAG: {
                        if (tinTuc != null) {
                            if (tag.equalsIgnoreCase("title")) {
                                tinTuc.title = text;
                            }
                            if (tag.equalsIgnoreCase("description")) {
                                tinTuc.description = text;
                            }
                            if (tag.equalsIgnoreCase("id")) {
                                tinTuc.id = text;
                            }
                            if (tag.equalsIgnoreCase("pubDate")) {
                                tinTuc.pubDate = text;
                            }
                            if (tag.equalsIgnoreCase("link")) {
                                arrayListLink.add(text);
                            }
                            if (tag.equalsIgnoreCase("item")) {
                                tinTucList.add(tinTuc);
                            }
                        }

                        break;
                    }

                }

                event = xmlPullParser.next();
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

    }
    /////
}