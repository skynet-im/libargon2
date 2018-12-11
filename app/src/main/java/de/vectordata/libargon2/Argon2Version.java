package de.vectordata.libargon2;

public enum Argon2Version {
    VERSION_10(0x10),
    VERSION_13(0x13);

    int value;

    Argon2Version(int value) {
        this.value = value;
    }
}
