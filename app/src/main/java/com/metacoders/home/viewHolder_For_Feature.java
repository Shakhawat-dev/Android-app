package com.metacoders.home;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class viewHolder_For_Feature extends RecyclerView.ViewHolder {
    View mView ;

    public viewHolder_For_Feature(View itemView) {
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
        TextView mTitleTv = mView.findViewById(R.id.rTitleTv_feature);
        TextView mDetailTv = mView.findViewById(R.id.rDescriptionTv_feature);
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


