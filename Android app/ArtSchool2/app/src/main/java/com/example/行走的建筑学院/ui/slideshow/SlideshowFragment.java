package com.example.行走的建筑学院.ui.slideshow;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.行走的建筑学院.FetchBook;
import com.example.行走的建筑学院.R;
import com.example.行走的建筑学院.Utility;
import com.example.行走的建筑学院.ui.home.HomeFragment;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    private VideoView mVideoView;
    private TextView text;
    private Button playBtn, stopBtn;
    MediaController mMediaController;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private List<Map<VideoView, String>> dataList;
    ArrayList<String> Idarray = new ArrayList<>();


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
       // listView = (ListView) root.findViewById(R.id.video_list);
        return root;
    }
    class mClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            new FetchBook(mVideoView, text).execute("");
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