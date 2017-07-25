package hytsnbr10519.get_all_installed_apps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class InstalledAppsAdapter extends ArrayAdapter {

    private LayoutInflater mInflater;

    public InstalledAppsAdapter(Context context, List<AppData> info_list) {
        super(context, android.R.layout.simple_list_item_1);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        addAll(info_list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = new ViewHolder();

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.main_adapter, null);
            vh = iniViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        AppData data = (AppData) getItem(position);
        setView(data, vh);

        return convertView;
    }

    private static class ViewHolder {
        TextView app_name_text;
        TextView app_package_text;
        ImageView app_icon_image;
    }

    private void setView(final AppData data, ViewHolder vh) {
        vh.app_name_text.setText(data.app_name);
        vh.app_package_text.setText(data.app_package);
        vh.app_icon_image.setImageDrawable(data.app_icon);
    }

    private ViewHolder iniViewHolder(View v) {

        ViewHolder vh = new ViewHolder();

        vh.app_name_text = (TextView) v.findViewById(R.id.appName);
        vh.app_package_text = (TextView) v.findViewById(R.id.appPackage);
        vh.app_icon_image = (ImageView) v.findViewById(R.id.appIcon);

        return vh;
    }
}