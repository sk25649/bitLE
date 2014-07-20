package com.austin.siwan.bitle;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.austin.siwan.bitle.Adapters.TabsPagerAdapter;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jojo on 7/19/14.
 */
public class MenuActivity extends FragmentActivity implements ActionBar.TabListener {

    private final static String TAG = MenuActivity.class.getSimpleName();

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private Button orderButton, closeTabButton;
    private ListView menuList;
    private static ConcurrentHashMap<String, String> order;

    // Tab titles
    private String[] tabs = {"Snacks", "Drinks"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.merchant_menu);

        order = new ConcurrentHashMap<String, String>();

        //initialize order and close tab button
        orderButton = (Button)findViewById(R.id.order);
        closeTabButton = (Button)findViewById(R.id.closeTab);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MenuActivity.this, "Your Order has been Placed!", Toast.LENGTH_LONG).show();
            }
        });

        closeTabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to closeTabActivity
                Intent intent = new Intent(MenuActivity.this, CloseTabActivity.class);
                ArrayList<String> itemNames = new ArrayList<String>(order.keySet());
                itemNames.add("Filler");
                ArrayList<String> orderCounts = new ArrayList<String>(order.values());
                orderCounts.add("Filler");
                intent.putStringArrayListExtra("itemNames", itemNames);
                intent.putStringArrayListExtra("orderCounts", orderCounts);
                startActivity(intent);
            }
        });

        //initializing tab pager
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float v, int i2) {}

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int position) {}
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

    public static ConcurrentHashMap<String, String> getOrderMap() {
        return order;
    }

}
