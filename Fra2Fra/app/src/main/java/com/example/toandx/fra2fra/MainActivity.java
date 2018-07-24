package com.example.toandx.fra2fra;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    Fragment1 fragment1;
    Fragment2 fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get FragmentManager and FragmentTransaction object.
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Create FragmentOne instance.
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();


        // Add fragment one with tag name.
        fragmentTransaction.replace(R.id.frame1, fragment1, "Fragment One");
        fragmentTransaction.commit();



        FragmentUtil.printActivityFragmentList(fragmentManager);
    }
    public void onClick1(View view)
    {
        /*Fragment fragmentTwo = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment Two");

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

        FragmentUtil.printActivityFragmentList(fragmentManager);*/
        String str=fragment1.getText();
        Bundle data=new Bundle();
        data.putString("text","Nhan: "+str);
        fragment2.setArguments(data);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame1,fragment2,"Fragment Two");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        FragmentUtil.printActivityFragmentList(fragmentManager);
    }
    public void onClick2(View view)
    {
        /*Fragment fragmentOne = FragmentUtil.getFragmentByTagName(fragmentManager, "Fragment One");

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

        FragmentUtil.printActivityFragmentList(fragmentManager);*/
        String str=fragment2.getText();
        Bundle data=new Bundle();
        data.putString("text","Nhan: "+str);
        fragment1.setArguments(data);
        fragmentManager.popBackStack();
        /*FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame1,fragment1,"Fragment One");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
        //fragment1.setText(str);
        FragmentUtil.printActivityFragmentList(fragmentManager);

    }
}
