package com.austin.siwan.bitle.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.austin.siwan.bitle.ListMerchantActivity;
import com.austin.siwan.bitle.MerchantDetailActivity;
import com.austin.siwan.bitle.R;
import com.estimote.sdk.Beacon;

import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardArrayAdapter;
import it.gmariotti.cardslib.library.internal.CardHeader;
import it.gmariotti.cardslib.library.internal.CardThumbnail;

/**
 * Created by Kevin on 8/10/2014.
 */
public class MerchantCardListAdapter extends CardArrayAdapter{
    private String[] names = {"Kevin and Joseph's lounge", "I <3 pho", "In-N-Out"},
            categories = {"Bar", "Vietnamese", "Burger"};
    private int index;
   public MerchantCardListAdapter(Context context, List<Card> cards) {
       super(context, cards);
   }

   public void replaceWith(List<Beacon> beacons) {
       this.clear();
       for(int i=0; i < beacons.size(); i++) {
           index = i;
           Card card = new Card(this.getContext());

           CardHeader cardHeader = new CardHeader(this.getContext());
           cardHeader.setTitle(names[i]);

           CardThumbnail cardThumbnail = new CardThumbnail(this.getContext());
           cardThumbnail.setDrawableResource(R.drawable.bull);

           card.addCardHeader(cardHeader);
           card.addCardThumbnail(cardThumbnail);

           card.setTitle(categories[i]);
           card.setOnClickListener(new Card.OnCardClickListener() {
               @Override
               public void onClick(Card card, View view) {
                   Intent intent =  new Intent(view.getContext(), MerchantDetailActivity.class);
                   intent.putExtra("name", names[index]);
                   intent.putExtra("category", categories[index]);
                   getContext().startActivity(intent);
               }
           });

           this.add(card);
       }
   }
}
