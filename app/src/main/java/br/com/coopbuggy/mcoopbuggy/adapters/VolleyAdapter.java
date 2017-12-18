package br.com.coopbuggy.mcoopbuggy.adapters;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by andre on 17/12/2017.
 */

public class VolleyAdapter extends StringRequest {
    private Map<String, String> params;
    private String user;
    private String pass;

    public VolleyAdapter(int method, String url, Map<String, String> params, Listener<String> listener, ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        this.params = params;
    }

    public VolleyAdapter(int method, String url, Listener<String> listener, ErrorListener errorListener) {
        super(method, url, listener, errorListener);
    }

    public VolleyAdapter(String url, Listener<String> listener, ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public Map<String, String> getParams() throws AuthFailureError{
        return params;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
    }
}
