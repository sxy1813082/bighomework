package com.example.artschool.ui.gallery;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.artschool.R;

public class GalleryFragment extends Fragment {
    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        image1 = root.findViewById(R.id.gallery_image);
        image2 = root.findViewById(R.id.gallery_image2);
        image3 = root.findViewById(R.id.gallery_image3);
        image4 = root.findViewById(R.id.gallery_image4);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image2.jpg?sign=0722f65c381acb6e3285fb6852b1a34e&t=1618392345").into(image1);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image2.jpg?sign=0722f65c381acb6e3285fb6852b1a34e&t=1618392345").into(image2);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image2.jpg?sign=0722f65c381acb6e3285fb6852b1a34e&t=1618392345").into(image3);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image2.jpg?sign=0722f65c381acb6e3285fb6852b1a34e&t=1618392345").into(image4);


        return root;
    }
}