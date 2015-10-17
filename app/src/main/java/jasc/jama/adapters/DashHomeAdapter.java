package jasc.jama.adapters;

import android.content.Context;
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
        TextView nameTextView, timeTextView, categoryTextView;

        public ViewHolder(View view) {
            nameTextView = (TextView) view.findViewById(R.id.nameTextView);
            timeTextView = (TextView) view.findViewById(R.id.timeTextView);
            categoryTextView = (TextView) view.findViewById(R.id.categoryTextView);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.dashboard_list_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        ParseObject object = getItem(position);

        viewHolder.nameTextView.setText(object.getString("Name"));
        viewHolder.timeTextView.setText(object.getString("Time"));
        viewHolder.categoryTextView.setText(object.getString("Category"));
        return convertView;
    }

}