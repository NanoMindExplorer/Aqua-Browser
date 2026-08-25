package com.aquabrowser.app.components

import android.graphics.Bitmap
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.aquabrowser.app.viewmodel.BrowserTab

@Composable
fun BrowserView(tab: BrowserTab, onTitleChange: (String) -> Unit) {
    var webViewRef by remember { mutableStateOf<WebView?>(null) }

    // Handle Android hardware back button
    BackHandler(enabled = webViewRef?.canGoBack() == true) {
        webViewRef?.goBack()
    }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            if (tab.webView != null) {
                tab.webView!!
            } else {
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.useWideViewPort = true
                    settings.loadWithOverviewMode = true
                    settings.setSupportZoom(true)
                    settings.builtInZoomControls = true
                    settings.displayZoomControls = false

                    webViewClient = object : WebViewClient() {
                        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                            super.onPageStarted(view, url, favicon)
                            tab.url = url ?: ""
                        }
                    }

                    webChromeClient = object : WebChromeClient() {
                        override fun onReceivedTitle(view: WebView?, title: String?) {
                            super.onReceivedTitle(view, title)
                            val newTitle = title ?: "Aqua Browser"
                            tab.title = newTitle
                            onTitleChange(newTitle)
                        }
                    }
                    
                    loadUrl(tab.url)
                    tab.webView = this
                }
            }
        },
        update = { webView ->
            webViewRef = webView
            if (webView.url != tab.url && tab.url.isNotEmpty()) {
                webView.loadUrl(tab.url)
            }
        }
    )
}
