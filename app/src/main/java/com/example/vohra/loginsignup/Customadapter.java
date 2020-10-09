package com.example.vohra.loginsignup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vohra on 29-03-2017.
 */

public class Customadapter extends BaseAdapter {
    private Context con;
    private final String[] vishal;
    public Customadapter(Context con, String[] vishal){
        this.con=con;
        this.vishal=vishal;
    }
    @Override
    public int getCount() {
        return vishal.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridview;
        if(convertView==null){
            gridview=new View(con);
            gridview=inflater.inflate(R.layout.custom_adapter,null);
            TextView tv=(TextView)gridview.findViewById(R.id.tv1);
            tv.setText(vishal[position]);
            ImageView iv=(ImageView) gridview.findViewById(R.id.iv);
            String s=vishal[position];
            if(s.equals("bleach")){
                iv.setImageResource(R.drawable.bleach);
            }
            else if(s.equals("spa")){
                iv.setImageResource(R.drawable.spa);
            }
            else if(s.equals("facial")){
                iv.setImageResource(R.drawable.facial);
            }}
        else{gridview=convertView;}
        return gridview;
        }

    }

