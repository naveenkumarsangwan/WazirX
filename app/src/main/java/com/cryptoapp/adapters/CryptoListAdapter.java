package com.cryptoapp.adapters;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cryptoapp.CryptoDetailedActivity;
import com.cryptoapp.MainActivity;
import com.cryptoapp.R;
import com.cryptoapp.models.CryptoListModel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CryptoListAdapter extends RecyclerView.Adapter<CryptoListAdapter.myviewholder> {
    List<CryptoListModel> mCryptoList;
    private Context context;

    public CryptoListAdapter(MainActivity context, List<CryptoListModel> list) {
        this.context = context;
        this.mCryptoList = list;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateCryptoList(List<CryptoListModel> list) {
        this.mCryptoList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
        return new myviewholder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.symbol.setText(mCryptoList.get(position).getSymbol());
        holder.quoteAsset.setText(mCryptoList.get(position).getQuoteAsset());
        holder.lastPrice.setText(mCryptoList.get(position).getLastPrice());
        float currentPrice = Float.parseFloat(mCryptoList.get(position).getLastPrice());
        float oldPrice = Float.parseFloat(mCryptoList.get(position).getOpenPrice());
        float result = ((currentPrice - oldPrice) / currentPrice) * 100;
        if (result > 0.0) {
            holder.changePrice.setText(String.format("%.2f", result));
            holder.changePrice.setBackgroundResource(R.drawable.rounded_corner_green);
        } else if (result < 0.0) {
            holder.changePrice.setText(String.format("%.2f", result));
            holder.changePrice.setBackgroundResource(R.drawable.rounded_cornor_red);
        }


        holder.layout.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), CryptoDetailedActivity.class);
            intent.putExtra("symbol", mCryptoList.get(position).getSymbol());
            intent.putExtra("baseAsset", mCryptoList.get(position).getBaseAsset());
            intent.putExtra("quoteAsset", mCryptoList.get(position).getQuoteAsset());
            intent.putExtra("openPrice", mCryptoList.get(position).getOpenPrice());
            intent.putExtra("lowPrice", mCryptoList.get(position).getLowPrice());
            intent.putExtra("highPrice", mCryptoList.get(position).getHighPrice());
            intent.putExtra("lastPrice", mCryptoList.get(position).getLastPrice());
            intent.putExtra("volume", mCryptoList.get(position).getVolume());
            view.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        if (this.mCryptoList != null)
            return this.mCryptoList.size();
        else
            return 0;
    }


    public static class myviewholder extends RecyclerView.ViewHolder {
        TextView symbol, quoteAsset, lastPrice, changePrice;
        CardView layout;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            symbol = itemView.findViewById(R.id.txt_symbol);
            quoteAsset = itemView.findViewById(R.id.txt_quoteasset);
            lastPrice = itemView.findViewById(R.id.txt_lastprice);
            changePrice = itemView.findViewById(R.id.txt_increasePrice);
            layout = itemView.findViewById(R.id.cardView);
        }
    }
}
