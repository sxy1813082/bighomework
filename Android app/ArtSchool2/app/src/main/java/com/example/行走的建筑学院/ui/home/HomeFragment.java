package com.example.行走的建筑学院.ui.home;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.行走的建筑学院.R;
import com.example.行走的建筑学院.Utility;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private ImageView image;
    private ImageButton image2;
    private ImageButton image3;
    private TextView text;
    private String bingPic;
    private LinearLayout mLinearLayout;
    //下面是评论的功能
    private EditText id;
    private EditText talk;
    private Button commit;
    private ListView listView;
    private ArrayAdapter<String> arrayAdapter;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, String>> dataList;
    ArrayList<String> Idarray = new ArrayList<>();
    ArrayList<String> linearray = new ArrayList<>();

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
        text = (TextView)root.findViewById(R.id.artschool);
        id = (EditText) root.findViewById(R.id.id);
        Log.d("what", String.valueOf(Idarray.size()));
        talk = (EditText) root.findViewById(R.id.talk);
        commit = root.findViewById(R.id.commmit);
        id.setFilters(new InputFilter[]{typeFilter});
        talk.setFilters(new InputFilter[]{typeFilter});
        //这个url是紫禁城对称美学
        String url = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/" +
                "image1.jpg?sign=ebed5726d37add3042dfc585a6986e31&t=1618832953";
        Glide.with(this)
                .load(url)
                .into(image);

        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image2.jpg?sign=0722f65c381acb6e3285fb6852b1a34e&t=1618392345").into(image2);
        Glide.with(this).load("https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/image3.jpg?sign=305393baa18134e64995e1b9abfc92b5&t=1618392373").into(image3);

        //new FetchBook(url, text).execute("");

        String [] imgg ;
        //下面是四张建筑的云存储url
        String url1 = "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/daxing.jpeg?sign=04c49cee7f161c637ff1d8866a284856&t=1620982889";
        String url2= "https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/dibai.jpeg?sign=6f181b701194e385e23c841aaa751d3e&t=1620982910";
        String url3 ="https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/xinjiapo.jpeg?sign=af857c4b3082049d806d2ca9d0849e08&t=1620982922";
        String url4="https://6172-artschool-2gzp7b8l0510e8d3-1305327499.tcb.qcloud.la/luofugong.jpeg?sign=a80fd1621e61ff1365f7859ad9407233&t=1620982944";
        imgg= new String[]{url1,url2,url3,url4};
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
            Glide.with(this).load(imgg[x]).into(img);
           // img.setImageResource(i_mage[x]);
//            //给TextView添加文字
            tv.setText("建筑"+(x+1));
            //把行布局放到linear里
            mLinearLayout.addView(view);
        }
        //下面是评论的功能
        String urlline = "https://artschool-2gzp7b8l0510e8d3-1305327499.ap-shanghai.service.tcloudbase.com/image/v1.0/talk";
        listView = (ListView) root.findViewById(R.id.comment_list);
        // 初始化数据
        String[] arrData = new String[] { "apple", "banana", "orange", "pear" };
        arrayAdapter = new ArrayAdapter<String>(HomeFragment.this.getContext(), android.R.layout.simple_list_item_1, arrData);
        listView.setAdapter(arrayAdapter);
        Utility.setListViewHeightBasedOnChildren(listView);
        dataList = new ArrayList<Map<String, String>>();
        OkGo.get(urlline).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    // Get the JSONArray of book items.
                    JSONArray itemsArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < itemsArray.length(); i ++) {
                        Map<String,String> map = new HashMap<String,String>();
                        JSONObject book = itemsArray.getJSONObject(i);
                        String title = book.getString("id");
                        String authors = book.getString("line");
                        map.put("id","id:"+title);
                        map.put("text", "Item number : " + authors);
                        dataList.add(map);
                    }

                    simpleAdapter = new SimpleAdapter(HomeFragment.this.getContext(),
                            dataList, R.layout.item_comment, new String[] {"id", "text"}
                            , new int[] {R.id.comment_name, R.id.comment_content});
                    listView.setAdapter(simpleAdapter);

                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String Id = id.getText().toString();
            String Text = talk.getText().toString();
                Map<String,String> map = new HashMap<String,String>();
                map.put("id","id:"+Id);
                map.put("text", "Item number : " + Text);
                dataList.add(map);
                simpleAdapter.notifyDataSetChanged();
                JSONObject json1 = new JSONObject();
                JSONObject item1 = new JSONObject();
                try {
                    json1.put("id",Id);
                    json1.put("line",Text);

                    item1.put("data",json1);
                    Log.d("json1", String.valueOf(json1));
                    Log.d("item",String.valueOf(item1));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(JSON, item1.toString());
                OkGo.post(urlline).tag(this)
                       .requestBody(body)
                        .execute(new StringCallback(){
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Log.d(TAG, "onSuccess: wotamiao");
                    }
                });

            }
        });
        return root;
    }
    InputFilter typeFilter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Pattern p = Pattern.compile("[a-zA-Z|\u4e00-\u9fa5]+");
            Matcher m = p.matcher(source.toString());
            if (!m.matches()) return "";
            return null;
        }
    };


    }
