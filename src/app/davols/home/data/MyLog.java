package app.davols.home.data;

import android.util.Log;

public class MyLog {
	private static final boolean DEBUG=true;
	public static void d(String TAG,String MSG) {
		if(DEBUG) {
			Log.d(TAG,MSG);
		}
	}
	public static void v(String TAG,String MSG) {
		if(DEBUG) {
			Log.v(TAG,MSG);
		}
	}
	public static void e(String TAG,String MSG) {
		if(DEBUG) {
			Log.e(TAG,MSG);
		}
	}
	public static void i(String TAG,String MSG) {
		if(DEBUG) {
			Log.i(TAG,MSG);
		}
	}
	public static void w(String TAG,String MSG) {
		if(DEBUG) {
			Log.w(TAG,MSG);
		}
	}
	public static void e(String TAG, String MSG, Exception e) {
		if(DEBUG) {
			Log.e(TAG,MSG,e);
		}
	}
	public static void e(String TAG, String MSG, Error e) {
		if(DEBUG) {
			Log.e(TAG,MSG,e);
		}
	}
	public static void w(String TAG, String MSG, Exception e) {
		if(DEBUG) {
			Log.w(TAG,MSG,e);
		}
	}
}
