package com.gameball.school;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.gameball.api.MMFavor;
import com.gameball.api.MMToast;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class MMFragment3NowOnSale extends Fragment {
    private View mFragmentView;
    @BindView(2131689678)
    WebView mWebView;

    class C02751 implements OnKeyListener {
        C02751() {
        }

        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() != 0 || keyCode != 4 || !MMFragment3NowOnSale.this.mWebView.canGoBack()) {
                return false;
            }
            MMFragment3NowOnSale.this.mWebView.goBack();
            return true;
        }
    }

    class MyWebView extends WebViewClient {
        MyWebView() {
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("tag", "url：" + url);
            int haveNP = url.indexOf("np=yes");
            Log.i("tag", "haveNP：" + haveNP);
            if (url.startsWith("line://") || url.startsWith("intent://") || url.startsWith("market://") || url.startsWith("jhs://") || haveNP != -1) {
                Intent intent = null;
                try {
                    intent = Intent.parseUri(url, 0);
                    MMFragment3NowOnSale.this.startActivity(intent);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                MMFragment3NowOnSale.this.startActivity(intent);
            } else {
                view.loadUrl(url);
            }
            return true;
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (!(MMFavor.getInstance().account.getVipReappear().equals("N") || MMFavor.getInstance().account.getVipReappear().equals(""))) {
            this.mFragmentView = null;
        }
        if (this.mFragmentView == null) {
            this.mFragmentView = inflater.inflate(C0308R.layout.fragment_2, null);
            ButterKnife.bind((Object) this, this.mFragmentView);
            if (checkAppConnectionStatus(getActivity())) {
                String url = getString(C0308R.string.url) + "vip";
                Map<String, String> extraHeaders = new HashMap();
                extraHeaders.put("Access-Token", MMFavor.getInstance().account.getToken());
                this.mWebView.loadUrl(url, extraHeaders);
                this.mWebView.getSettings().setJavaScriptEnabled(true);
                this.mWebView.setWebViewClient(new MyWebView());
                this.mWebView.setOnKeyListener(new C02751());
            } else {
                MMToast.showTextToast(MMApplication.getAppContext().getString(C0308R.string.connection_fail));
            }
        }
        if (this.mFragmentView.getParent() != null) {
            ((ViewGroup) this.mFragmentView.getParent()).removeAllViews();
        }
        return this.mFragmentView;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void onPause() {
        super.onPause();
    }

    public void onResume() {
        super.onResume();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public boolean checkAppConnectionStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService("connectivity");
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected()) {
            return true;
        }
        return false;
    }
}
