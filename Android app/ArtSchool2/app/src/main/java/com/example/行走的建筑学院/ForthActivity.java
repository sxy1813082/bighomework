package com.example.行走的建筑学院;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ForthActivity extends AppCompatActivity {
    private ImageView image;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        String url1 = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/h1.jpeg?sign=5e1652fed5350db8037ea71865cefd52&t=1620991275";
        String url2 = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/h2.jpeg?sign=c3aa2ce8952cdb7dc50d7834e4f947ee&t=1620991288";
        String url3 = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/h3.jpeg?sign=bf80dfd12d71801bb22f6481bff78d4c&t=1620994724";
        String url4 = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/h4.jpeg?sign=960cf290c011fb9e73af3b0d0102fbc0&t=1620991316";
        image = (ImageView) findViewById(R.id.image);
        text = (TextView)findViewById(R.id.text);
        Intent intent = getIntent();
        int value = intent.getIntExtra("image",1);
        if(value == 1){
            Glide.with(this).load(url1).into(image);
            text.setText(R.string.ku);
        }
        if(value == 2){
            Glide.with(this).load(url2).into(image);
            text.setText(R.string.zun);
        }
        if(value == 3){
            Glide.with(this).load(url3).into(image);
            text.setText(R.string.dubai);
        }
        if(value == 4){
            Glide.with(this).load(url4).into(image);
            text.setText(R.string.ta);
        }
    }
}