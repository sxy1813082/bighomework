package com.example.行走的建筑学院;

import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.MultiDex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class mysqlActivity extends AppCompatActivity {
    private DBManager dm;
    private ListView lv;
    private Button add;
    private Button query;
    private Button update;
    private Button delete;
    private EditText name;
    private EditText age;
    private EditText info;
    Connection conn = null;
    Connection co = null;
    Connection addcon = null;
    ArrayList<Map<String,String>> list = new ArrayList<>();
    ArrayList<Map<String,String>> listdata = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mysql);
        MultiDex.install(this);
        lv = (ListView)findViewById(R.id.lv);         //绑定控件
        query = (Button)findViewById(R.id.btnquery);
        add = (Button)findViewById(R.id.btnadd);
        update = (Button)findViewById(R.id.btnupdate);
        delete = (Button)findViewById(R.id.btndelete);
        name=(EditText)findViewById(R.id.et_name);
        age=(EditText)findViewById(R.id.et_age);
        info=(EditText)findViewById(R.id.et_info);
        //用handerler接受消息来更新子线程
        Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        //完成主界面更新,拿到数据
                        listdata = (ArrayList<Map<String, String>>) msg.obj;
                        SimpleAdapter adapter = new SimpleAdapter(mysqlActivity.this, listdata, android.R.layout.simple_list_item_2,
                                new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
                        lv.setAdapter(adapter);

                        break;
                    default:
                        break;
                }
            }

        };

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Map<String,String>> listA = new ArrayList<>();
                if (name.getText().toString().equals("") ||age.toString().equals("") ||info.toString().equals("")){
                    Toast.makeText(mysqlActivity.this,"添加失败！请不要空白提交！",Toast.LENGTH_SHORT).show();
                }
                else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // 反复尝试连接，直到连接成功后退出循环
                            while (!Thread.interrupted()) {
                                try {
                                    Thread.sleep(100);  // 每隔0.1秒尝试连接
                                } catch (InterruptedException e) {
                                    Log.e("TAG", e.toString());
                                }
                                // 2.设置好IP/端口/数据库名/用户名/密码等必要的连接信息
                                String url ="jdbc:mysql://1.117.179.201:3306/art?&useSSL=false";
                                String user = "art";
                                String password = "1Haogeqiu";
                                // 3.连接JDBC
                                Connection con = null;
                                Connection conadd = null;
                                try {
                                    con = DriverManager.getConnection(url, user, password);
                                    Log.i("TAG", "远程连接成功!");
                                } catch (SQLException e) {
                                    Log.e("oops", e.toString());
                                }
                                if (con != null) {
                                    Log.d("TAG", "run: conn is not null");
                                    String sql = "insert into person "+ " values(NULL,"+"'"+
                                            age.getText().toString() +"'"+ "," +"'"+name.getText().toString()+"'"+ ","+"'"+info.getText().toString()+"'"+ ")";
                                    String sqladd ="SELECT * FROM person";
                                    try {
                                        // 创建用来执行sql语句的对象
                                        java.sql.Statement statement = con.createStatement();
                                       // java.sql.Statement statement1 = conadd.createStatement();
                                        // 执行sql查询语句并获取查询信息
                                        statement.execute(sql);
                                        ResultSet rSet = statement.executeQuery(sqladd);
                                        while (rSet.next()){
                                            //  Log.i("TAG", rSet.getString("id") + "\t" + rSet.getString("info"));
                                            HashMap<String,String> map = new HashMap<>();
                                            map.put("name",rSet.getString("name"));
                                            map.put("info","NO"+rSet.getString("id")+rSet.getString("info"));
                                            listA.add(map);
                                        }
                                        Message msg =new Message();
                                        msg.obj = listA;//可以是基本类型，可以是对象，可以是List、map等；
                                        mHandler.sendMessage(msg);
                                    } catch (SQLException e) {
                                        Log.e("TAG", e.toString());
                                    }

                                    try {
                                        con.close();
                                    } catch (SQLException e) {
                                        Log.e("TAG", "关闭连接失败");
                                    }
                                    return;
                                } else {
                                    Log.e("TAG", "run: null accpet" );
                                }
                            }
                        }
                    }).start();
                    Toast.makeText(mysqlActivity.this,"添加成功！可以点击query查询！",Toast.LENGTH_SHORT).show();
                }
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Map<String,String>> listQ = new ArrayList<>();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 反复尝试连接，直到连接成功后退出循环
                        while (!Thread.interrupted()) {
                            try {
                                Thread.sleep(1000);  // 每隔0.1秒尝试连接
                            } catch (InterruptedException e) {
                                Log.e("TAG", e.toString());
                            }
                            // 2.设置好IP/端口/数据库名/用户名/密码等必要的连接信息
                            String url ="jdbc:mysql://1.117.179.201:3306/art?&useSSL=false";
                            String user = "art";
                            String password = "1Haogeqiu";
                            // 3.连接JDBC
                            try {
                                co= DriverManager.getConnection(url, user, password);
                                Log.i("TAG", "远程连接成功!");
                                // conn.close();
                                // return;
                            } catch (SQLException e) {
                                Log.e("oops", e.toString());
                            }
                            if (co != null) {
                                Log.d("TAG", "run: conn is not null");
                                String sql = "SELECT * FROM person";
                                try {
                                    // 创建用来执行sql语句的对象
                                    java.sql.Statement statement = co.createStatement();
                                    // 执行sql查询语句并获取查询信息
                                    ResultSet rSet = statement.executeQuery(sql);
                                    // 迭代打印出查询信息
                                    while (rSet.next()){
                                        //  Log.i("TAG", rSet.getString("id") + "\t" + rSet.getString("info"));
                                        HashMap<String,String> map = new HashMap<>();
                                        map.put("name",rSet.getString("name"));
                                        map.put("info","NO"+rSet.getString("id")+rSet.getString("info"));
                                        listQ.add(map);
                                    }
                                    Message msg =new Message();
                                    msg.obj = listQ;//可以是基本类型，可以是对象，可以是List、map等；
                                    mHandler.sendMessage(msg);

                                } catch (SQLException e) {
                                    Log.e("TAG", "createStatement error");
                                }

                                try {
                                    co.close();
                                } catch (SQLException e) {
                                    Log.e("TAG", "关闭连接失败");
                                }
                                return;
                            } else {
                                Log.e("TAG", "run: null accpet" );
                            }
                        }
                    }
                }).start();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update(v);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(v);
            }
        });

    }
   // public void refresh() { onCreate(null); }
   public void delete(View view){
       Handler mHandler = new Handler() {
           @Override
           public void handleMessage(Message msg) {
               super.handleMessage(msg);
               switch (msg.what) {
                   case 0:
                       //完成主界面更新,拿到数据
                       listdata = (ArrayList<Map<String, String>>) msg.obj;
                       SimpleAdapter adapter = new SimpleAdapter(mysqlActivity.this, listdata, android.R.layout.simple_list_item_2,
                               new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
                       lv.setAdapter(adapter);

                       break;
                   default:
                       break;
               }
           }

       };

       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setIcon(R.drawable.ic_delete);
       builder.setTitle("请输入想要删除的id");
       //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
       View view2 = LayoutInflater.from(this).inflate(R.layout.dialog_input, null);
       //    设置我们自己定义的布局文件作为弹出框的Content
       builder.setView(view2);

       final EditText nameTemp = (EditText)view2.findViewById(R.id.et_delete_name);


       builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
       {
           @Override
           public void onClick(DialogInterface dialog, int which)
           {
               ArrayList<Map<String,String>> listD = new ArrayList<>();
               String deleteId;
               deleteId = nameTemp.getText().toString().trim();
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       // 反复尝试连接，直到连接成功后退出循环
                       while (!Thread.interrupted()) {
                           try {
                               Thread.sleep(100);  // 每隔0.1秒尝试连接
                           } catch (InterruptedException e) {
                               Log.e("TAG", e.toString());
                           }
                           // 2.设置好IP/端口/数据库名/用户名/密码等必要的连接信息
                           String url ="jdbc:mysql://1.117.179.201:3306/art?&useSSL=false";
                           String user = "art";
                           String password = "1Haogeqiu";
                           // 3.连接JDBC
                           Connection con = null;
                           try {
                               con = DriverManager.getConnection(url, user, password);
                               Log.i("TAG", "远程连接成功!");
                           } catch (SQLException e) {
                               Log.e("oops", e.toString());
                           }
                           if (con != null) {
                               Log.d("TAG", "run: deldete is not null");
                               String sql = "DELETE FROM person WHERE id = "+ deleteId;
                               String sqladd ="SELECT * FROM person";
                               try {
                                   // 创建用来执行sql语句的对象
                                   java.sql.Statement statement = con.createStatement();
                                   // java.sql.Statement statement1 = conadd.createStatement();
                                   // 执行sql查询语句并获取查询信息
                                   statement.execute(sql);
                                   ResultSet rSet = statement.executeQuery(sqladd);
                                   while (rSet.next()){
                                       //  Log.i("TAG", rSet.getString("id") + "\t" + rSet.getString("info"));
                                       HashMap<String,String> map = new HashMap<>();
                                       map.put("name",rSet.getString("name"));
                                       map.put("info","NO"+rSet.getString("id")+rSet.getString("info"));
                                       listD.add(map);
                                   }
                                   Message msg =new Message();
                                   msg.obj = listD;//可以是基本类型，可以是对象，可以是List、map等；
                                   mHandler.sendMessage(msg);
                               } catch (SQLException e) {
                                   Log.e("TAG", e.toString());
                               }

                               try {
                                   con.close();
                               } catch (SQLException e) {
                                   Log.e("TAG", "关闭连接失败");
                               }
                               return;
                           } else {
                               Log.e("TAG", "run: null accpet" );
                           }
                       }
                   }
               }).start();
           }
       });
       builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {

           }
       });
       builder.show();
   }



    public void update(View v) {
        Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:
                        //完成主界面更新,拿到数据
                        listdata = (ArrayList<Map<String, String>>) msg.obj;
                        SimpleAdapter adapter = new SimpleAdapter(mysqlActivity.this, listdata, android.R.layout.simple_list_item_2,
                                new String[]{"name", "info"}, new int[]{android.R.id.text1, android.R.id.text2});
                        lv.setAdapter(adapter);

                        break;
                    default:
                        break;
                }
            }

        };

        ArrayList<Map<String,String>> listU = new ArrayList<>();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setIcon(R.drawable.ic_delete);
        builder.setTitle("请输入想要更改的数据");
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view2 = LayoutInflater.from(this).inflate(R.layout.item_mysql, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view2);

        final EditText nameTemp = (EditText)view2.findViewById(R.id.name);
        final EditText idTemp = (EditText)view2.findViewById(R.id.ID);
        final EditText feedback = (EditText)view2.findViewById(R.id.feedback);
        final EditText age = view2.findViewById(R.id.age);

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                String Name;
                String ID;
                String FE;
                String AGE;
                Name = nameTemp.getText().toString().trim();
                ID = idTemp.getText().toString().trim();
                FE = feedback.getText().toString().trim();
                AGE = age.getText().toString().trim();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 反复尝试连接，直到连接成功后退出循环
                        while (!Thread.interrupted()) {
                            try {
                                Thread.sleep(100);  // 每隔0.1秒尝试连接
                            } catch (InterruptedException e) {
                                Log.e("TAG", e.toString());
                            }
                            // 2.设置好IP/端口/数据库名/用户名/密码等必要的连接信息
                            String url ="jdbc:mysql://1.117.179.201:3306/art?&useSSL=false";
                            String user = "art";
                            String password = "1Haogeqiu";
                            // 3.连接JDBC
                            Connection con = null;
                            Connection conadd = null;
                            try {
                                con = DriverManager.getConnection(url, user, password);
                                Log.i("TAG", "远程连接成功!");
                            } catch (SQLException e) {
                                Log.e("oops", e.toString());
                            }
                            if (con != null) {
                                Log.d("TAG", "run: conn is not null");
                                String sql = "UPDATE person SET age = "+AGE+", name = "+"'"+Name+"'"+", info = "+"'"+FE+"'"
                                        + " WHERE id = "+ID;
                                String sqladd ="SELECT * FROM person";
                                try {
                                    // 创建用来执行sql语句的对象
                                    java.sql.Statement statement = con.createStatement();
                                    // java.sql.Statement statement1 = conadd.createStatement();
                                    // 执行sql查询语句并获取查询信息
                                    statement.execute(sql);
                                    ResultSet rSet = statement.executeQuery(sqladd);
                                    while (rSet.next()){
                                        //  Log.i("TAG", rSet.getString("id") + "\t" + rSet.getString("info"));
                                        HashMap<String,String> map = new HashMap<>();
                                        map.put("name",rSet.getString("name"));
                                        map.put("info","NO"+rSet.getString("id")+rSet.getString("info"));
                                        listU.add(map);
                                    }
                                    Message msg =new Message();
                                    msg.obj = listU;//可以是基本类型，可以是对象，可以是List、map等；
                                    mHandler.sendMessage(msg);
                                } catch (SQLException e) {
                                    Log.e("TAG", e.toString());
                                }

                                try {
                                    con.close();
                                } catch (SQLException e) {
                                    Log.e("TAG", "关闭连接失败");
                                }
                                return;
                            } else {
                                Log.e("TAG", "run: null accpet" );
                            }
                        }
                    }
                }).start();


            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();

    }

}