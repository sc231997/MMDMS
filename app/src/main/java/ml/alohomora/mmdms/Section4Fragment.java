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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Section4Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Section4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Section4Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button b1,b2;
    EditText et1,et2;
    TextView tv1,tv2;
    int eid;
    SharedPreferences sp;
    String currentEid = "mmdmsPreferences";
    SQLiteDatabase sqLiteDatabaseInActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    private OnFragmentInteractionListener mListener;

    public Section4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Section4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Section4Fragment newInstance(String param1, String param2) {
        Section4Fragment fragment = new Section4Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment


        b1 = (Button)getActivity().findViewById(R.id.Form4button1);
        et1 = (EditText) getActivity().findViewById(R.id.editForm4field1);
        et2 = (EditText) getActivity().findViewById(R.id.editForm4field2);

        tv1 = (TextView)getActivity().findViewById(R.id.form4dispHB);
        tv2 = (TextView)getActivity().findViewById(R.id.form4dispWBC);

        sp = getActivity().getSharedPreferences(currentEid,Context.MODE_PRIVATE);

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

                sqLiteDatabaseInActivity.execSQL("INSERT into PatInfo values("+ haemoglobin +","+ wbc+");");

               Toast.makeText(getContext(),"You entered "+haemoglobin+" and "+wbc+".",Toast.LENGTH_SHORT).show();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //SQLiteDatabase.openDatabase("PatInfo",null,getActivity().MODE_PRIVATE);


                eid = sp.getInt(currentEid,0);


                Cursor rs = sqLiteDatabaseInActivity.rawQuery("SELECT * from PatInfo where eid="+ eid + "",null);


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

        return inflater.inflate(R.layout.fragment_section4, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
