package ml.alohomora.mmdms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

/**
 * Created by Ankush on 7/16/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    Context context;
    String[] name,lastVisit,contact;
    private static LayoutInflater inflater=null;

    public CustomListAdapter(MainActivity mainActivity, String[] name,String[] lastVisit,String[] contact)
    {
        context = mainActivity;
        this.name = name;
        this.lastVisit = lastVisit;
        this.contact = contact;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View listItem = inflater.inflate(R.layout.search_list_view_layout,null,true);
        TextView textViewName,textViewContact,textViewLastVisit;
        textViewName = (TextView) listItem.findViewById(R.id.listTextViewName);
        textViewContact = (TextView)listItem.findViewById(R.id.listTextViewContact);
        textViewLastVisit = (TextView)listItem.findViewById(R.id.listTextViewLastVisit);
        textViewName.setText(name[i]);
        textViewLastVisit.setText(lastVisit[i]);
        textViewContact.setText(contact[i]);
        return listItem;
    }
}
