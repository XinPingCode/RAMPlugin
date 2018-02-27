package RAMPlugin;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.net.Uri;
import android.content.Context;
import android.app.Activity;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.Manifest;
import java.io.File;
import java.net.URI;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.lang.Exception;
import java.lang.Math;
import java.lang.Float;
/**
 * This class echoes a string called from JavaScript.
 */
public class RAMPlugin extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        }else if(action.equals("getRAM")) {
            String message = args.getString(0);
            this.getRAM(message, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
    private void getRAM(String message, CallbackContext callbackContext) {
     String path = "/proc/meminfo";
     String firstLine = null;
     int totalRam = 0 ;
     try{
        FileReader fileReader = new FileReader(path);
        BufferedReader br = new BufferedReader(fileReader,8192);
        firstLine = br.readLine().split("\\s+")[1];
        br.close();
    }catch (Exception e){
        e.printStackTrace();
    }
    if(firstLine != null){
        totalRam = (int)Math.ceil((new Float(Float.valueOf(firstLine) / (1024 * 1024)).doubleValue()));
    }
    callbackContext.success(totalRam);
        //return totalRam + "GB";//返回1GB/2GB/3GB/4GB
}
}
