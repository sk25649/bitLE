package com.austin.siwan.bitle.bitpay.model;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.austin.siwan.bitle.tasks.BitpayRatesAsyncTask;
import com.austin.siwan.bitle.R;

/**
 * Created by Kevin on 7/19/2014.
 */
public class TestActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        setUpButtons();
    }

    private void setUpButtons() {
        final Button ratesButton = (Button) findViewById(R.id.ratesButton);
        ratesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BitpayRatesAsyncTask((TextView) findViewById(R.id.ratesTextView)).execute();
            }
        });
    }
}
