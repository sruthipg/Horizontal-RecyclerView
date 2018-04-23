package com.mindlabs.sample;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by User on 23-04-2018.
 */

public class MonthNamesAdapter extends RecyclerView.Adapter<MonthNamesAdapter.MonthNameViewHolder> {

    ArrayList<MonthNames> monthNamesModelList;
    Context context;
    PositionCallBack positionCallBack;
    Calendar calendar;
    int currentPos=0;
    int prevPos=0;

    String[] monthStrins;

    public MonthNamesAdapter(ArrayList<MonthNames> monthNamesModelList, Context context,
                             PositionCallBack positionCallBack,String[] monthStrins) {
        this.monthNamesModelList = monthNamesModelList;
        this.context = context;
        this.positionCallBack=positionCallBack;
        this.monthStrins=monthStrins;
    }

    @Override
    public MonthNameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.month_name_list, parent, false);

        return new MonthNameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MonthNameViewHolder holder, final int position) {

        holder.monthNameTv.setText(monthNamesModelList.get(position).getMonthNames());
        currentPos= Integer.parseInt(monthNamesModelList.get(position).getCurrentPos());
        if (currentPos==1){
            holder.monthNamesLinearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorRed));
        }else {
            holder.monthNamesLinearLayout.setBackgroundColor(context.getResources().getColor(R.color.colorWhite));
        }
        holder.monthNamesLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<monthNamesModelList.size();i++){
                    MonthNames monthNames=new MonthNames();
                    if (i==position){
                        monthNames.setCurrentPos("1");
                    }else {
                        monthNames.setCurrentPos("0");
                    }
                    monthNames.setMonthNames(monthStrins[i]);
                }
                notifyDataSetChanged();
                notifyItemChanged(position);
                positionCallBack.posChanged(position);
            }

        });


    }

    @Override
    public int getItemCount() {
        return monthNamesModelList.size();
    }

    public class MonthNameViewHolder extends RecyclerView.ViewHolder {
        TextView monthNameTv, numberOfDaysTv;
        RelativeLayout monthNamesLinearLayout;

        public MonthNameViewHolder(View itemView) {
            super(itemView);

            monthNameTv = itemView.findViewById(R.id.monthNameTv);

            monthNamesLinearLayout = itemView.findViewById(R.id.monthNamesRelativeLayout);

        }
    }

}
