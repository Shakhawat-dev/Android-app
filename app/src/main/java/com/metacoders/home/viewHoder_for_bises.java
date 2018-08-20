package com.metacoders.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class viewHoder_for_bises extends RecyclerView.ViewHolder {
    View mView ;


    public viewHoder_for_bises (View itemView) {

        super(itemView);

        mView = itemView;
        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
    }



    public void setDetails(Context ctx, String title, String description){
        //Views
        TextView mTitleTv = mView.findViewById(R.id.rTitleTv_bises);
        TextView mDetailTv = mView.findViewById(R.id.rDescriptionTv_bises);
        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(description);
    }


    private ViewHolder.ClickListener mClickListener;
    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }

    public void setOnClickListener(ViewHolder.ClickListener clickListener)
    {
        mClickListener = clickListener;
    }














}
