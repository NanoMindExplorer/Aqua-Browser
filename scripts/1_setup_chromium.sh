#!/bin/bash
# Script to setup Chromium build environment for Aqua Browser
# WARNING: Requires at least 150GB of free disk space and 32GB RAM!

set -e

echo "🌊 Starting Aqua Browser Chromium Setup..."

# Install depot_tools
if [ ! -d "$HOME/depot_tools" ]; then
    git clone https://chromium.googlesource.com/chromium/tools/depot_tools.git "$HOME/depot_tools"
fi
export PATH="$PATH:$HOME/depot_tools"

# Create build directory
mkdir -p "$HOME/chromium_build"
cd "$HOME/chromium_build"

echo "Fetching Android Chromium source (This will take a very long time)..."
fetch --nohooks android

cd src

echo "Installing build dependencies..."
./build/install-build-deps.sh --android

echo "Running gclient sync..."
gclient sync -D --with_branch_heads --with_tags

echo "Chromium source is ready for patching."
