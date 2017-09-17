package deepmehtait.com.movieteasers.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by deepmetha on 9/16/17.
 */

public class AppStatus {

    private static AppStatus instance=new AppStatus();
    static Context context;
    ConnectivityManager connectivityManager;
    boolean connected=false;

    public static AppStatus getInstance(Context ctx){
        context = ctx.getApplicationContext();
        return instance;
    }

    public boolean isOnline(){
        try{
            connectivityManager =(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
            return connected;
        }
        catch(Exception e){
            Log.v("AppStatus Class", e.toString());

        }
        return connected;
    }

}
