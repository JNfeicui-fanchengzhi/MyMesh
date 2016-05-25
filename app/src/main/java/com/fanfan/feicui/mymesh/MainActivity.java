package com.fanfan.feicui.mymesh;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static String classname=null;
    private             List<Person> list2 = null;
    //    {students:[{name:'刘群',age:25},{name:'刘承宇',age:22}],classname:'Android02'}
    public static final String       TAG   = "dshds";
    private Button mButton;
    private static String message = "http://192.168.1.147:8080/index2.jsp";
    private TextView mTextView1;
    private TextView mTextView2;
    private String  jsonString2 = null;
    private Handler mHandler    = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    mTextView1.setText(list2.get(0).toString());
                    mTextView2.setText(list2.get(1).toString());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.btn_show);
        mTextView1 = (TextView) findViewById(R.id.tv_1);
        mTextView2 = (TextView) findViewById(R.id.tv_2);
        mButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.btn_show:
                                final String path2 = message;
                                new Thread() {
                                    @Override
                                    public void run() {
                                        try {
                                            jsonString2 = HttpUtils.getjson(path2);
                                            list2 = new ArrayList<Person>();
                                            list2 = getPersons("persons", jsonString2);
                                            Log.d(TAG, list2.toString());
                                            mHandler.sendEmptyMessage(1);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }.start();
                                break;
                        }
                    }
                });
    }

    public static List<Person> getPersons(String key, String jsonString) {
        List<Person> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
             classname=jsonObject.getString("classname");

            //返回json的数组
            JSONArray jsonArray = jsonObject.getJSONArray("students");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                Person person = new Person();
                person.setName(jsonObject2.getString("name"));
                person.setAge(jsonObject2.getInt("age"));
                person.setRank(classname);
                list.add(person);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
