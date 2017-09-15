package com.project.redrocketz.dummy;

/**
 * Created by Dell on 9/14/2017.
 */

public class Luas {

    private int panjang;
    private int lebar;

    public Luas(int panjang,
                int lebar) {
        this.panjang = panjang;
        this.lebar = lebar;
    }

    public int hitungLuas() {
        return panjang * lebar;
    }
}
