package ml.alohomora.mmdms;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ActionBarOverlayLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Section1Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class Section1Fragment extends Fragment implements AdapterView.OnItemClickListener {


    private OnFragmentInteractionListener mListener;

    public Section1Fragment() {
        // Required empty public constructor
    }
    EditText ageEditText,name,contactNumber;
    RadioGroup genderGroup;
    RadioButton genderButton;
    String nameVar,contactNumberVar,genderVar,dobVar;
    int day_dob,month_dob, year_dob,age;
    SQLiteDatabase myDB;
    SharedPreferences sharedPreferences=getActivity().getSharedPreferences("mmdmsPreferences",Context.MODE_PRIVATE);
    int eid=sharedPreferences.getInt("currentEid",0);
    //private Integer[] integer = {1,2,3,4,5};
    //Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view =  inflater.inflate(R.layout.fragment_section1, container, false);
       /* spinner=(Spinner)view.findViewById(R.id.age);
        ArrayAdapter<Integer> adapter_age=new ArrayAdapter<Integer>(getActivity(),android.R.layout.simple_spinner_item,integer);
        adapter_age.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_age);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getActivity(),spinner.getSelectedItem().toString(),Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/

        ageEditText=(EditText)view.findViewById(R.id.age);
        name=(EditText)view.findViewById(R.id.name);
        contactNumber=(EditText)view.findViewById(R.id.contactNumber);
        genderGroup = (RadioGroup)view.findViewById(R.id.gender);
        myDB = ((PrimaryTabbedActivity)getActivity()).sqLiteDatabaseInActivity;
        setForm();
        return view;
    }
    public void setForm(){
        // TODO: Enter pid and eid
        Cursor cursor=myDB.rawQuery("SELECT name,gender,age,contactNumber FROM PatInfo WHERE eid="+eid+";",null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            nameVar=cursor.getString(0);
            genderVar=cursor.getString(1);
            age=cursor.getInt(2);
            contactNumberVar=cursor.getString(3);
        }
        name.setText(nameVar, TextView.BufferType.EDITABLE);

        if(genderVar=="Male")
            (getActivity().findViewById(R.id.genderMale)).setEnabled(true);
        else
            (getActivity().findViewById(R.id.genderFemale)).setEnabled(true);
        ageEditText.setText(age);
        contactNumber.setText(contactNumberVar);
    }
    public void save(){

        nameVar=name.getText().toString();

        int selectedId = genderGroup.getCheckedRadioButtonId();
        genderButton = (RadioButton)getActivity().findViewById(selectedId);
        genderVar=genderButton.getText().toString();

        age=Integer.parseInt(ageEditText.getText().toString());

        contactNumberVar=contactNumber.getText().toString();

        // TODO: Enter pid and eid
        myDB.execSQL("UPDATE PatInfo SET name="+nameVar+",contactNumber=+"+contactNumberVar+",gender="+genderVar+",age="+age+
                "WHERE eid="+eid+";");
    }
/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
/*
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
*/
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        save();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

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