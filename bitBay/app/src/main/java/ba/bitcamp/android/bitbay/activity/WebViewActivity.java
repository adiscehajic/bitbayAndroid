package ba.bitcamp.android.bitbay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import ba.bitcamp.android.bitbay.Helper;
import ba.bitcamp.android.bitbay.R;

/**
 * This class is used to load webpages in our application from url that we set here. For now its
 * PayPal purchase processing page.
 */
public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        //get view from layout and set url which will be loaded on it
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(Helper.IP_ADDRESS + "/purchaseprocessing");
    }

}
