package com.example.artschool.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.example.artschool.MainActivity;
import com.example.artschool.R;
import com.example.artschool.SecondActivity;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ImageView image;
    private ImageButton image2;
    private ImageButton image3;
    private String bingPic;
    private LinearLayout mLinearLayout;
    public int[] i_mage = {R.drawable.image_art, R.drawable.image_art2, R.drawable.image_art3, R.drawable.image_art};

    private static final String TAG = "Extract";
    //    private LinearLayout mLinearLayout;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        image= (ImageView) root.findViewById(R.id.show_image);
        image2 = root.findViewById(R.id.show_image2);
        image3 = root.findViewById(R.id.show_image3);

        String url = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image1.jpg?sign=ebed5726d37add3042dfc585a6986e31&t=1618832953";
        Glide.with(this)
                .load(url)
                .into(image);

       // Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image1.jpg?sign=ebed5726d37add3042dfc585a6986e31&t=1618832953").into(image);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image2.jpg?sign=0722f65c381acb6e3285fb6852b1a34e&t=1618392345").into(image2);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image3.jpg?sign=305393baa18134e64995e1b9abfc92b5&t=1618392373").into(image3);

        mLinearLayout= (LinearLayout) root.findViewById(R.id.linear);
        //开始添加数据
        for(int x=0; x<4; x++){
            //寻找行布局，第一个参数为行布局ID，第二个参数为这个行布局需要放到那个容器上
            View view=LayoutInflater.from(this.getContext()).inflate(R.layout.item_text,mLinearLayout,false);
            //通过View寻找ID实例化控件
            ImageView img= (ImageView) view.findViewById(R.id.imageView);
            //实例化TextView控件
            TextView tv= (TextView) view.findViewById(R.id.textView);
            //将int数组中的数据放到ImageView中
            img.setImageResource(i_mage[x]);
//            //给TextView添加文字
            tv.setText((x+1)+"page");
            //把行布局放到linear里
            mLinearLayout.addView(view);
        }
        //第二组轮播

        return root;
    }


    }
