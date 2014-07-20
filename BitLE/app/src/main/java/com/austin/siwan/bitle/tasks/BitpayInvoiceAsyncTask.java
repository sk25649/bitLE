package com.austin.siwan.bitle.tasks;

import android.net.Uri;
import android.os.AsyncTask;
import android.widget.TextView;

import com.austin.siwan.bitle.bitpay.model.BitPay;
import com.austin.siwan.bitle.bitpay.model.Invoice;

/**
 * Created by Kevin on 7/19/2014.
 */
public class BitpayInvoiceAsyncTask extends AsyncTask {
    private Invoice invoice;
    private TextView invoiceTextView;

    public BitpayInvoiceAsyncTask(TextView invoice) {
        this.invoiceTextView = invoice;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        BitPay bitPay = new BitPay("l927qFQwgHR9kfVn8NJW2B5LE0qKbXPy7BxG0u7TJQs", "USD");
        invoice = bitPay.createInvoice(100);
        return invoice;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        invoiceTextView.setText(String.valueOf(invoice.getCurrentTime()));
    }


}
