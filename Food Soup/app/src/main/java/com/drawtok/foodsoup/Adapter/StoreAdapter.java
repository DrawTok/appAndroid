package com.drawtok.foodsoup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.drawtok.foodsoup.Interface.RecycleViewInterface;
import com.drawtok.foodsoup.Model.Store;
import com.drawtok.foodsoup.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {

    private final List<Store> listStore;
    Context context;
    private final RecycleViewInterface recycleViewInterface;

    public StoreAdapter(Context context, List<Store> listStore, RecycleViewInterface recycleViewInterface) {
        this.listStore = listStore;
        this.context = context;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_partner_store, parent, false);
        return new StoreViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        Store store = listStore.get(position);
        Glide.with(context).load(store.getImageStore()).into(holder.imageStore);
        holder.tvNameStore.setText(store.getNameStore());
        if(position != getItemCount()-1)
        {
            holder.viewDivide.setVisibility(View.VISIBLE);
        }else
        {
            holder.viewDivide.setVisibility(View.GONE);

        }
    }


    @Override
    public int getItemCount() {
        if(listStore != null)
        {
            return listStore.size();
        }
        return 0;
    }
    public static class StoreViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView imageStore;
        TextView tvNameStore;
        View viewDivide;
        public StoreViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);
            imageStore = itemView.findViewById(R.id.image_store);
            tvNameStore = itemView.findViewById(R.id.tv_name_store);
            viewDivide = itemView.findViewById(R.id.divide_recycle);

            itemView.setOnClickListener(view -> {
                if(recycleViewInterface != null)
                {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                    {
                        recycleViewInterface.onClickItem(pos, 0);
                    }
                }
            });
        }
    }

}
