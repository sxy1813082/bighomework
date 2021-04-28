package com.example.artschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.artschool.ui.slideshow.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        image = (ImageView) findViewById(R.id.image);
        Intent intent = getIntent();
        int value = intent.getIntExtra("image",1);
        if(value == 1){
            Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image1.jpg?sign=ebed5726d37add3042dfc585a6986e31&t=1618832953").into(image);

        }
        if(value == 2){
            Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image2.jpg?sign=0722f65c381acb6e3285fb6852b1a34e&t=1618392345").into(image);

        }
        if(value == 3){
            Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image3.jpg?sign=305393baa18134e64995e1b9abfc92b5&t=1618392373").into(image);

        }
        if(value == 11){
            Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image1.jpg?sign=ebed5726d37add3042dfc585a6986e31&t=1618832953").into(image);

        }
        if(value == 22){
            Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image1.jpg?sign=ebed5726d37add3042dfc585a6986e31&t=1618832953").into(image);

        }
        if(value == 33){
            Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image1.jpg?sign=ebed5726d37add3042dfc585a6986e31&t=1618832953").into(image);

        }
        if(value == 44){
            Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image1.jpg?sign=ebed5726d37add3042dfc585a6986e31&t=1618832953").into(image);

        }

    }

}