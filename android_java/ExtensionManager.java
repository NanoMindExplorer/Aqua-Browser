package com.aquabrowser.extensions;

import org.chromium.base.annotations.NativeMethods;
import org.chromium.base.annotations.JNINamespace;

/**
 * 🌊 Aqua Browser - JNI Bridge for Chromium Extensions
 * This class connects the Android UI with the C++ Extensions System.
 */
@JNINamespace("aqua_browser::extensions")
public class ExtensionManager {
    
    private long mNativeExtensionManager;

    public ExtensionManager() {
        mNativeExtensionManager = ExtensionManagerJni.get().initNativeManager();
    }

    /**
     * Install an extension from a given CRX file path.
     */
    public boolean installExtension(String crxPath) {
        return ExtensionManagerJni.get().installFromCrx(mNativeExtensionManager, crxPath);
    }

    /**
     * Spoof the User-Agent to Desktop mode so Chrome Web Store allows downloading.
     */
    public void enableWebStoreSpoofing() {
        ExtensionManagerJni.get().setSpoofDesktopUserAgent(mNativeExtensionManager, true);
    }

    /**
     * Get a list of installed extensions formatted as JSON.
     */
    public String getInstalledExtensions() {
        return ExtensionManagerJni.get().getInstalledExtensions(mNativeExtensionManager);
    }

    @NativeMethods
    public interface Natives {
        long initNativeManager();
        boolean installFromCrx(long nativeManager, String crxPath);
        void setSpoofDesktopUserAgent(long nativeManager, boolean enable);
        String getInstalledExtensions(long nativeManager);
    }
}
