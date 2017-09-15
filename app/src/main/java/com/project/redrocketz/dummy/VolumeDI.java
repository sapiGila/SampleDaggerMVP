package com.project.redrocketz.dummy;

/**
 * Created by Dell on 9/14/2017.
 */

public class VolumeDI {

    private Luas luas;
    private int tinggi;

    public VolumeDI(Luas luas,
                    int tinggi) {
        this.luas = luas;
        this.tinggi = tinggi;
    }

    public int hitungVolume() {
        return luas.hitungLuas() * tinggi;
    }
}
