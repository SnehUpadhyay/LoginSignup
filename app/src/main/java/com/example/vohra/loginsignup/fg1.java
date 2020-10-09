   package com.example.vohra.loginsignup;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class fg1 extends Fragment {
    static final String[] number=new String[]{"bleach","spa","facial"};
    GridView gv1;
    public fg1() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gv1=(GridView) getView().findViewById(R.id.gv);
        gv1.setAdapter(new Customadapter(getContext(),number));
        gv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),((TextView)view.findViewById(R.id.tv1)).getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_fg1, container, false);
    }
    public static fg1 newInstance(){
        fg1 f=new fg1();
        return f;
    }

}
