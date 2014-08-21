package com.austin.siwan.bitle;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.austin.siwan.bitle.Fragments.BitpayAPIFragment;

import java.util.ArrayList;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;
import it.gmariotti.cardslib.library.view.CardListView;

/**
 * Created by Kevin on 7/19/2014.
 */
public class TestActivity extends Activity {
    private String[] names = {"Kevin and Joseph's lounge", "I <3 pho", "In-N-Out"},
            categories = {"Bar", "Vietnamese", "Burger"};
    private int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_card_merchants);

        ArrayList<Card> cards = new ArrayList<Card>();

        for(int i=0; i < 3; i++) {
            index = i;
            Card card = new Card(this);
            CardHeader header = new CardHeader(this);

            header.setTitle("Title");
            card.setTitle("Sample title");

            CardThumbnail thumbnail = new CardThumbnail(this);
            thumbnail.setDrawableResource(R.drawable.bull);

            card.addCardHeader(header);
            card.addCardThumbnail(thumbnail);

            card.setOnClickListener(new Card.OnCardClickListener() {
                @Override
                public void onClick(Card card, View view) {
                    Intent intent =  new Intent(view.getContext(), MerchantDetailActivity.class);
                    intent.putExtra("name", names[index]);
                    intent.putExtra("category", categories[index]);
                    view.getContext().startActivity(intent);
                }
            });

            cards.add(card);
        }

        CardArrayAdapter mCardArrayAdapter = new CardArrayAdapter(this, cards);
        CardListView listView = (CardListView) this.findViewById(R.id.merchant_card_list);

        listView.setAdapter(mCardArrayAdapter);
    }

}
