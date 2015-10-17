package jasc.jama.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

import jasc.jama.R;

public class DashHomeAdapter extends BaseAdapter {
    List<ParseObject> itemList;
    Context context;
    private static LayoutInflater inflater = null;

    public DashHomeAdapter(Context mainActivity, List<ParseObject> itemList) {
        this.itemList = itemList;
        context = mainActivity;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public ParseObject getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        TextView nameTextView, frequencyTextView, timeTextView, dosageTextView;

        public ViewHolder(View view) {
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            timeTextView = (TextView) view.findViewById(R.id.timeTextView);
            dosageTextView = (TextView) view.findViewById(R.id.dosageTextView);
            frequencyTextView = (TextView) view.findViewById(R.id.frequencyTextView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ParseObject object = getItem(position);
        String type = object.getString("type");
        Log.d("TYPE", type);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.dashboard_list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        if (type.equalsIgnoreCase("medicine")) {
            viewHolder.nameTextView.setText(object.getString("Name"));
            viewHolder.timeTextView.setText(object.getString("Time"));
            viewHolder.frequencyTextView.setText(object.getString("freq"));
            viewHolder.dosageTextView.setText(object.getString("dosage"));
        } else if (type.equalsIgnoreCase("appointment")) {
            viewHolder.nameTextView.setText(object.getString("reason"));
            viewHolder.timeTextView.setText(object.getString("Time"));
            viewHolder.frequencyTextView.setText(object.getString("docs"));
            viewHolder.dosageTextView.setText(object.getString("Name"));
        }
        return convertView;
    }

}