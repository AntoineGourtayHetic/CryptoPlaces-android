package com.antoinegourtay.mob_e16_android.item;

import android.view.View;

import com.antoinegourtay.mob_e16_android.R;
import com.antoinegourtay.mob_e16_android.viewholder.TransactionViewholder;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

/**
 * Created by antoinegourtay on 21/12/2017.
 */

public class TransactionItem extends AbstractItem<TransactionItem, TransactionViewholder> {

    public String idTransaction;
    public String dateTransaction;
    public String amountTransaction;

    @Override
    public TransactionViewholder getViewHolder(View v) {

        TransactionViewholder holder = new TransactionViewholder(v);
        return holder;
    }

    @Override
    public int getType() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.row_transaction;
    }

    @Override
    public void bindView(TransactionViewholder holder, List<Object> payloads) {
        super.bindView(holder, payloads);

        // Cette fonction est appeller quand la recycler view affiche une cellule en particulier
        // Donc il faut definir les valeurs

        holder.setIdTransaction(this.idTransaction);
        holder.setDateTransaction(this.dateTransaction);
        holder.setAmountTransaction(this.amountTransaction);
    }
}


}
