package ml.alohomora.mmdms;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;



public class Section3Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    EditText et;
    SharedPreferences sharedPreferences;
    int seid;
    public Section3Fragment() {
        // Required empty public constructor
    }

    String bGroup,resp,gLevel,card;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_section3, container, false);
        sharedPreferences=getActivity().getSharedPreferences("mmdmsPreferences", Context.MODE_PRIVATE);
        seid=sharedPreferences.getInt("currentEid",0);
        spin(view);
        et = (EditText) view.findViewById(R.id.Form3glucoseLevel);
        previoustext();
        insert(view);
        SQLiteDatabase mydb = ((PrimaryTabbedActivity)getActivity()).sqLiteDatabaseInActivity;
        Cursor resultSet = mydb.rawQuery("Select bloodGroup from sqLiteDatabaseInActivity where eid="+seid,null);
        resultSet.moveToFirst();
        bGroup = resultSet.getString(6);

        resultSet = mydb.rawQuery("Select glucoseLevel from sqLiteDatabaseInActivity where eid="+seid,null);
        resultSet.moveToFirst();
        gLevel = resultSet.getString(7);

        resultSet = mydb.rawQuery("Select respiratoryProblem from sqLiteDatabaseInActivity where eid="+seid,null);
        resultSet.moveToFirst();
        resp = resultSet.getString(8);

        resultSet = mydb.rawQuery("Select cardiacProblem from sqLiteDatabaseInActivity where eid="+seid,null);
        resultSet.moveToFirst();
        card = resultSet.getString(9);







        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                previoustext();

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                highlight();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }


        public void spin(View view) {
        Spinner dropdown = (Spinner) view.findViewById(R.id.spinner);
        String[] bgroup = {"Select","A+", "A-", "B+", "B-", "O", "AB+", "AB-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>((getActivity()), android.R.layout.simple_spinner_item, bgroup);
        dropdown.setAdapter(adapter);


    }



        public void insert(View view)
        {
            String bloodGroup,cardiacProblem,repiratoryProblem;
            int glucoseLevel;
            Spinner sp = (Spinner) view.findViewById(R.id.spinner);
            bloodGroup= sp.getSelectedItem().toString();
            EditText et = (EditText) view.findViewById(R.id.Form3glucoseLevel);
            glucoseLevel = Integer.parseInt(et.getText().toString());
            RadioButton br = (RadioButton) view.findViewById(R.id.cardProblemNo);
            RadioButton rb = (RadioButton) view.findViewById(R.id.cardProblemYes);
            RadioButton rb1 = (RadioButton) view.findViewById(R.id.respProblemYes);
            RadioButton br1= (RadioButton) view.findViewById(R.id.respProblemNo);
            if(rb1.isChecked())
            {
                repiratoryProblem=rb1.getText().toString();
            }
            else
            {
                repiratoryProblem=br1.getText().toString();
            }


            if(rb.isChecked()) {
                cardiacProblem = rb.getText().toString();
            }
            else {
                cardiacProblem = br.getText().toString();
            }
            SQLiteDatabase mydb;
            mydb = ((PrimaryTabbedActivity)getActivity()).sqLiteDatabaseInActivity;
            mydb.execSQL("update PatInfo set bloodGroup="+bloodGroup+",glucoseLevel="+glucoseLevel+",cardiacProblem="+cardiacProblem+",respiratoryProblem="+repiratoryProblem+"where eid="+seid+";",null);

        }

        public void previoustext()
        {

            String[] bgroup = {"Select","A+", "A-", "B+", "B-", "O", "AB+", "AB-"};

            if(gLevel!=null)
            {
                 EditText et1 = (EditText) getActivity().findViewById(R.id.Form3glucoseLevel);
                et1.setText(gLevel);
            }

            if(resp!=null)
            {
                if(resp=="Yes")
                {
                    RadioButton rb = (RadioButton) getActivity().findViewById(R.id.respProblemYes);
                    rb.setEnabled(true);
                }
                else
                {
                    RadioButton rb = (RadioButton) getActivity().findViewById(R.id.respProblemNo);
                    rb.setEnabled(true);
                }

            }

            if(card!=null)
            {
                if(card=="Yes")
                {
                    RadioButton rb = (RadioButton) getActivity().findViewById(R.id.cardProblemYes);
                    rb.setEnabled(true);
                }

                else
                {
                    RadioButton rb = (RadioButton) getActivity().findViewById(R.id.cardProblemNo);
                    rb.setEnabled(true);
                }
            }

            if(bGroup!=null)
            {
                for(int i=0;i<8;i++)
                {
                    if(bGroup==bgroup[i])
                    {
                        Spinner spin = (Spinner) getActivity().findViewById(R.id.spinner);
                        spin.setSelection(i);
                    }
                }
            }


        }




        public void highlight()
         { String s;
        int llimit=100;
        int ulimit=500;
             et.setTextColor(Color.BLACK);
        if(et.getText().toString().length() > 1)
        {
            String e=et.getText().toString();
            if (e.length() > 1) {
                int value = Integer.parseInt(e);
                if(value>ulimit||value<llimit)
                {
                    et.setTextColor(Color.RED);
                }
                else
                {
                    et.setTextColor(Color.BLACK);
                }
            }
        }
         }





    @Override
    public void onDetach() {
        super.onDetach();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
