#!/bin/bash
set -x

JAVA_SOURCES_DIR=$(echo "$(pwd)" | sed 's#/com/.*##')
CPP_SOURCES_DIR="$(pwd)"/source
PROJECT_ROOT_DIR=$(echo "$(pwd)" | sed 's#/src/.*##')
MINGW_ROOT_DIR="/usr/x86_64-w64-mingw32"
JDK_ROOT_DIR="$HOME/.jdks/corretto-24.0.2"
TARGET_PLATFORM="linux"
OUTPUT_LIBRARY_FILE=""

$JDK_ROOT_DIR/bin/javac -h ./header $JAVA_SOURCES_DIR/com/eriksandsten/chromedpt/utils/WHelper.java

moveFile() {
  cp $OUTPUT_LIBRARY_FILE $PROJECT_ROOT_DIR/$OUTPUT_LIBRARY_FILE
  rm ./$OUTPUT_LIBRARY_FILE
}

OUTPUT_LIBRARY_FILE="wHelper.dll"
/usr/bin/x86_64-w64-mingw32-g++-win32 -static -shared -Wall -O2 -fPIC -o $OUTPUT_LIBRARY_FILE -I"$MINGW_ROOT_DIR/include" -I"$JDK_ROOT_DIR/include/linux" -I"$JDK_ROOT_DIR/include" $CPP_SOURCES_DIR/JniUtils.cpp $CPP_SOURCES_DIR/windows/WHelper.cpp
moveFile $OUTPUT_LIBRARY_FILE, $PROJECT_ROOT_DIR/$OUTPUT_LIBRARY_FILE

OUTPUT_LIBRARY_FILE="libwHelper.so"
g++ -shared -Wall -O2 -fPIC -o $OUTPUT_LIBRARY_FILE -lX11 -I/usr/include/X11 -I"$JDK_ROOT_DIR/include/linux" -I"$JDK_ROOT_DIR/include" $CPP_SOURCES_DIR/JniUtils.cpp $CPP_SOURCES_DIR/unix/WHelper.cpp
moveFile $OUTPUT_LIBRARY_FILE, $PROJECT_ROOT_DIR/$OUTPUT_LIBRARY_FILE
