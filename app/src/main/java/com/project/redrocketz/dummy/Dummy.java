package com.project.redrocketz.dummy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dell on 9/14/2017.
 */

public class Dummy extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //contoh DI
        Luas luas = new Luas(4, 5);
        VolumeDI volumeDI = new VolumeDI(luas, 3);
        int hitungVolumeDI = volumeDI.hitungVolume();

        LuasTrapesium luasTrapesium = new LuasTrapesium(4, 10, 5);
        VolumeDI volumeDI1 = new VolumeDI(luasTrapesium, 3);
        int hitungVolumeDI1 = volumeDI1.hitungVolume();

        //contoh non DI
        VolumeNonDI volumeNonDI = new VolumeNonDI(4, 5, 3);
        int hitungVolumeNonDI = volumeNonDI.hitungVolume();
    }
}
