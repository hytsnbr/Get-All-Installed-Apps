package hytsnbr10519.get_all_installed_apps;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private PackageManager pm;
    private List<ApplicationInfo> installedApps;
    private List<AppData> info_list = new ArrayList<AppData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pm = getPackageManager();
        int flag = PackageManager.MATCH_UNINSTALLED_PACKAGES;
        installedApps = pm.getInstalledApplications(flag);

        getInstalledAppInfo();

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View mView = inflater.inflate(R.layout.custom_dialog, (ViewGroup) findViewById(R.id.dialog_layout_root));

        list = (ListView) findViewById(R.id.list);
        list.setAdapter(new InstalledAppsAdapter(this, info_list));
        list.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlertDialog.Builder dialog = new AlertDialog.Builder(getApplicationContext());
                        dialog.setView(mView);

                        dialog.create().show();
                    }
                }
        );
    }

    private void getInstalledAppInfo() {
        runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        for (ApplicationInfo app : installedApps) {
                            AppData data = new AppData();
                            data.app_name = app.loadLabel(pm).toString();
                            data.app_package = app.packageName;
                            data.app_icon = app.loadIcon(pm);
                            info_list.add(data);
                        }
                    }
                }
        );
    }
}