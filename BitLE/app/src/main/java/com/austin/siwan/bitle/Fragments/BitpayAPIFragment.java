package com.austin.siwan.bitle.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.austin.siwan.bitle.InvoiceWebViewActivity;
import com.austin.siwan.bitle.R;
import com.austin.siwan.bitle.TestActivity;
import com.austin.siwan.bitle.bitpay.model.Invoice;
import com.austin.siwan.bitle.tasks.BitpayInvoiceAsyncTask;
import com.austin.siwan.bitle.tasks.BitpayRatesAsyncTask;

import java.util.concurrent.ExecutionException;

/**
 * Created by Kevin on 7/19/2014.
 */
public class BitpayAPIFragment extends Fragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpButtons();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.bitpay_api_fragment, container, false);
    }
    private void setUpButtons() {
        final Button ratesButton = (Button) getActivity().findViewById(R.id.ratesButton);
        ratesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new BitpayRatesAsyncTask((TextView) getActivity().findViewById(R.id.ratesTextView)).execute();
            }
        });
        final Button invoiceButton = (Button) getActivity().findViewById(R.id.createInvoiceButton);
        invoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Invoice invoice = (Invoice) new BitpayInvoiceAsyncTask().execute().get();
                    Intent intent = new Intent(getActivity(), InvoiceWebViewActivity.class);
                    intent.putExtra("url", invoice.getUrl());
                    System.out.println(invoice.getId());
                    startActivity(intent);

                } catch (ExecutionException e) {

                } catch (InterruptedException e) {

                }
            }
        });
    }
}
