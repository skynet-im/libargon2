#include <jni.h>
#include <string>
#include "argon2.h"

void throwException(JNIEnv *env, const char *clazz, const char *message) {
    jclass exClass = env->FindClass(clazz);
    env->ThrowNew(exClass, message);
}

extern "C"
JNIEXPORT jbyteArray JNICALL
Java_de_vectordata_libargon2_Argon2_hash0(JNIEnv *env, jobject instance, jint t_cost, jint m_cost,
                                          jint parallelism, jbyteArray pwd_, jbyteArray salt_,
                                          jint type, jint version, jint hashLen) {

    if(hashLen <= 0)
    {
        throwException(env, "java/lang/IllegalArgumentException", "Hash length must not be less than or equal to 0");
        return NULL;
    }

    jbyte *pwd = env->GetByteArrayElements(pwd_, NULL);
    jbyte *salt = env->GetByteArrayElements(salt_, NULL);
    int pwdLen = env->GetArrayLength(pwd_);
    int saltLen = env->GetArrayLength(salt_);

    jbyteArray arr = env->NewByteArray(hashLen);

    void *hash = malloc(sizeof(jbyte) * hashLen);

    argon2_hash(static_cast<const uint32_t>(t_cost), static_cast<const uint32_t>(m_cost),
                static_cast<const uint32_t>(parallelism), pwd, static_cast<const size_t>(pwdLen),
                salt,
                static_cast<const size_t>(saltLen), hash, static_cast<const size_t>(hashLen), NULL,
                0,
                static_cast<argon2_type>(type), static_cast<const uint32_t>(version));

    env->SetByteArrayRegion(arr, 0, hashLen, (jbyte *) hash);

    env->ReleaseByteArrayElements(pwd_, pwd, 0);
    env->ReleaseByteArrayElements(salt_, salt, 0);

    env->ReleaseByteArrayElements(pwd_, pwd, 0);
    env->ReleaseByteArrayElements(salt_, salt, 0);

    return arr;
}