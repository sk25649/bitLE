package com.austin.siwan.bitle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by Jojo on 7/19/14.
 */
public class MerchantDetailActivity extends Activity {

    private Button startTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merchant_detail);
//        getActionBar().setDisplayHomeAsUpEnabled(true);

        startTab = (Button)findViewById(R.id.startTab);
        startTab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MerchantDetailActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}
