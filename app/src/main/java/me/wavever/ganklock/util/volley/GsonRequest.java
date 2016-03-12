package me.wavever.ganklock.util.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by WAVE on 2016/2/22.
 */
public class GsonRequest<T> extends Request<T> {

    private final Response.Listener<T> mListener;
    private Gson mGson;
    private Class<T> mClass;

    public GsonRequest(String url, Class<T> clazz, Listener listener, ErrorListener errorListener) {
        this(Method.GET, url, clazz, listener, errorListener);
    }

    public GsonRequest(int method, String url, Class<T> clazz, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        mListener = listener;
        mGson = new Gson();
        mClass = clazz;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(mGson.fromJson(jsonString, mClass),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }
}
