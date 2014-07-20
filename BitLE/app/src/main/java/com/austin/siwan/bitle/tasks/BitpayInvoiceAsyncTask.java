package com.austin.siwan.bitle.tasks;

import android.os.AsyncTask;

import com.austin.siwan.bitle.bitpay.model.BitPay;

/**
 * Created by Kevin on 7/19/2014.
 */
public class BitpayInvoiceAsyncTask extends AsyncTask {

    @Override
    protected Object doInBackground(Object[] objects) {
        BitPay bitPay = new BitPay("l927qFQwgHR9kfVn8NJW2B5LE0qKbXPy7BxG0u7TJQs", "USD");
        return bitPay.createInvoice(100);
    }

}
