#!/bin/bash
# Apply Aqua Browser patches to Chromium source

set -e

CHROMIUM_SRC="$HOME/chromium_build/src"
PATCH_DIR="$(pwd)/../patches"

if [ ! -d "$CHROMIUM_SRC" ]; then
    echo "Chromium source not found! Run 1_setup_chromium.sh first."
    exit 1
fi

echo "🌊 Applying Aqua Browser patches..."
cd "$CHROMIUM_SRC"

patch -p1 < "$PATCH_DIR/enable_extensions_android.patch"

echo "Configuring GN args for Android build..."
gn gen out/AquaRelease --args='target_os="android" target_cpu="arm64" is_debug=false enable_extensions=true dcheck_always_on=false is_official_build=true android_channel="stable"'

echo "Ready to build! Run: ninja -C out/AquaRelease chrome_public_apk"
