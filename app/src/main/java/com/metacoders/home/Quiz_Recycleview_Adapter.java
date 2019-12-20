package com.metacoders.home;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Quiz_Recycleview_Adapter extends RecyclerView.Adapter {
    private ArrayList<Model_for_Quiz_row> dataset ;

    private Context mContext ;
    public Quiz_Recycleview_Adapter(ArrayList<Model_for_Quiz_row>mlist , Context context ){

        this.dataset = mlist ;
        this.mContext = context ;

    }
    public static class  ImageTypeViewHolder extends  RecyclerView.ViewHolder{
        TextView title , subtitle ;
        ImageView imageView ;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title_quiz) ;
            this.subtitle=(TextView) itemView.findViewById(R.id.subtitle_quiz) ;
            this.imageView = (ImageView) itemView.findViewById(R.id.Icon_quiz) ;

        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_quiz , parent ,  false );

        return new Quiz_Recycleview_Adapter.ImageTypeViewHolder(view);
    }
//dinbe

    @Override
    public void onBindViewHolder( final RecyclerView.ViewHolder holder,final int position) {
        final Model_for_Quiz_row object = dataset.get(position);
        (  (Quiz_Recycleview_Adapter.ImageTypeViewHolder) holder).title.setText(object.title);
        (  (Quiz_Recycleview_Adapter.ImageTypeViewHolder) holder).subtitle.setText(object.subtitle);

        ( (Quiz_Recycleview_Adapter.ImageTypeViewHolder) holder).title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Wp_Quiz_details.class);
                intent.putExtra("itemPosition", position);
                mContext.startActivity(intent);
            }
        });
        ( (Quiz_Recycleview_Adapter.ImageTypeViewHolder) holder).subtitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Wp_Quiz_details.class);
                intent.putExtra("itemPosition", position);
                mContext.startActivity(intent);
            }
        });
        ( (Quiz_Recycleview_Adapter.ImageTypeViewHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Wp_Quiz_details.class);
                intent.putExtra("itemPosition", position);
                mContext.startActivity(intent);
            }
        });





    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
