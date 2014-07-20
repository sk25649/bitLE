package com.austin.siwan.bitle.tasks;

import android.os.AsyncTask;
import android.widget.TextView;

import com.austin.siwan.bitle.bitpay.model.BitPay;
import com.austin.siwan.bitle.bitpay.model.Rates;

import org.json.simple.JSONArray;

/**
 * Created by Kevin on 7/19/2014.
 */
public class BitpayRatesAsyncTask extends AsyncTask{
    TextView ratesText;
    Rates rates;
    public BitpayRatesAsyncTask(TextView ratesTextView) {
        ratesText = ratesTextView;
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        BitPay bitPay = new BitPay("l927qFQwgHR9kfVn8NJW2B5LE0qKbXPy7BxG0u7TJQs", "USD");
        rates = bitPay.getRates();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        ratesText.setText(rates.getRates().toJSONString());
    }

}
