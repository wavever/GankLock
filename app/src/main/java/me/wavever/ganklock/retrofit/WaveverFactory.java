package me.wavever.ganklock.retrofit;

/**
 * Created by WAVE on 2016/3/11.
 */
public class WaveverFactory {

    static GankService sService = null;
    protected static final Object monitor = new Object();


    public static GankService getSingle() {
        synchronized (monitor) {
            if (sService == null) {
                sService = new WaveverRetrofit().getService();
            }
            return sService;
        }
    }
}
