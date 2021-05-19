package com.example.行走的建筑学院.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.行走的建筑学院.ForthActivity;
import com.example.行走的建筑学院.R;

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
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/h1.jpeg?sign=bfae6b2f0889af39c4604c1e66829d0b&t=1620991157").into(image1);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/h2.jpeg?sign=54a0dfc4416d1e44af17dcdb1d9cd7a1&t=1620991179").into(image2);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/h3.jpeg?sign=26620b51a11ef5995dcc2fba32e20bba&t=1620991192").into(image3);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/h4.jpeg?sign=849246fd315716b6148b69e0a4d7ccb2&t=1620991214").into(image4);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryFragment.this.getContext(), ForthActivity.class);
                intent.putExtra("image", 1);
                startActivity(intent);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryFragment.this.getContext(), ForthActivity.class);
                intent.putExtra("image", 2);
                startActivity(intent);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryFragment.this.getContext(), ForthActivity.class);
                intent.putExtra("image", 3);
                startActivity(intent);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GalleryFragment.this.getContext(), ForthActivity.class);
                intent.putExtra("image", 4);
                startActivity(intent);
            }
        });

        return root;
    }
}