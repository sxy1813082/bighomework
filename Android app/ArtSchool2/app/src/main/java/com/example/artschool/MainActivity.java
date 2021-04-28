package com.example.artschool;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artschool.ui.slideshow.ViewPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ImageView image;
    private ImageButton image2;
    private ImageButton image3;
    private ImageView image4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image= (ImageView)findViewById(R.id.show_image);
        image2 = findViewById(R.id.show_image2);
        image3 = findViewById(R.id.show_image3);
        image4 = findViewById(R.id.imageView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }


    public static Bitmap getBitMBitmap(String urlpath) {
        Bitmap map = null;
        try {
            URL url = new URL(urlpath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void showImage(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("image", 1);

        startActivity(intent);
    }
    public void showImage1(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("image", 2);
        startActivity(intent);
    }
    public void showImage2(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("image", 3);
        startActivity(intent);
    }
    //下面是gallery页面的图片点击事件
    public void show_gallery_image1(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("image", 11);
        startActivity(intent);
    }
    public void show_gallery_image2(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("image", 22);
        startActivity(intent);
    }
    public void show_gallery_image3(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("image", 33);
        startActivity(intent);
    }
    public void show_gallery_image4(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("image", 44);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    public void showLunbo(View view){
        Intent intent = new Intent(this,ThirdActivity.class);
        startActivity(intent);
//        Intent intent = new Intent(this, GalleryFragment.class);
//        Bitmap bitmap = setimage(image4);
//        intent.putExtra("image",Bitmap2Bytes(bitmap));
//        startActivity(intent);

    }


//    public Bitmap setimage(ImageView view1){
//        Bitmap image = ((BitmapDrawable)view1.getDrawable()).getBitmap();
//        Bitmap bitmap1 = Bitmap.createBitmap(image);
//        return bitmap1;
//    }
//    private byte[] Bitmap2Bytes(Bitmap bm){
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        return baos.toByteArray();
//    }



}