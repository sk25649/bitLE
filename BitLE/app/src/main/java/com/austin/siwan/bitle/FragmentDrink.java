package com.austin.siwan.bitle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Jojo on 7/19/14.
 */
public class FragmentDrink extends Fragment {

    private ListView drinkList;
    private TextView drinkName;
    private static EditText drinkCounts;
    private String[] items = {"Red Bull", "Bottle Water"}, prices = {"1", "2"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_drink, container, false);
        drinkList = (ListView) rootView.findViewById(R.id.orderItemList);
        ((ListView) drinkList).setAdapter(new MenuListAdapter(getActivity(), items, prices));
        return rootView;
    }

}
