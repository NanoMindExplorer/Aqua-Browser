package com.aquabrowser.app.theme

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import org.json.JSONObject

/**
 * 🌊 Aqua Browser - Chrome Theme Engine
 * Parses standard Chrome Web Store theme manifests (manifest.json)
 * and maps them to Jetpack Compose colors dynamically.
 */
class ThemeManager private constructor() {
    
    // Default Aqua Theme Colors
    var frameColor = mutableStateOf(Color(0xFF0F172A)) // Background
    var toolbarColor = mutableStateOf(Color(0xFF1E293B)) // AppBar / Toolbar
    var textColor = mutableStateOf(Color(0xFFFFFFFF))
    var tabText = mutableStateOf(Color(0xFF94A3B8))
    var accentColor = mutableStateOf(Color(0xFF38BDF8))

    companion object {
        val instance = ThemeManager()
    }

    /**
     * Parses a Chrome Extension theme manifest.json
     * Example structure:
     * {
     *   "theme": {
     *     "colors": {
     *       "frame": [15, 23, 42],
     *       "toolbar": [30, 41, 59],
     *       "tab_text": [255, 255, 255]
     *     }
     *   }
     * }
     */
    fun applyChromeThemeFromJson(jsonString: String) {
        try {
            val root = JSONObject(jsonString)
            if (!root.has("theme")) return
            
            val themeObj = root.getJSONObject("theme")
            if (!themeObj.has("colors")) return
            
            val colors = themeObj.getJSONObject("colors")

            if (colors.has("frame")) {
                frameColor.value = parseRgbArray(colors.getJSONArray("frame"))
            }
            if (colors.has("toolbar")) {
                toolbarColor.value = parseRgbArray(colors.getJSONArray("toolbar"))
            }
            if (colors.has("tab_text")) {
                textColor.value = parseRgbArray(colors.getJSONArray("tab_text"))
            }
            if (colors.has("bookmark_text")) {
                tabText.value = parseRgbArray(colors.getJSONArray("bookmark_text"))
            }
            if (colors.has("button_background")) {
                accentColor.value = parseRgbArray(colors.getJSONArray("button_background"))
            }

            Log.d("AquaThemeEngine", "Chrome Theme Applied Successfully!")
        } catch (e: Exception) {
            Log.e("AquaThemeEngine", "Failed to parse Chrome Theme", e)
        }
    }

    private fun parseRgbArray(jsonArray: org.json.JSONArray): Color {
        if (jsonArray.length() >= 3) {
            val r = jsonArray.getInt(0)
            val g = jsonArray.getInt(1)
            val b = jsonArray.getInt(2)
            return Color(r, g, b)
        }
        return Color.Black
    }

    // Reset to default Aqua design
    fun resetToDefault() {
        frameColor.value = Color(0xFF0F172A)
        toolbarColor.value = Color(0xFF1E293B)
        textColor.value = Color(0xFFFFFFFF)
        tabText.value = Color(0xFF94A3B8)
        accentColor.value = Color(0xFF38BDF8)
    }
}
