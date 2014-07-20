package com.austin.siwan.bitle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jojo on 7/19/14.
 */

public class MenuListAdapter extends BaseAdapter {

    private Context context;
    private String[] items, prices;

    public MenuListAdapter(Context context, String[] items, String[] prices) {
        this.context = context;
        this.items = items;
        this.prices = prices;
    }

    public class ViewHolder {
        public TextView item_name, item_price, item_count;
        public ImageView plus_button, minus_button;
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        final ViewHolder holder;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.order_item, parent, false);
            holder = new ViewHolder();
            holder.item_name = (TextView)view.findViewById(R.id.item_name);
            holder.item_price = (TextView)view.findViewById(R.id.item_price);
            holder.item_count = (TextView)view.findViewById(R.id.item_count);
            holder.plus_button = (ImageView)view.findViewById(R.id.plus_button);
            holder.minus_button = (ImageView)view.findViewById(R.id.minus_button);

            holder.plus_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(holder.item_count.getText().toString());
                    count++;
                    holder.item_count.setText(String.format("%d", count));
                    updateOrder(holder.item_name.getText().toString(), holder.item_count.getText().toString());
                }
            });

            holder.minus_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int count = Integer.parseInt(holder.item_count.getText().toString());
                    if(count != 0) {
                        count--;
                        holder.item_count.setText(String.format("%d", count));
                        updateOrder(holder.item_name.getText().toString(), holder.item_count.getText().toString());
                    }
                }
            });
            view.setTag(holder);
        } else {
            holder = (ViewHolder)view.getTag();
        }

        holder.item_name.setText(items[position]);
        holder.item_price.setText(String.format("$%s",prices[position]));
        return view;
    }

    public void updateOrder(String key, String value) {
        ConcurrentHashMap<String, String> order = MenuActivity.getOrderMap();
        if(order.containsKey(key)) {
            order.replace(key, value);
        } else {
            order.put(key, value);
        }
    }

}
