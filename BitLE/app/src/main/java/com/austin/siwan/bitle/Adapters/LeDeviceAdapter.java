package com.austin.siwan.bitle.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.austin.siwan.bitle.R;
import com.estimote.sdk.Beacon;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Jojo on 7/19/14.
 */
public class LeDeviceAdapter extends BaseAdapter {

    private ArrayList<Beacon> beacons;
    private LayoutInflater inflater;

    public LeDeviceAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.beacons = new ArrayList<Beacon>();
    }

    public void replaceWith(Collection<Beacon> newBeacons) {
        this.beacons.clear();
        this.beacons.addAll(newBeacons);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return beacons.size();
    }

    @Override
    public Beacon getItem(int position) {
        return beacons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflateIfRequired(view, position, parent);
        bind(getItem(position), view);
        return view;
    }

    private void bind(Beacon beacon, View view) {
        ViewHolder holder = (ViewHolder) view.getTag();

        //TODO:get static store's detail from array
        holder.priceRange.setText("Price Range: $");
        holder.category.setText("Category: Bar");
        holder.storeName.setText("Kevin and Joseph's Lounge");
    }

    private View inflateIfRequired(View view, int position, ViewGroup parent) {
        if (view == null) {
            view = inflater.inflate(R.layout.merchant_item, null);
            view.setTag(new ViewHolder(view));
        }
        return view;
    }

    static class ViewHolder {
        final TextView priceRange;
        final TextView category;
        final TextView storeName;

        ViewHolder(View view) {
            priceRange = (TextView)view.findViewById(R.id.priceRange);
            category = (TextView)view.findViewById(R.id.category);
            storeName =(TextView)view.findViewById(R.id.storeName);
        }
    }
}
