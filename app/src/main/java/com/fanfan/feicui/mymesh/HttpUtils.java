package com.fanfan.feicui.mymesh;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/5/25.
 */
public class HttpUtils {
    public static String getjson(String path) throws Exception {
        //获得URL的对象
        URL url = new URL(path);
        //打开连接  获得HttpURLConnection的对象
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setConnectTimeout(3 * 1000);//设置超时时间
        connection.setRequestMethod("GET");//设置请求方式
        InputStream inputStream = connection.getInputStream();
        String jsonstring = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int leng = 0;
        while((leng = inputStream.read(bytes)) != -1){
            outputStream.write(bytes,0,leng);
        }
        jsonstring= outputStream.toString();
        outputStream.close();
        inputStream.close();
        return jsonstring;
    }
}
