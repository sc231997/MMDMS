package ml.alohomora.mmdms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ankush on 7/16/2016.
 */
public class CustomListAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> name,contact;
    ArrayList<Integer> visitNo;
    private static LayoutInflater inflater=null;

    public CustomListAdapter(MainActivity mainActivity, ArrayList<String> name, ArrayList<Integer> lastVisit, ArrayList<String> contact)
    {
        context = mainActivity;
        this.name = name;
        this.visitNo = lastVisit;
        this.contact = contact;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return name.size();
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
        TextView textViewName,textViewContact,textViewVisitNo;
        textViewName = (TextView) listItem.findViewById(R.id.listTextViewName);
        textViewContact = (TextView)listItem.findViewById(R.id.listTextViewContact);
        textViewVisitNo = (TextView)listItem.findViewById(R.id.listTextViewVisitNo);
        textViewName.append(name.get(i));
        textViewVisitNo.append(Integer.toString(visitNo.get(i)));
        textViewContact.append(contact.get(i));
        return listItem;
    }
}
