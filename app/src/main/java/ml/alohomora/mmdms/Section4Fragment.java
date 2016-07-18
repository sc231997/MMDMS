package ml.alohomora.mmdms;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class Section4Fragment extends Fragment {


    Button b1,b2;
    EditText et1,et2;
    TextView tv1,tv2;
    int eid;
    SharedPreferences sp;
    String currentEid = "mmdmsPreferences";
    SQLiteDatabase sqLiteDatabase;







    public Section4Fragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_section4, container, false);

        b1 = (Button)view.findViewById(R.id.Form4button1);
        et1 = (EditText) view.findViewById(R.id.editForm4field1);
        et2 = (EditText) view.findViewById(R.id.editForm4field2);

        tv1 = (TextView)view.findViewById(R.id.form4dispHB);
        tv2 = (TextView)view.findViewById(R.id.form4dispWBC);

        sp = getActivity().getSharedPreferences(currentEid,Context.MODE_PRIVATE);
        sqLiteDatabase = ((PrimaryTabbedActivity)getActivity()).sqLiteDatabaseInActivity;
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hb = et1.getText().toString().replaceAll(" ","");
                float haemoglobin = Float.parseFloat(hb);
                String wbc1 = et2.getText().toString().replaceAll(" ","");
                Long wbc = Long.parseLong(wbc1);

              // final SQLiteDatabase sqLiteDatabaseInActivity=null;
                // = SQLiteDatabase.openDatabase("PatInfo",null,getActivity().MODE_PRIVATE);
               // sqLiteDatabaseInActivity.execSQL("insert into PatInfo values(?,?)");

                sqLiteDatabase.execSQL("INSERT into PatInfo values("+ haemoglobin +","+ wbc+");");

               Toast.makeText(getContext(),"You entered "+haemoglobin+" and "+wbc+".",Toast.LENGTH_SHORT).show();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //SQLiteDatabase.openDatabase("PatInfo",null,getActivity().MODE_PRIVATE);


                eid = sp.getInt(currentEid,0);


                Cursor rs = sqLiteDatabase.rawQuery("SELECT * from PatInfo where eid="+ eid + "",null);


                rs.moveToFirst();
                String hb,wbc;
                do
                {
                    hb = rs.getString(13);
                    wbc=rs.getString(14);

                    tv1.setText(hb);
                    tv2.setText(wbc);

                }while(rs.moveToNext());


            }
        });

        return view;
    }






    @Override
    public void onDetach() {
        super.onDetach();
    }

}
