package com.metacoders.home.bookMarkController;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.metacoders.home.R;
import com.metacoders.home.ViewHolder;
import com.squareup.picasso.Picasso;

public class viewHolderForBOOKMARK extends RecyclerView.ViewHolder {

    View mView;

    public viewHolderForBOOKMARK(View itemView) {
        super(itemView);

        mView = itemView;

        //item click
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        });
        //item long click
        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view, getAdapterPosition());
                return true;
            }
        });

    }

    //set details to recycler view row
    public void setDetails(Context ctx, String title, String description, String image, String postID ){
        //Views
        TextView mTitleTv = mView.findViewById(R.id.rTitleTvBOOKMARK);
        TextView mDetailTv = mView.findViewById(R.id.rDescriptionTvBOOKMARK);
        ImageView mImageIv = mView.findViewById(R.id.rImageViewBOOKMARK);
        TextView  postid = mView.findViewById(R.id.postIDBOOKMARK);

        //set data to views
        mTitleTv.setText(title);
        mDetailTv.setText(description);
        postid.setText(postID);
        Picasso.get().load(image).error(R.drawable.loading).into(mImageIv);


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
