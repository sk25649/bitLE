package com.austin.siwan.bitle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.austin.siwan.bitle.bitpay.model.Invoice;
import com.austin.siwan.bitle.tasks.BitpayInvoiceAsyncTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Jojo on 7/19/14.
 */
public class CloseTabActivity extends Activity {

    private ListView orderItemList;
    private Button acceptButton;
    private TextView subtotal;
    private ListView checkItemList;
    private ArrayList<String> itemNames, orderCounts;
    private double subTotalAmount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.close_tab);
        getActionBar().setDisplayHomeAsUpEnabled(false);

        Bundle bundle = this.getIntent().getExtras();
        itemNames = bundle.getStringArrayList("itemNames");
        orderCounts = bundle.getStringArrayList("orderCounts");

        checkItemList = (ListView)findViewById(R.id.checkItemList);
        checkItemList.setAdapter(new BillItemAdapter());
        acceptButton = (Button)findViewById(R.id.acceptPayment);
        subtotal = (TextView)findViewById(R.id.subTotal);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("subtotal: " + subTotalAmount);
                try {
                    Invoice invoice = (Invoice) new BitpayInvoiceAsyncTask(subTotalAmount/4).execute().get();
                    Intent intent = new Intent(CloseTabActivity.this, InvoiceWebViewActivity.class);
                    intent.putExtra("url", invoice.getUrl());
                    startActivity(intent);
                } catch (ExecutionException e) {

                } catch (InterruptedException e) {

                }
            }
        });
    }

    private class BillItemAdapter extends BaseAdapter {

        private class ViewHolder {
            public TextView checkItemName, checkItemSubTotalPrice;
        }

        @Override
        public int getItemViewType(int position) {
            return (position != itemNames.size()-1) ? 0 : 1;
        }

        @Override
        public int getCount() {
            return itemNames.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            final ViewHolder holder;
            if(convertView == null) {
                view = getLayoutInflater().inflate(R.layout.check_item, parent, false);
                holder = new ViewHolder();
                holder.checkItemName = (TextView)view.findViewById(R.id.checkItemName);
                holder.checkItemSubTotalPrice = (TextView)view.findViewById(R.id.checkItemCount);
                view.setTag(holder);
            } else {
                holder = (ViewHolder)view.getTag();
            }
            if(getItemViewType(position) == 0) {
                holder.checkItemName.setText(itemNames.get(position) + " $" + priceLookUp(itemNames.get(position)) + " X " + orderCounts.get(position));
                holder.checkItemSubTotalPrice.setText(String.format("$%.2f",
                        calcualteSubTotal(priceLookUp(itemNames.get(position)), orderCounts.get(position))));
            } else {
                holder.checkItemName.setText(String.format("Subtotal: $%.2f", subTotalAmount/2));
                holder.checkItemSubTotalPrice.setText("");
            }
            return view;
        }
    }

    private double calcualteSubTotal(int amount, String price) {
        int _price = Integer.parseInt(price);
        subTotalAmount += amount * _price * .01;
        return amount * _price * .01;
    }

    private int priceLookUp(String itemName) {
        if(itemName.equals("Red Bull")) {
            return 1;
        } else if(itemName.equals("Bottle Water")) {
            return 2;
        } else if (itemName.equals("Pretzel")) {
            return 1;
        } else if (itemName.equals("Chex Mix")) {
            return 2;
        } else {
            return 0;
        }
    }
}
