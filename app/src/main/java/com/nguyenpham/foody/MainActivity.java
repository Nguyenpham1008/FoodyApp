package com.nguyenpham.foody;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvMoiNhat;
    ListView lvDanhMuc;
    ListView lvTinhThanh;
    String[] dsMoiNhat,dsDanhMuc,dsTinhThanh;
    ArrayAdapter<String> adapterMoiNhat,adapterDanhMuc,adapterTinhThanh;

    ImageButton btnLogo,btnAdd,btnMenuOdau;
    Button btnHuy;


    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {

        btnMenuOdau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                xuLyHienThiActivityAnGi();
            }
        });
        btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                lvDanhMuc.setVisibility(View.VISIBLE);
                lvMoiNhat.setVisibility(View.VISIBLE);
                lvTinhThanh.setVisibility(View.VISIBLE);
                btnHuy.setVisibility(View.VISIBLE);
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(android.R.color.transparent));
                           // unselected
                }
                tabHost.getTabWidget().getChildAt(tabHost.getCurrentTab())
                        .setBackgroundColor(Color.rgb(238,238,238)); // selected


            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvDanhMuc.setVisibility(View.INVISIBLE);
                lvMoiNhat.setVisibility(View.INVISIBLE);
                lvTinhThanh.setVisibility(View.INVISIBLE);
                btnHuy.setVisibility(View.INVISIBLE);
                for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
                    tabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    // unselected
                }
            }
        });
        lvTinhThanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tinhThanh = (String) parent.getItemAtPosition(position);

                TextView title = (TextView) tabHost.getTabWidget().getChildAt(2).findViewById(android.R.id.title);
                title.setText(tinhThanh);
            }
        });

        lvDanhMuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String danhMuc = (String) parent.getItemAtPosition(position);

                TextView title = (TextView) tabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
                title.setText(danhMuc);
            }
        });

        lvMoiNhat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String moiNhat = (String) parent.getItemAtPosition(position);

                TextView title = (TextView) tabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
                title.setText(moiNhat);
            }
        });

        btnLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DanhMucActivity.class);
                startActivity(intent);
            }
        });


    }

    private void xuLyHienThiActivityAnGi() {
        Intent intent = new Intent(MainActivity.this,AnGiActivity.class);
        startActivity(intent);
        this.finish();
    }


    private void addControls() {

        btnAdd = (ImageButton) findViewById(R.id.btnAdd);
        btnMenuOdau = (ImageButton) findViewById(R.id.btnMenuOdau);
        btnLogo = (ImageButton) findViewById(R.id.btnlogo);
        btnHuy = (Button) findViewById(R.id.btnHuyODau);

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tab1 = tabHost.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("Mới Nhất");


        tabHost.addTab(tab1);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("Danh Mục");
        tabHost.addTab(tab2);


        TabHost.TabSpec tab3 = tabHost.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("Tỉnh Thành");
        tabHost.addTab(tab3);

        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
            TextView tv =  (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setAllCaps(false);
            tv.setTextSize(14);
        }



        lvDanhMuc = (ListView) findViewById(R.id.lvDanhMuc);
        lvMoiNhat = (ListView) findViewById(R.id.lvMoiNhat);
        lvTinhThanh = (ListView) findViewById(R.id.lvTinhThanh);


        dsDanhMuc = getResources().getStringArray(R.array.danhMucODau);
        dsTinhThanh =getResources().getStringArray(R.array.tinhThanh);
        dsMoiNhat = getResources().getStringArray(R.array.moiNhatODAu);


        adapterDanhMuc = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,dsDanhMuc);
        adapterMoiNhat = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,dsMoiNhat);
        adapterTinhThanh = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,dsTinhThanh);

        lvMoiNhat.setAdapter(adapterMoiNhat);
        lvDanhMuc.setAdapter(adapterDanhMuc);
        lvTinhThanh.setAdapter(adapterTinhThanh);

        lvDanhMuc.setVisibility(View.INVISIBLE);
        lvMoiNhat.setVisibility(View.INVISIBLE);
        lvTinhThanh.setVisibility(View.INVISIBLE);
        btnHuy.setVisibility(View.INVISIBLE);


    }

}
