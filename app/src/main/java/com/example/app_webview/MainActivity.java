package com.example.app_webview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private EditText max;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        max = findViewById(R.id.max);

        initWebView();
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
            max.setText(urname);
        }
    }

}
