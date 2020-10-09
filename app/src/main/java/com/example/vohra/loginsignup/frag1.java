package com.example.vohra.loginsignup;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class frag1 extends Fragment {
    public frag1()
    {

    }
    Button btn;
    RatingBar rb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag1, container, false);
        btn = (Button) view.findViewById(R.id.btn);
        rb = (RatingBar) view.findViewById(R.id.rb);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float rv = rb.getRating();
                Toast.makeText(frag1.this.getActivity(), "RATING IS: " + rv, Toast.LENGTH_SHORT).show();
            }
        });

    return view;
    }


    }

