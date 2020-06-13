#include <jni.h>
#include <string>



extern "C" JNIEXPORT jintArray JNICALL
Java_com_example_mso_1laboratorium_1zdalne_natives_NativeLib_sort(
        JNIEnv* env,
        jclass clazz,
        jintArray j) {
    std::string hello = "Hello from C++";
    jsize len = env->GetArrayLength(j);
    jint* body = env->GetIntArrayElements(j, 0);
    std::sort(body, body + len);
    env->ReleaseIntArrayElements(j, body, NULL);
    return j;
}