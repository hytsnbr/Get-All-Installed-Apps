package hytsnbr10519.get_all_installed_apps;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class getVersion {

    public static String getVersionName(Context context, ApplicationInfo info) {
        String name = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(info.packageName, 0);
            name = pi.versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static int getVersionCode(Context context, ApplicationInfo info) {
        int code = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(info.packageName, 0);
            code = pi.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }
}
