package com.project.redrocketz.dummy;

/**
 * Created by Dell on 9/15/2017.
 */

public class LuasTrapesium extends Luas {

    int p1;
    int p2;
    int l;

    public LuasTrapesium(int panjang1, int panjang2, int lebar) {
        super(panjang1, lebar);
        this.p1 = panjang1;
        this.p2 = panjang2;
        this.l = lebar;
    }

    @Override
    public int hitungLuas() {
        return (int) (0.5 * (p1 + p2) * l);
    }
}
