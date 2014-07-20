package com.austin.siwan.bitle;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.austin.siwan.bitle.tasks.BitpayAPIFragment;

/**
 * Created by Kevin on 7/19/2014.
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.bitpayTestFragment, new BitpayAPIFragment());
        fragmentTransaction.commit();

    }

}
