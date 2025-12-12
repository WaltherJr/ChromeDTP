#include <jni.h>
#include <stdio.h>
#include <vector>
#include <string>
#include <cstring>
#include <sstream>
#include <iostream>
#include <X11/Xlib.h>
#include <X11/Xutil.h>
#include <X11/Xatom.h>
#include "../../header/com_eriksandsten_chromedpt_utils_WHelper.h"
#include "../WStringWrapper.h"
#include "../JStringWrapper.h"
#include "../ArrayList.h"

using namespace std;

bool matchWMClass(Display* display, Window window, const char* targetClass) {
    XClassHint classHint;

    if (XGetClassHint(display, window, &classHint)) {
        bool match = (classHint.res_class && strcmp(classHint.res_class, targetClass) == 0);
        if (classHint.res_name) XFree(classHint.res_name);
        if (classHint.res_class) XFree(classHint.res_class);
        return match;
    }

    return false;
}

void findWindows(Display* display, Window root, const std::string& windowTargetClass, std::vector<Window>& windows) {
    Window root_return, parent_return;
    Window* children;
    unsigned int nchildren;

    if (!XQueryTree(display, root, &root_return, &parent_return, &children, &nchildren)) {
        return;
    }

    for (unsigned int i = 0; i < nchildren; ++i) {
        Window w = children[i];
        XClassHint classHint;

        if (XGetClassHint(display, w, &classHint)) {
            if (classHint.res_class && windowTargetClass == classHint.res_class) {
                windows.push_back(w);
            }

            if (classHint.res_name) {
                XFree(classHint.res_name);
            }
            if (classHint.res_class) {
                XFree(classHint.res_class);
            }
        }

        // Recurse into child windows
        findWindows(display, w, windowTargetClass, windows);
    }

    if (children) {
        XFree(children);
    }
}

bool setFocusEWMH(Display* display, Window window) {
    Window rootWindow = DefaultRootWindow(display);
    Atom _NET_ACTIVE_WINDOW = XInternAtom(display, "_NET_ACTIVE_WINDOW", False);

    XEvent event;
    memset(&event, 0, sizeof(event));
    event.xclient.type = ClientMessage;
    event.xclient.window = window;
    event.xclient.message_type = _NET_ACTIVE_WINDOW;
    event.xclient.format = 32;
    event.xclient.data.l[0] = 1; // 1 = request to activate
    event.xclient.data.l[1] = CurrentTime;

    int sent = XSendEvent(display, rootWindow, False,
                          SubstructureRedirectMask | SubstructureNotifyMask,
                          &event);
    XFlush(display);
    if (sent) {
        printf("Focus request sent using _NET_ACTIVE_WINDOW.\n");
        return true;
    } else {
        printf("Failed to send _NET_ACTIVE_WINDOW request.\n");
        return false;
    }
}

std::vector<Window> getAllWindowsWithClassName(Display *display, const char* windowClassName) {
    std::vector<Window> windows;
    Window rootWindow = DefaultRootWindow(display);
    findWindows(display, rootWindow, windowClassName, windows);

    return windows;
}

Display* getDisplay() {
    Display* display = XOpenDisplay(nullptr);

    if (!display) {
        std::cerr << "Cannot open X display" << std::endl;
        return nullptr;
    } else {
        return display;
    }
}

Window getWindowByClassName(const char* windowClassName) {
    Display* display = getDisplay();

    if (display != nullptr) {
        std::vector<Window> windows = getAllWindowsWithClassName(display, windowClassName);
        std::cout << "Found " << windows.size() << " window(s) matching WM_CLASS \"" << windowClassName << "\":" << std::endl;

        for (Window w : windows) {
            std::cout << "Window ID: " << w << std::endl;
        }

        XCloseDisplay(display);
        return windows[0];

    } else {
        return (Window)-1;
    }
}

// https://stackoverflow.com/questions/19136365/win32-setforegroundwindow-not-working-all-the-time
JNIEXPORT bool JNICALL Java_org_eriksandsten_utils_WHelper_setActiveWindow(JNIEnv* jniEnv, jobject obj, jlong windowHandle) {
    Display* display = getDisplay();

    if (display != nullptr) {
        setFocusEWMH(display, (Window) windowHandle);
        return true;
    } else {
        return false;
    }
}

JNIEXPORT bool JNICALL Java_org_eriksandsten_utils_WHelper_setActiveWindow(JNIEnv* jniEnv, jobject obj, jstring windowClassName) {
    Display* display = getDisplay();

    if (display != nullptr) {
        const char* cstr = jniEnv->GetStringUTFChars(windowClassName, nullptr);

        if (cstr != nullptr) {
            printf("Window class name: %s\n", cstr);
            Window window = getWindowByClassName(cstr);
            setFocusEWMH(display, (Window) window);
            jniEnv->ReleaseStringUTFChars(windowClassName, cstr);
            return true;
        }
    }

    return false;
}

JNIEXPORT jlong JNICALL Java_org_eriksandsten_utils_WHelper_getWindowByClassName(JNIEnv* jniEnv, jobject obj, jstring windowClassName) {
    const char* cstr = jniEnv->GetStringUTFChars(windowClassName, nullptr);
    Window window = -1;

    if (cstr != nullptr) {
        printf("Window class name: %s\n", cstr);
        window = getWindowByClassName(cstr);
        jniEnv->ReleaseStringUTFChars(windowClassName, cstr);
    }

    return (jlong) window;
}
