package com.bugchain.android.development.sampleconnectserver;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by BUG CHAIN on 27/2/2558.
 */
public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ViewHolder> {

    private List<MemberObject> items;

    public ItemViewAdapter(List<MemberObject> items){
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {

        MemberObject item = items.get(i);
        holder.textName.setText(item.getFirstName() + "   " + item.getLastName());
        holder.textTel.setText(item.getTel());
        holder.textEmail.setText(item.getEmail());
        Picasso.with(holder.image.getContext()).load(item.getImageUrl())
                .error(R.drawable.ic_default_profile)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView image;
        public TextView textName;
        public TextView textTel;
        public TextView textEmail;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView)itemView.findViewById(R.id.imageProfile);
            textName = (TextView)itemView.findViewById(R.id.textName);
            textTel = (TextView)itemView.findViewById(R.id.textTel);
            textEmail = (TextView)itemView.findViewById(R.id.textEmail);
        }
    }

}
