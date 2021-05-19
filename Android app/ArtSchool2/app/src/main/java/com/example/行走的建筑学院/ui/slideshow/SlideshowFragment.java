package com.example.行走的建筑学院.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.行走的建筑学院.FetchBook;
import com.example.行走的建筑学院.R;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private VideoView mVideoView;
    private TextView text;
    private Button playBtn, stopBtn;
    MediaController mMediaController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        mVideoView = new VideoView(this.getContext());
        mVideoView = (VideoView) root.findViewById(R.id.video);
        mMediaController = new MediaController(this.getContext());
        playBtn = (Button) root.findViewById(R.id.playbutton);
        stopBtn = (Button) root.findViewById(R.id.stopbutton);
        playBtn.setOnClickListener((View.OnClickListener) new mClick());
        stopBtn.setOnClickListener((View.OnClickListener) new mClick());




        return root;
    }
    class mClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
           // String uri = "android.resource://" + getPackageName() + "/" + R.raw.hoshi;  //本地
           // String uri2 = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/video.mp4?sign=ca10496f282b3d98b7efc2e207b24b8c&t=1620915938";  //网络
            //mVideoView.setVideoURI(Uri.parse(uri2));  //本地
            new FetchBook(mVideoView, text).execute("");
            //mVideoView.setVideoURI(Uri.parse(uri2));  //网络
            mMediaController.setMediaPlayer(mVideoView);
            mVideoView.setMediaController(mMediaController);
            if (v == playBtn) {
                mVideoView.start();
            } else if (v == stopBtn) {
                mVideoView.stopPlayback();
            }
        }
    }
}