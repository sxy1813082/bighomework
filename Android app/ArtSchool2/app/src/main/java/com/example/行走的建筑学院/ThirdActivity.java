package com.example.行走的建筑学院;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.行走的建筑学院.ui.slideshow.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    private static final String TAG = "oops";
    private ViewPager mViewPager;
    private TextView mTvPagerTitle;

    public List<ImageView> mImageList;//轮播的图片集合
    private String[] mImageTitles;//标题集合
    private int previousPosition = 0;//前一个被选中的position
    private List<View> mDots;//小点

    private boolean isStop = false;//线程是否停止
    private static int PAGER_TIOME = 2000;//间隔时间

    // 在values文件假下创建了pager_image_ids.xml文件，并定义了4张轮播图对应的id，用于点击事件
    private int[] imgae_ids = new int[]{R.id.image_art,R.id.image_art2,R.id.image_art3,R.id.donut_circle};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        init();
    }
    /**
     * 第一步、初始化控件
     */
    public void init() {
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mTvPagerTitle = (TextView) findViewById(R.id.tv_pager_title);
        initData();//初始化数据
        initView();//初始化View，设置适配器
        autoPlayView();//开启线程，自动播放
    }

    /**
     * 第二步、初始化数据（图片、标题、点击事件）
     */
    public void initData() {
        //初始化标题列表和图片
        String url1 = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/daxing.jpeg?sign=04c49cee7f161c637ff1d8866a284856&t=1620982889";
        String url2= "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/dibai.jpeg?sign=6f181b701194e385e23c841aaa751d3e&t=1620982910";
        String url3 ="https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/xinjiapo.jpeg?sign=af857c4b3082049d806d2ca9d0849e08&t=1620982922";
        String url4="https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/luofugong.jpeg?sign=a80fd1621e61ff1365f7859ad9407233&t=1620982944";
        mImageTitles = new String[]{"大兴机场建筑设计","迪拜火焰楼","新加坡著名建筑","罗浮宫建筑"};
        String[] imageRess = new String[]{url1,url2,url3,url4};

        //添加图片到图片列表里
        mImageList = new ArrayList<>();
        ImageView iv;
        for (int i = 0; i < 4; i++) {

            iv = new ImageView(this);
            ImageView finalIv = iv;
            SimpleTarget<Drawable> simpleTarget = new SimpleTarget<Drawable>() {
                @Override
                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                    finalIv.setBackground(resource);
                }
            };
           // iv.setBackgroundResource(imageRess[i]);//设置图片
            Glide.with(this).load(imageRess[i]).into(simpleTarget);

            iv.setId(imgae_ids[i]);//顺便给图片设置id
            iv.setOnClickListener(new pagerImageOnClick());//设置图片点击事件
            mImageList.add(iv);
        }
        if(mImageList.size()<=0){
            Log.e(TAG, "wtf");
        }

        //添加轮播点
        LinearLayout linearLayoutDots = (LinearLayout) findViewById(R.id.lineLayout_dot);
        mDots = addDots(linearLayoutDots,fromResToDrawable(ThirdActivity.this,R.drawable.ic_dot_normal),mImageList.size());//其中fromResToDrawable()方法是我自定义的，目的是将资源文件转成Drawable


    }

    //图片点击事件
    private class pagerImageOnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.image_art:
                    Toast.makeText(ThirdActivity.this, "大兴机场建筑设计", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.image_art2:
                    Toast.makeText(ThirdActivity.this, "迪拜火焰楼", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.image_art3:
                    Toast.makeText(ThirdActivity.this, "新加坡著名建筑", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.donut_circle:
                    Toast.makeText(ThirdActivity.this, "罗浮宫建筑", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
    /**
     *  第三步、给PagerViw设置适配器，并实现自动轮播功能
     */
    public void initView(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(mImageList, mViewPager);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //伪无限循环，滑到最后一张图片又从新进入第一张图片
                int newPosition = position % mImageList.size();
                // 把当前选中的点给切换了, 还有描述信息也切换
                mTvPagerTitle.setText(mImageTitles[newPosition]);//图片下面设置显示文本
                //设置轮播点
                LinearLayout.LayoutParams newDotParams = (LinearLayout.LayoutParams) mDots.get(newPosition).getLayoutParams();
                newDotParams.width = 24;
                newDotParams.height = 24;

                LinearLayout.LayoutParams oldDotParams = (LinearLayout.LayoutParams) mDots.get(previousPosition).getLayoutParams();
                oldDotParams.width = 16;
                oldDotParams.height = 16;

                // 把当前的索引赋值给前一个索引变量, 方便下一次再切换.
                previousPosition = newPosition;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setFirstLocation();
    }

    /**
     * 第四步：设置刚打开app时显示的图片和文字
     */
    private void setFirstLocation() {
        mTvPagerTitle.setText(mImageTitles[previousPosition]);
        // 把ViewPager设置为默认选中Integer.MAX_VALUE / t2，从十几亿次开始轮播图片，达到无限循环目的;
        if(mImageList.size()<=0){
            Log.e(TAG, "单选的菜品 -------------");
        }
        int m = (Integer.MAX_VALUE / 2) % mImageList.size();
        int currentPosition = Integer.MAX_VALUE / 2 - m;
        mViewPager.setCurrentItem(currentPosition);
    }

    /**
     * 第五步: 设置自动播放,每隔PAGER_TIOME秒换一张图片
     */
    private void autoPlayView() {
        //自动播放图片
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                        }
                    });
                    SystemClock.sleep(PAGER_TIOME);
                }
            }
        }).start();
    }



    /**
     * 资源图片转Drawable
     * @param context
     * @param resId
     * @return
     */
    public Drawable fromResToDrawable(Context context, int resId) {
        return context.getResources().getDrawable(resId);
    }


    /**
     * 动态添加一个点
     * @param linearLayout 添加到LinearLayout布局
     * @param backgount 设置
     * @return
     */
    public int addDot(final LinearLayout linearLayout, Drawable backgount) {
        final View dot = new View(this);
        LinearLayout.LayoutParams dotParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dotParams.width = 16;
        dotParams.height = 16;
        dotParams.setMargins(4,0,4,0);
        dot.setLayoutParams(dotParams);
        dot.setBackground(backgount);
        dot.setId(View.generateViewId());
        linearLayout.addView(dot);
        return dot.getId();
    }

    /**
     * 添加多个轮播小点到横向线性布局
     * @param linearLayout
     * @param backgount
     * @param number
     * @return
     */
    public List<View> addDots(final LinearLayout linearLayout, Drawable backgount, int number){
        List<View> dots = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            int dotId = addDot(linearLayout,backgount);
            dots.add(findViewById(dotId));
        }
        return dots;
    }
}