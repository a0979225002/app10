package com.example.app_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

}
