package com.austin.siwan.bitle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Jojo on 7/19/14.
 */
public class MerchantDetailActivity extends Activity {

    private Button startTab;
    private TextView businessName, categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merchant_detail);

        Bundle bundle = getIntent().getExtras();
        String storeName = bundle.getString("name");
        String category = bundle.getString("category");

        // Get a handle to the Map Fragment
        GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        LatLng nestGSV = new LatLng(37.484507, -122.200929);
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(nestGSV, 16));

        map.addMarker(new MarkerOptions()
                .title(storeName)
                .snippet("Red Buill and Vodka.")
                .position(nestGSV));

        businessName = (TextView)findViewById(R.id.businessName);
        categories = (TextView)findViewById(R.id.categories);

        businessName.setText(storeName);
        categories.setText(String.format("Category: %s", category));

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
