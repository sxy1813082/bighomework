package com.example.artschool;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView image;
    private ImageView image2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image=(ImageView)findViewById(R.id.show_image);
        image2 = (ImageView)findViewById(R.id.show_image2);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image1.jpg?sign=3c1ec60a61fb6066b2a88fac2cfaf819&t=1617808084").into(image);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image1.jpg?sign=3c1ec60a61fb6066b2a88fac2cfaf819&t=1617808084").into(image2);


    }

}