package com.project.redrocketz.dummy;

/**
 * Created by Dell on 9/14/2017.
 */

public class VolumeNonDI {

    private Luas luas;
    private int tinggi;

    public VolumeNonDI(int p, int l, int t) {
        luas = new Luas(p, l);
        this.tinggi = t;
    }

    public int hitungVolume() {
        return luas.hitungLuas() * tinggi;
    }
}
