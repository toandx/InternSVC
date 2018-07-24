package com.example.toandx.fra2fra;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {


    View retView;
    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        retView= inflater.inflate(R.layout.fragment_fragment2, container, false);
        // final FragmentManager fragmentManager = getFragmentManager();
        if (getArguments()!=null)
        {
            TextView tv=(TextView) retView.findViewById(R.id.textView2);
            tv.setText(getArguments().getString("text"));
        }

        /*Button gotoFragmentThreeBtn = (Button)retView.findViewById(R.id.button2);
        gotoFragmentThreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentOne = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment One");

                // Because fragment two has been popup from the back stack, so it must be null.
                if(fragmentOne==null)
                {
                    fragmentOne = new Fragment1();
                    Log.d("ERROR","ERROR");
                }

                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                // Replace fragment one with fragment two, the second fragment tag name is "Fragment Two".
                // This action will remove Fragment one and add Fragment two.
                fragmentTransaction.replace(R.id.frame1, fragmentOne, "Fragment One");

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
        TextView tv=(TextView) retView.findViewById(R.id.textView2);
        tv.setText(str);
    }
    public String getText()
    {
        EditText tv=(EditText) retView.findViewById(R.id.editText2);
        return(tv.getText().toString());
    }

}
