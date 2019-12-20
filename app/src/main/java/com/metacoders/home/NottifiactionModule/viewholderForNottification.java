package com.metacoders.home.NottifiactionModule;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.metacoders.home.R;

public class viewholderForNottification extends RecyclerView.ViewHolder{
    View mView ;
    public viewholderForNottification(View itemView) {
        super(itemView);

        mView = itemView ;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onItemClick(v , getAdapterPosition());

            }
        });
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true ;
            }
        });
    }


    public void setDetails(Context ctx, String description , String activity){

        TextView mDetailTv = mView.findViewById(R.id.r_TITLENottification);
        TextView activitiyView = mView.findViewById(R.id.activityTask);
        //set data to views
        mDetailTv.setText(description);
        activitiyView.setText(activity);

    }
    private viewholderForNottification.ClickListener mClickListener;
    //interface to send callbacks
    public interface ClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }


    public void setOnClickListener(viewholderForNottification.ClickListener clickListener){
        mClickListener = clickListener;
    }


}
