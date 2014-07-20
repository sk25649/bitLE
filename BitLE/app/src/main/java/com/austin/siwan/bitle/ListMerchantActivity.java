package com.austin.siwan.bitle;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.austin.siwan.bitle.Adapters.LeDeviceAdapter;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;
import it.gmariotti.cardslib.library.view.CardView;

/**
 * Created by Jojo on 7/19/14.
 */
public class ListMerchantActivity extends Activity {

    private static final String TAG = ListMerchantActivity.class.getSimpleName();

    private static final int REQUEST_ENABLE_BT = 1234;
    private static final Region ALL_ESTIMOTE_BEACONS_REGION = new Region("rid", null, null, null);

    private ListView merchantList;
    private CardListView cardListView;
    private BeaconManager beaconManager;
    private LeDeviceAdapter adapter;
    private ArrayList<Card> cards;
    private CardArrayAdapter mCardArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.list_merchants);
        setContentView(R.layout.list_merchants_cards);

        cards = new ArrayList<Card>();

        mCardArrayAdapter = new CardArrayAdapter(this, cards);

        cardListView = (CardListView) findViewById(R.id.cardListView);
        cardListView.setAdapter(mCardArrayAdapter);

        //initialize merchant list
        //merchantList = (ListView)findViewById(R.id.merchant_list);


        //configure list adapter
        adapter = new LeDeviceAdapter(this);
        //merchantList.setAdapter(adapter);
        //merchantList.setOnItemClickListener(createOnItemClickListener());

        //configure beacon manager
        beaconManager = new BeaconManager(this);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, final List<Beacon> beacons) {
                // Note that results are not delivered on UI thread.
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Merchants are already sorted by proximated distances
                        getActionBar().setSubtitle("Found merchants: " + beacons.size());
                        //adapter.replaceWith(beacons);
                        cards.clear();
                        for(Beacon beacon : beacons) {
                            Card c = new Card(getBaseContext(), R.layout.merchant_item);
                            c.setOnClickListener(new Card.OnCardClickListener() {
                                @Override
                                public void onClick(Card card, View view) {
                                    startActivity(new Intent(ListMerchantActivity.this, MerchantDetailActivity.class));
                                }
                            });
                            cards.add(c);
                            mCardArrayAdapter.notifyDataSetChanged();
                        }
                    }
                });
            }
        });

    }

    //todo: scanning/ refresh icon
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.scan_menu, menu);
//        MenuItem refreshItem = menu.findItem(R.id.refresh);
//        refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        beaconManager.disconnect();

        super.onDestroy();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if device supports Bluetooth Low Energy.
        if (!beaconManager.hasBluetooth()) {
            Toast.makeText(this, "Device does not have Bluetooth Low Energy", Toast.LENGTH_LONG).show();
            return;
        }

        // If Bluetooth is not enabled, let user enable it.
        if (!beaconManager.isBluetoothEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        } else {
            connectToService();
        }
    }

    @Override
    protected void onStop() {
        try {
            beaconManager.stopRanging(ALL_ESTIMOTE_BEACONS_REGION);
        } catch (RemoteException e) {
            Log.d(TAG, "Error while stopping ranging", e);
        }

        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ENABLE_BT) {
            if (resultCode == Activity.RESULT_OK) {
                connectToService();
            } else {
                Toast.makeText(this, "Bluetooth not enabled", Toast.LENGTH_LONG).show();
                getActionBar().setSubtitle("Bluetooth not enabled");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void connectToService() {
        getActionBar().setSubtitle("Scanning...");
        adapter.replaceWith(Collections.<Beacon>emptyList());
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                try {
                    beaconManager.startRanging(ALL_ESTIMOTE_BEACONS_REGION);
                } catch (RemoteException e) {
                    Toast.makeText(ListMerchantActivity.this, "Cannot find nearby merchants...!",
                            Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Cannot start ranging", e);
                }
            }
        });
    }

    private AdapterView.OnItemClickListener createOnItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //todo: go to next activity with correct restaurant info.
                //todo: grab info from view
                Intent intent =  new Intent(ListMerchantActivity.this, MerchantDetailActivity.class);
                startActivity(intent);
            }
        };
    }
}
