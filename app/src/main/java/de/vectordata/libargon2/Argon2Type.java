package de.vectordata.libargon2;

public enum Argon2Type {
    ARGON2D(0),
    ARGON2I(1),
    ARGON2ID(2);

    int value;

    Argon2Type(int value) {
        this.value = value;
    }
}
