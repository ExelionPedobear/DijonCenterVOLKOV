package com.example.ruslanmanca.dijoncentervolkov.models;

/**
 * Created by RuslanManca on 20/09/2017.
 */

public enum EnumStatut {
    AVENIR(0),
    ENCOURS(1),
    TERMINE(2),
    ANNULE(3);

    private final int code;

    EnumStatut(int code) {
        this.code = code;
    }

    public int getTypeLieu() {
        return this.code;
    }

    public String getStringTypeLieu() {
        return String.valueOf(this.code);
    }

    public static EnumStatut getEnumFromCode(int index) {
        for (EnumStatut l : EnumStatut.values()) {
            if (l.code == index) return l;
        }
        throw new IllegalArgumentException("Enum non trouvé.");
    }
}
