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

public class ReportListAdapter extends BaseAdapter {
    List<ParseObject> itemList;
    Context context;
    private static LayoutInflater inflater = null;

    public ReportListAdapter(Context mainActivity, List<ParseObject> itemList) {
        // TODO Auto-generated constructor stub

        this.itemList = itemList;
        context = mainActivity;
        inflater = (LayoutInflater) context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return itemList.size();
    }

    @Override
    public ParseObject getItem(int position) {
        // TODO Auto-generated method stub
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder {
        TextView nameTextView, timeTextView, detailTextView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder = new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.dashboard_list_item, null);
        holder.nameTextView = (TextView) rowView.findViewById(R.id.nameTextView);
        holder.timeTextView = (TextView) rowView.findViewById(R.id.timeTextView);
        holder.detailTextView = (TextView) rowView.findViewById(R.id.detailTextView);
        final ParseObject object = getItem(position);

        holder.nameTextView.setText(object.getString("Name"));
        holder.timeTextView.setText(object.getString("time"));
        holder.detailTextView.setText(object.getString("Category"));

        return rowView;
    }

}