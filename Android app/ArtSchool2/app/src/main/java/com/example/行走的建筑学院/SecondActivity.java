package com.example.行走的建筑学院;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SecondActivity extends AppCompatActivity {
    private ImageView image;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String url1 = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image1.jpg?sign=ebed5726d37add3042dfc585a6986e31&t=1618832953";
        String url2 = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image2.jpg?sign=0722f65c381acb6e3285fb6852b1a34e&t=1618392345";
        String url3 = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image3.jpg?sign=305393baa18134e64995e1b9abfc92b5&t=1618392373";
        image = (ImageView) findViewById(R.id.image);
        text = (TextView)findViewById(R.id.text);
        Intent intent = getIntent();
        int value = intent.getIntExtra("image",1);
        if(value == 1){
            Glide.with(this).load(url1).into(image);
            text.setText(R.string.duichen);
        }
        if(value == 2){
            Glide.with(this).load(url2).into(image);
            text.setText(R.string.gulou);
        }
        if(value == 3){
            Glide.with(this).load(url3).into(image);
            text.setText(R.string.zun);
        }
    }

}