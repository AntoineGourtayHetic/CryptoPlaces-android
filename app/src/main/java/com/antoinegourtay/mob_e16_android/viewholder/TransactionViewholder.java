package com.antoinegourtay.mob_e16_android.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.antoinegourtay.mob_e16_android.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by antoinegourtay on 21/12/2017.
 */

public class TransactionViewholder extends RecyclerView.ViewHolder {

    @BindView(R.id.textViewIdTransaction)
    TextView idTransaction;
    @BindView(R.id.textViewDateTransaction)
    TextView dateTransaction;
    @BindView(R.id.textViewAmountTransaction)
    TextView amountTransaction;

    public TransactionViewholder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction.setText(idTransaction);
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction.setText(dateTransaction);
    }

    public void setAmountTransaction(String amountTransaction) {
        this.amountTransaction.setText(amountTransaction);
    }

}
