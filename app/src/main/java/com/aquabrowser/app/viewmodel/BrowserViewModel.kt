package com.aquabrowser.app.viewmodel

import android.webkit.WebView
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

data class BrowserTab(
    val id: Long = System.currentTimeMillis(),
    var url: String = "https://www.google.com",
    var title: String = "New Tab",
    var webView: WebView? = null // Caches the WebView instance so it doesn't reload when switching tabs
)

class BrowserViewModel : ViewModel() {
    val tabs = mutableStateListOf<BrowserTab>()
    var activeTabIndex = mutableStateOf(-1)
    
    // View state: 0 = Home, 1 = Browser, 2 = Tabs, 3 = Extensions, 4 = Settings, 5 = Bookmarks
    var currentScreen = mutableStateOf(0) 
    
    var searchQuery = mutableStateOf("")

    init {
        // Create initial tab
        createNewTab()
    }

    fun createNewTab(url: String = "") {
        tabs.add(BrowserTab(url = url))
        activeTabIndex.value = tabs.size - 1
        if (url.isNotEmpty()) {
            currentScreen.value = 1 // Switch to Browser view
        } else {
            currentScreen.value = 0 // Switch to Home view
        }
    }

    fun loadUrlInActiveTab(url: String) {
        if (activeTabIndex.value >= 0 && activeTabIndex.value < tabs.size) {
            val formattedUrl = if (url.startsWith("http")) url else "https://www.google.com/search?q=$url"
            tabs[activeTabIndex.value].url = formattedUrl
            tabs[activeTabIndex.value].webView?.loadUrl(formattedUrl)
            currentScreen.value = 1
        }
    }

    fun switchTab(index: Int) {
        if (index in tabs.indices) {
            activeTabIndex.value = index
            if (tabs[index].url.isEmpty()) currentScreen.value = 0 else currentScreen.value = 1
        }
    }

    fun closeTab(index: Int) {
        if (index in tabs.indices) {
            tabs[index].webView?.destroy()
            tabs.removeAt(index)
            if (tabs.isEmpty()) {
                createNewTab()
            } else if (activeTabIndex.value >= tabs.size) {
                activeTabIndex.value = tabs.size - 1
            }
            if (tabs[activeTabIndex.value].url.isEmpty()) currentScreen.value = 0 else currentScreen.value = 1
        }
    }
}
