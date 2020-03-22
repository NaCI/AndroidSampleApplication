package com.naci.mytestapplication.ui.animelist;

import android.view.ViewGroup;

import com.naci.mytestapplication.R;
import com.naci.mytestapplication.data.remote.response.AnimeData;
import com.naci.mytestapplication.ui.base.BaseAdapter;
import com.naci.mytestapplication.ui.listener.OnAnimeItemClickListener;

import java.util.List;

public class AdapterAnimeList extends BaseAdapter {

    private List<AnimeData> animeList;
    private final OnAnimeItemClickListener onAnimeItemClickListener;

    public AdapterAnimeList(List<AnimeData> animeList, OnAnimeItemClickListener onAnimeItemClickListener) {
        this.animeList = animeList;
        this.onAnimeItemClickListener = onAnimeItemClickListener;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        //do specific ui operations
    }

    @Override
    public Object getDataAtPosition(int position) {
        return animeList.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.item_anime;
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    @Override
    public BaseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = super.onCreateViewHolder(parent, viewType);
        myViewHolder.bindListener(onAnimeItemClickListener);
        return myViewHolder;
    }
}
