package com.synergywebdesigners.nima;

import android.content.Context;
import android.net.http.RequestQueue;

import com.android.volley.Request;
import com.android.volley.toolbox.Volley;

/**
 * Created by Ashish Shahi on 13-04-2017.
 */

public class MySingleton {
  private static MySingleton mInstance;
  private static Context mctx;
  private com.android.volley.RequestQueue requestQueue;

  private MySingleton(Context context)
  {
    mctx = context;
    requestQueue = getRequestQueue();
  }

  private com.android.volley.RequestQueue getRequestQueue()
  {
    if (requestQueue==null)
    {
      requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());
    }
    return requestQueue;
  }
  public static synchronized MySingleton getmInstance(Context context)
  {
    if (mInstance == null)
    {
      mInstance = new MySingleton(context);
    }
    return mInstance;
  }
  public<T> void addToRequestque (Request<T> request)
  {
    getRequestQueue().add(request);
  }
}
