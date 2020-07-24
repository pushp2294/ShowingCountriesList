package com.example.showingcountrieslist.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.showingcountrieslist.R;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle=getIntent().getExtras();
        LinearLayoutCompat toolbar=findViewById(R.id.custom_toolbar);
        TextView tv_countryname=findViewById(R.id.tv_countryname);
        TextView tv_capital=findViewById(R.id.tv_capital);
        TextView tv_population=findViewById(R.id.tv_population);
        TextView tv_latitude=findViewById(R.id.tv_latitude);
        ImageView iv_flag=findViewById(R.id.iv_flag);
        Glide.with(this).load(bundle.getString("Flag")).error(R.drawable.placeholder).into(iv_flag);
        tv_countryname.setText(bundle.getString("Countryname"));
        tv_capital.setText(bundle.getString("capital"));
        tv_population.setText(bundle.getString("population"));
        tv_latitude.setText(bundle.getString("latlong"));
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }
}