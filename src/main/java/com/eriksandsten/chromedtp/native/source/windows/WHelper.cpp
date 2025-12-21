#include <jni.h>
#include <stdio.h>
#include <string>
#include <sstream>
#include <windows.h>
#include "../../header/com_eriksandsten_chromedtp_utils_WHelper.h"
#include "../WStringWrapper.h"
#include "../JStringWrapper.h"
#include "../ArrayList.h"

#define SW_SHOW 5

using namespace std;

HWND finalWindowHandle = NULL;
jint globalProcessId;
JStringWrapper globalClassName;

void setActiveWindow(HWND hWnd) {
    HWND foregroundWindow = GetForegroundWindow();
    DWORD windowThreadProcessId = GetWindowThreadProcessId(foregroundWindow, 0);
    DWORD currentThreadId = GetCurrentThreadId();
    AttachThreadInput(windowThreadProcessId, currentThreadId, TRUE);
    BringWindowToTop(hWnd);
    ShowWindow(hWnd, SW_SHOW);
    AttachThreadInput(windowThreadProcessId, currentThreadId, FALSE);
}

BOOL CALLBACK EnumWindowsProc_getWindowHandlesByWindowClassName(HWND hWnd, LPARAM lParam) {
    const wstring currentWindowClassName = getWin32WindowClassName(hWnd);
    auto dataPointer = reinterpret_cast<VectorWithValue<HWND, wstring>*>(lParam);

    if (dataPointer->additionalValue == currentWindowClassName) {
        dataPointer->array->push_back(hWnd);
    }

    return TRUE;
}

HWND getWindowByClassName(JNIEnv *jniEnv, wstring className) {
    vector<HWND> windowHandles;
    VectorWithValue<HWND, wstring> data = {&windowHandles, className};
    EnumWindows(EnumWindowsProc_getWindowHandlesByWindowClassName, (LPARAM) &data);

    return windowHandles[0];
}

BOOL CALLBACK EnumWindowsProc(HWND hWnd, LPARAM lParam) {
    DWORD pId;
    GetWindowThreadProcessId(hWnd, &pId);

    if (pId == (DWORD)lParam) {
        finalWindowHandle = hWnd;
        return FALSE;
    }

    return TRUE;
}

// https://stackoverflow.com/questions/19136365/win32-setforegroundwindow-not-working-all-the-time
JNIEXPORT void JNICALL Java_org_eriksandsten_utils_WHelper_setActiveWindow(JNIEnv* jniEnv, jobject obj, jlong windowHandle) {
    HWND hWnd = reinterpret_cast<HWND>(windowHandle);

    HWND foregroundWindow = GetForegroundWindow();
    DWORD windowThreadProcessId = GetWindowThreadProcessId(foregroundWindow, 0);
    DWORD currentThreadId = GetCurrentThreadId();
    AttachThreadInput(windowThreadProcessId, currentThreadId, TRUE);
    BringWindowToTop(hWnd);
    ShowWindow(hWnd, SW_SHOW);
    AttachThreadInput(windowThreadProcessId, currentThreadId, FALSE);
}

JNIEXPORT bool JNICALL Java_org_eriksandsten_utils_WHelper_setActiveWindow(JNIEnv* jniEnv, jobject obj, jstring windowClassName) {
    const char* cstr = jniEnv->GetStringUTFChars(windowClassName, nullptr);

    if (cstr != nullptr) {
        HWND window = getWindowByClassName(jniEnv, convertJStringToWString(jniEnv, windowClassName));
        setActiveWindow(window);
        jniEnv->ReleaseStringUTFChars(windowClassName, cstr);
        return true;
    } else {
        return false;
    }
}

JNIEXPORT jlong JNICALL Java_org_eriksandsten_utils_WHelper_getWindowByClassName(JNIEnv* jniEnv, jobject obj, jstring windowClassName) {
    const char* cstr = jniEnv->GetStringUTFChars(windowClassName, nullptr);

    if (cstr != nullptr) {
        HWND window = getWindowByClassName(jniEnv, convertJStringToWString(jniEnv, windowClassName));
        return (jlong) window;
    } else {
        return -1;
    }
}
