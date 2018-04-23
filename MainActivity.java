package com.mindlabs.sample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements PositionCallBack{

    MonthNamesAdapter monthNamesAdapter;
    RecyclerView recycler_view;
    ArrayList<MonthNames> arrayList;
    String[] monthNamess;
    Context context;
    int month;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        recycler_view= (RecyclerView)findViewById(R.id.recycler_view);
        setData();
        setAdapterData();
    }

    private void setData() {


        monthNamess= new String[]{"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

    }
    Calendar calendar;
    private void setAdapterData() {
        arrayList=new ArrayList<>();
        calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        for (int i=0;i< monthNamess.length;i++){
            MonthNames monthNames=new MonthNames();
            if (i==month){

                monthNames.setCurrentPos("1");
            }else {
                monthNames.setCurrentPos("0");

            }
            monthNames.setMonthNames(monthNamess[i]);
            arrayList.add(monthNames);
        }
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context,
                LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(mLayoutManager);

        monthNamesAdapter=new MonthNamesAdapter(arrayList,context,this,monthNamess);
        recycler_view.setAdapter(monthNamesAdapter);
        monthNamesAdapter.notifyDataSetChanged();
    }

    @Override
    public void posChanged(int currentPos) {
        arrayList=new ArrayList<>();
        for (int i=0;i<monthNamess.length;i++){
            MonthNames monthNames=new MonthNames();
            if (i==currentPos){

                monthNames.setCurrentPos("1");
            }else {
                monthNames.setCurrentPos("0");

            }
            monthNames.setMonthNames(monthNamess[i]);
            arrayList.add(monthNames);
        }
        monthNamesAdapter=new MonthNamesAdapter(arrayList,context,this,monthNamess);
        recycler_view.setAdapter(monthNamesAdapter);
        monthNamesAdapter.notifyDataSetChanged();
    }
}
