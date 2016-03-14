package me.wavever.ganklock.retrofit;

/**
 * Created by WAVE on 2016/3/11.
 */
public class WaveverFactory {

    static GankService sService = null;

    public static GankService getSingle() {
        synchronized (GankService.class) {
            if (sService == null) {
                sService = new WaveverRetrofit().getService();
            }
            return sService;
        }
    }
}
