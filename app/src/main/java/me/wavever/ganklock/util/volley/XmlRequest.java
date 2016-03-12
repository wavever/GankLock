package me.wavever.ganklock.util.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by WAVE on 2016/2/22.
 */
public class XmlRequest extends Request<XmlPullParser> {

    private final Response.Listener<XmlPullParser> mListener;

    public XmlRequest(String url, Response.ErrorListener listener, Response.Listener<XmlPullParser> mListener) {
        this(Method.GET, url, listener, mListener);
    }

    public XmlRequest(int method, String url, Response.ErrorListener listener, Response.Listener<XmlPullParser> mListener) {
        super(method, url, listener);
        this.mListener = mListener;
    }

    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse networkResponse) {
        String xmlString;
        try {
            xmlString = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(xmlString));
            return Response.success(parser, HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (XmlPullParserException e) {
            return Response.error(new ParseError(e));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(XmlPullParser xmlPullParser) {
        mListener.onResponse(xmlPullParser);
    }
}
