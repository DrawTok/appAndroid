package com.drawtok.foodsoup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.drawtok.foodsoup.Interface.RecycleViewInterface;
import com.drawtok.foodsoup.Model.MenuStore;
import com.drawtok.foodsoup.R;

import java.util.List;

public class MenuStoreAdapter extends RecyclerView.Adapter<MenuStoreAdapter.MenuStoreViewHolder> {

    private final Context context;
    private final List<MenuStore> menuStoreList;
    private final RecycleViewInterface recInterface;
    private final int recyclerViewId;

    public MenuStoreAdapter(Context context, List<MenuStore> menuStoreList,
                            RecycleViewInterface recInterface, final int recyclerViewId) {
        this.context = context;
        this.menuStoreList = menuStoreList;
        this.recInterface = recInterface;
        this.recyclerViewId = recyclerViewId;
    }

    @NonNull
    @Override
    public MenuStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuStoreViewHolder(view, recInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuStoreViewHolder holder, int position) {
        MenuStore menuStore = menuStoreList.get(position);
        holder.tvNameMenu.setText(menuStore.getNameFood());
        String price = menuStore.getPriceFood() +"đ";
        holder.tvPriceMenu.setText(price);
        holder.tvTitle.setText(menuStore.getTitle());
        Glide.with(context).load(menuStore.getImageFood()).into(holder.imgMenu);
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
        if(menuStoreList != null)
        {
            return menuStoreList.size();
        }
        return 0;
    }

    public class MenuStoreViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvNameMenu;
        TextView tvPriceMenu;
        TextView tvTitle;
        ImageView imgMenu;
        View viewDivide;
        public MenuStoreViewHolder(@NonNull View itemView, RecycleViewInterface recInterface) {
            super(itemView);
            tvNameMenu  = itemView.findViewById(R.id.tv_name_menu);
            tvPriceMenu = itemView.findViewById(R.id.tv_price);
            tvTitle     = itemView.findViewById(R.id.tv_title_menu);
            imgMenu     = itemView.findViewById(R.id.image_menu);
            viewDivide  = itemView.findViewById(R.id.divide_recycle);

            itemView.setOnClickListener(view->
            {
                if(recInterface != null)
                {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION)
                    {

                        recInterface.onClickItem(pos, recyclerViewId);
                    }
                }
            });
        }
    }

}
