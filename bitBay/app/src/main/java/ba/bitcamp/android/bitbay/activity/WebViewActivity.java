package ba.bitcamp.android.bitbay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import ba.bitcamp.android.bitbay.Helper;
import ba.bitcamp.android.bitbay.R;

/**
 * Created by senadin.botic on 29/10/15.
 */
public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Helper.IP_ADDRESS + "/purchaseprocessing");
    }

}
