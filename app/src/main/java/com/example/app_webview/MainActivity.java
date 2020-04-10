package com.example.app_webview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private EditText max;
    private LocationManager lmgr;//偵測位置
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                   123 );

        }else {
            init();
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        init();
    }

    private void init(){
        queue = Volley.newRequestQueue(this);

        //取得位置管理員
        lmgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //如果在設定中沒有開啟ＧＰＳ
        if (!lmgr.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Intent intent = new  Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent,123);
        }


        webView = findViewById(R.id.webView);
        max = findViewById(R.id.max);
        initWebView();
    }
    //如果還是不開啟ＧＰＳ的情況
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (!lmgr.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Log.v("brad","no gps");
        }
    }

    private void initWebView(){
        //需要給予知道我是個用戶端,擁有瀏覽器的特徵,點擊視窗不會跳出程式到手機瀏覽器內顯示網頁
        webView.setWebViewClient(new WebViewClient());


        WebSettings settings =  webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);//縮放
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(true);


        //這裡的name:壞在前端以 lipin.callFromJS()的方式呼叫
        //callFromJS()為MyJSobject()裡面寫的方法
        webView.addJavascriptInterface(new MyJSobject(),"lipin");

        //直接把網站顯示在webview
//      webView.loadUrl("https://www.iii.org.tw");

        //自己加入一個資源檔,須先在右邊app資料夾右鍵new->Folder->assets Folder
        //在assets內建立一個html檔
        //在這裡asset不要加s,因為這裡的webView.loadUrl只有指定一個檔案
        webView.loadUrl("file:///android_asset/lipin.html");

    }
    //開啟程式時打開ＧＰＳ
    @Override
    protected void onStart() {
        super.onStart();
        //打開ＧＰＳ功能
        // 參數一：打開ＧＰＳ提供者
        // 參數二：更新頻率
        // 參數三：多少距離更新一次（以公尺計算）
        // 參數三：監聽功能,需實做
        myListener = new MyListener();
        lmgr.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                0,0,myListener);
    }

    public void test2(View view){
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=AIzaSyDn1AwRWz2oQX_oOr5CobxB1PRe-Vg2eyA";
        //上面那串url內有一個％s 而那個％s的值是會從EditText max的欄位抓,目的讓使用者自己輸入地址,然後會轉成json給我們處理
        String url2 = String.format(url,max.getText().toString());
        Log.v("brad",max.getText().toString());
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        parseJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(request);
    }

    private void parseJSON(String json){
        try {
            JSONObject root = new JSONObject(json);//拿取整個網頁
            String statis = root.getString("status");//抓出status的物件
            //在網頁的json中statis是判斷是否有取得地址 有取得正確會是"status" : "OK"
            if (statis.equals("OK")){
                JSONArray results = root.getJSONArray("results");//取得results內的陣列
                JSONObject result = results.getJSONObject(0);
                JSONObject geometry = result.getJSONObject("geometry");
                JSONObject location = geometry.getJSONObject("location");
                double lat = location.getDouble("lat");
                double lng = location.getDouble("lng");
                Log.v("brad","geocoding=>"+lat+":"+lng);
                webView.loadUrl(String.format("javascript:moveTo(%f,%f)",lat,lng));
            }else {
                Log.v("brad","not ok:"+statis);
            }
        }catch (Exception e){
            Log.v("brad",e.toString());
        }
    }

    //GPS監聽
    private MyListener myListener;
    private class MyListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            //在這可以get你想偵測東西
            double lat = location.getLatitude();//拿取當前經度
            double lng = location.getLongitude();//拿取當前緯度
//            Log.v("brad",lat+""+lng);

            //將經緯度顯示在EditText max上面
            Message message = new Message();
            Bundle data = new Bundle();
            data.putString("urname",lat+":"+lng);
            message.setData(data);
            uIhandler.sendMessage(message);

//            webView.loadUrl(String.format("javascript:moveTo(%f,%f)",lat,lng));
            //將經緯度傳到html的map.html身上
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    //程式暫停的時候關閉ＧＰＳ
    @Override
    protected void onPause() {
        super.onPause();
        lmgr.removeUpdates(myListener);
    }

    //修改返回鍵,因為Ｗebview按返回會直接結束該支程式
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();//webview只要能夠返回就讓他返回,不會跳出程式
        }else {
            super.onBackPressed();
        }
    }
    public void test1(View view){
        //給予對話匡,可以去抓android原生寫的東西把值給予html
        String strMax =  max.getText().toString();

        //呼叫html內的script:test1()功能
        webView.loadUrl(String.format("javascript:test1(%s)",strMax));
    }
    //定義在javascript呼叫的方法
    //因為有些機種得不到傳遞的值,所以使用handler的方式取值
    public class MyJSobject{
        //需要註解這個@JavascriptInterface
        @JavascriptInterface
        public void callFromJS(String urname){
            Log.v("brad","ok:"+urname);
            Message message = new Message();//將Message對象發送給目標對象
            Bundle data = new Bundle();//新增一個存放參數的Bundle
            data.putString("urname",urname);//將參數存放到Bundle
            message.setData(data);//將Bundle內的參數給予Message
            uIhandler.sendMessage(message);//將有參數Message給予handler
//            max.setText(urname);

        }
    }
    private UIhandler uIhandler = new UIhandler();
    private class UIhandler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String urname = msg.getData().getString("urname");
            //把Message內的參數拿出來
//            max.setText(urname);
        }
    }

}
