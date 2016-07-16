package ml.alohomora.mmdms;

import android.content.Context;
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
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Section3Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Sectiofactory method to
 * create an instance of this fragment.
 */
public class Section3Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText et;
    private OnFragmentInteractionListener mListener;

    public Section3Fragment() {
        // Required empty public constructor
    }











    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_section3, container, false);
        spin(view);
        et = (EditText) view.findViewById(R.id.BGhighlight);
        et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

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
        String[] bgroup = {"A+", "A-", "B+", "B-", "O", "AB+", "AB-"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>((getActivity()), android.R.layout.simple_spinner_item, bgroup);
        dropdown.setAdapter(adapter);

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
        mListener = null;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
