#!/bin/bash
set -x
# To get the X11 library - sudo apt install libx11-dev
# To get MinGW for x64 - sudo apt install g++-mingw-w64-x86-64

JAVA_SOURCES_DIR=src/main/java
CPP_SOURCES_DIR=src/main/java/com/eriksandsten/chromedtp/native/source
USER_ROOT_DIR=$(wslpath "$(wslvar USERPROFILE)") || $HOME
MINGW_ROOT_DIR=/usr/x86_64-w64-mingw32
JDK_ROOT_DIR=$USER_ROOT_DIR/.jdks/corretto-24.0.2
JAVAC_COMPILER=$(ls $JDK_ROOT_DIR/bin/javac*) #.exe on Windows, no file extension on Linux

cat << EOF
==============================================
            Creating WHelper.h
==============================================
EOF
$JAVAC_COMPILER -h ./header $JAVA_SOURCES_DIR/com/eriksandsten/chromedtp/utils/WHelper.java

cat << EOF
==============================================
           Compiling wHelper.dll
==============================================
EOF
OUTPUT_LIBRARY_FILE=wHelper.dll
/usr/bin/x86_64-w64-mingw32-g++-win32 -D_WIN32 -static -shared -Wall -O2 -fPIC -o $OUTPUT_LIBRARY_FILE -I$MINGW_ROOT_DIR/include -I$JDK_ROOT_DIR/include/linux -I$JDK_ROOT_DIR/include/win32 -I$JDK_ROOT_DIR/include $CPP_SOURCES_DIR/JniUtils.cpp $CPP_SOURCES_DIR/windows/WHelper.cpp

cat << EOF
==============================================
          Compiling libwHelper.so
==============================================
EOF
OUTPUT_LIBRARY_FILE=libwHelper.so
g++ -shared -Wall -O2 -fPIC -o $OUTPUT_LIBRARY_FILE -lX11 -I/usr/include/X11 -I$JDK_ROOT_DIR/include/linux -I$JDK_ROOT_DIR/include $CPP_SOURCES_DIR/JniUtils.cpp $CPP_SOURCES_DIR/unix/WHelper.cpp
