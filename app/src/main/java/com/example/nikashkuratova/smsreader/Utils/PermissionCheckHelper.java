package com.example.nikashkuratova.smsreader.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

public final class PermissionCheckHelper {
    private static final int MY_PERMISSIONS_REQUEST_READ_SMS = 0;
    private static String[] PERMISSIONS_SMS = {Manifest.permission.READ_SMS};


    public static boolean checkSmsPermissionGranted(Activity activity) {
        boolean permissionGranted = false;
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Contacts permissions have not been granted.
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_SMS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                //todo add explanation
                ActivityCompat.requestPermissions(activity, PERMISSIONS_SMS, MY_PERMISSIONS_REQUEST_READ_SMS);
            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(activity, PERMISSIONS_SMS, MY_PERMISSIONS_REQUEST_READ_SMS);
            }
        } else {
            // Contact permissions have been granted. Show the contacts fragment.
            Log.i("Info",
                    "Contact permissions have already been granted. Displaying contact details.");
            permissionGranted = true;
        }
        return permissionGranted;
    }


}
