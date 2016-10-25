package com.novaparse.scorpionbyte5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

	public WebView mMainWebView;
	public String mMainWebViewTempUrl;
	public boolean mLoading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mMainWebView = (WebView) findViewById(R.id.mMainWebView);
		mMainWebView.getSettings().setJavaScriptEnabled(true);
		mMainWebView.loadUrl("http://www.scorpionbyte.com/m/myfeed.php?");
		mMainWebView.setWebViewClient(new MainWebViewClient());
	}

	/**
	 * @Override protected void onPause() { super.onPause(); mMainWebViewTempUrl
	 *           = mMainWebView.getUrl();
	 *           mMainWebView.loadUrl("http://www.scorpionbyte.com/m/loading.html"
	 *           ); }
	 * @Override protected void onResume() { super.onResume(); if
	 *           (!mMainWebView.
	 *           equals("http://www.scorpionbyte.com/m/loading.html") &&
	 *           mMainWebViewTempUrl != null) {
	 *           mMainWebView.loadUrl(mMainWebViewTempUrl); } }
	 */

	private class MainWebViewClient extends WebViewClient {

		@Override
		public void onPageFinished(WebView view, String url) {
			mMainWebView.loadUrl(url);
		}

		@Override
		public boolean shouldOverrideUrlLoading(WebView webview, String url) {
			if (url.contains("scorpionbyte.com")) {
				webview.loadUrl(url);
			} else {
				Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				startActivity(i);
			}
			return true;
		}
	}

	@Override
	public void onBackPressed() {
		if (mMainWebView.canGoBack())
			mMainWebView.goBack();
		else
			super.onBackPressed();
	}

}