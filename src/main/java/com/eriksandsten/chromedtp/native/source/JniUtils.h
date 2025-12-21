#include <jni.h>
#include <string>
#include <vector>
#ifdef _WIN32
    #include <windows.h>
#endif

#ifndef _WHELPER_UTILS_H_
#define _WHELPER_UTILS_H_

const std::wstring SEPARATOR = L"||";

#ifdef _WIN32
    std::wstring getWin32WindowTitle         (HWND windowHandle);
    jstring      getWin32WindowTitle_jString (JNIEnv* jniEnv, HWND windowHandle);
    std::wstring getWin32WindowClassName     (HWND windowHandle);
    jobject      convertHWNDToJIntegerObject (JNIEnv* jniEnv, const HWND& windowHandle);
#endif

std::string  convertJLongToString    (jlong value);
jstring      convertWStringToJString (JNIEnv* jniEnv, const std::wstring& str);
std::wstring convertJStringToWString (JNIEnv* jniEnv, jstring str);
jstring      convertJLongToJString   (JNIEnv* jniEnv, jlong value);
jstring      concatenateJStrings     (JNIEnv *jniEnv, jstring str1, jstring str2);

#endif
