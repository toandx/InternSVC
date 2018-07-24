package com.example.toandx.fra2fra;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    View retView;
    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        retView=inflater.inflate(R.layout.fragment_fragment1, container, false);
        final FragmentManager fragmentManager = getFragmentManager();
        if (getArguments()!=null)
        {
            TextView tv=(TextView) retView.findViewById(R.id.textView);
            tv.setText(getArguments().getString("text"));
        }

        /*Button gotoFragmentTwoBtn = (Button)retView.findViewById(R.id.button);
        gotoFragmentTwoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragmentTwo = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment Two");

                // Because fragment two has been popup from the back stack, so it must be null.
                if(fragmentTwo==null)
                {
                    fragmentTwo = new Fragment2();
                }

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Replace fragment one with fragment two, the second fragment tag name is "Fragment Two".
                // This action will remove Fragment one and add Fragment two.
                fragmentTransaction.replace(R.id.frame1, fragmentTwo, "Fragment Two");

                // Add fragment one in back stack.So it will not be destroyed. Press back menu can pop it up from the stack.
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.commit();

                FragmentUtil.printActivityFragmentList(fragmentManager);
            }
        });*/

        return retView;

    }
    public void setText(String str)
    {
        TextView tv=(TextView) retView.findViewById(R.id.textView);
        tv.setText(str);
    }
    public String getText()
    {
        EditText tv=(EditText) retView.findViewById(R.id.editText);
        return(tv.getText().toString());
    }

}
