package de.vectordata.libargon2;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.UnsupportedEncodingException;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class Argon2Test {

    private static final String TAG = "Argon2Test";

    @Test
    public void testArgon() throws UnsupportedEncodingException {

        byte[] password = "testpassword".getBytes("UTF-8");
        byte[] salt = "test@test.com".getBytes("UTF-8");
        long before = System.currentTimeMillis();
        byte[] hash = Argon2.hash(2, 102400, 8, password, salt, Argon2Type.ARGON2ID, Argon2Version.VERSION_13, 64);

        Log.i(TAG, bytesToHex(hash));

        Log.i(TAG, "Hash took " + (System.currentTimeMillis() - before) + "ms");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHashCrash() throws UnsupportedEncodingException {
        byte[] password = "testpassword".getBytes("UTF-8");
        byte[] salt = "test@test.com".getBytes("UTF-8");
        Argon2.hash(2, 102400, 8, password, salt, Argon2Type.ARGON2ID, Argon2Version.VERSION_13, -5);
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
