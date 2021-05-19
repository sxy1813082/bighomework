package com.example.行走的建筑学院;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.ImageView;

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

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ImageView image;
    private ImageButton image2;
    private ImageButton image3;
    private ImageView image4;

    // Member variables.

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
//    //下面是gallery页面的图片点击事件
//    public void show_gallery_image1(View view){
//        Intent intent = new Intent(this, ForthActivity.class);
//        intent.putExtra("image", 1);
//        startActivity(intent);
//    }
//    public void show_gallery_image2(View view){
//        Intent intent = new Intent(this, ForthActivity.class);
//        intent.putExtra("image", 2);
//        startActivity(intent);
//    }
//    public void show_gallery_image3(View view){
//        Intent intent = new Intent(this, ForthActivity.class);
//        intent.putExtra("image", 3);
//        startActivity(intent);
//    }
//    public void show_gallery_image4(View view){
//        Intent intent = new Intent(this, ForthActivity.class);
//        intent.putExtra("image", 4);
//        startActivity(intent);
//    }

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

    }

}