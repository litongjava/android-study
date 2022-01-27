#include "com_litongjava_androidndkdem02_MyNdk.h"

JNIEXPORT jstring JNICALL Java_com_litongjava_androidndkdem02_MyNdk_getString(JNIEnv * env, jobject obj){
  return (*env).NewStringUTF("This is mylibrary !!!");
}
JNIEXPORT jstring JNICALL Java_com_litongjava_androidndkdem02_MyNdk_getMyString(JNIEnv * env, jobject obj){
  return (*env).NewStringUTF("getMyString success !!!");
}