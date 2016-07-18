package ml.alohomora.mmdms;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class Section13Fragment extends Fragment {


    Button calcBalance;
    EditText et1,et2;
    TextView tv1;





    public Section13Fragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_section13, container, false);
        calcBalance = (Button)view.findViewById(R.id.form13balanceButton);
        et1 = (EditText)view.findViewById(R.id.form13Amount);
        et2 = (EditText)view.findViewById(R.id.form13paidAmount);

        tv1 = (TextView)view.findViewById(R.id.form13viewBalance);


        String amts,paidAmts;
        int amt,paidAmt,balance;

        amts = et1.getText().toString();
        amt = Integer.parseInt(amts);
        paidAmts = et2.getText().toString();
        paidAmt = Integer.parseInt(paidAmts);

        balance = amt - paidAmt;

        tv1.setText(balance);
        return view;
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
