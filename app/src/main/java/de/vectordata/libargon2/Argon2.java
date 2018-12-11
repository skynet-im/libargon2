package de.vectordata.libargon2;

public class Argon2 {

    static {
        System.loadLibrary("libargon2");
    }

    private static native byte[] hash0(int t_cost, int m_cost, int parallelism, byte[] pwd, byte[] salt, int type, int version, int hashLen);

    public static byte[] hash(int t, int m, int parallelism, byte[] password, byte[] salt, Argon2Type type, Argon2Version version, int hashLength) {
        return hash0(t, m, parallelism, password, salt, type.value, version.value, hashLength);
    }

}
