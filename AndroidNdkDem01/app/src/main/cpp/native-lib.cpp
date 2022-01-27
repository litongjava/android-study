#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring

JNICALL
Java_com_litongjava_androidndkdem01_MainActivity_stringFromJNI(
  JNIEnv *env,
  jobject /* this */) {
  std::string hello = "Hello from Cpp";
  return env->NewStringUTF(hello.c_str());
}
