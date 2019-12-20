package com.metacoders.home;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class viewHolder_for_qusBank extends RecyclerView.ViewHolder {
    View mView ;
    public viewHolder_for_qusBank(View itemView) {
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


    public void setDetails(Context ctx, String title, String description){
        TextView mTitleTv = mView.findViewById(R.id.r_title_qus);
        TextView mDetailTv = mView.findViewById(R.id.r_descrip_qus);
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


    public void setOnClickListener(ViewHolder.ClickListener clickListener){
        mClickListener = clickListener;
    }

}
