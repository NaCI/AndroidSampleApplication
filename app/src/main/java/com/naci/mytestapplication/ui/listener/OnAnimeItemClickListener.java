package com.naci.mytestapplication.ui.listener;

import com.naci.mytestapplication.data.remote.response.AnimeData;
import com.naci.mytestapplication.ui.base.BaseItemClickListener;

public interface OnAnimeItemClickListener extends BaseItemClickListener {
    void onAnimeDetailClicked(AnimeData anime);
}
