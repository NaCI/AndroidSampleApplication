package com.naci.mytestapplication.ui.animelist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.naci.mytestapplication.App;
import com.naci.mytestapplication.R;
import com.naci.mytestapplication.data.remote.response.AnimeData;
import com.naci.mytestapplication.databinding.AnimeFragmentBinding;
import com.naci.mytestapplication.ui.base.BaseFragment;
import com.naci.mytestapplication.ui.listener.OnAnimeItemClickListener;
import com.naci.mytestapplication.util.SharedPrefsUtil;
import com.naci.mytestapplication.util.ViewModelFactory;
import com.naci.mytestapplication.util.enums.ArgExtras;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class AnimeFragment extends BaseFragment<AnimeFragmentBinding> implements OnAnimeItemClickListener {

    private AnimeViewModel mViewModel;
    private AdapterAnimeList adapterAnimeList;
    private List<AnimeData> animeList;

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    SharedPrefsUtil sharedPrefsUtil;

    public static AnimeFragment newInstance(ArrayList<AnimeData> animeList) {
        Bundle args = new Bundle();
        AnimeFragment fragment = new AnimeFragment();
        args.putParcelableArrayList(ArgExtras.ANIME_LIST_EXTRA.getText(), animeList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if (getActivity() != null && getActivity().getApplication() != null) {
            App.getInstance().getMainComponent(getBaseActivity()).inject(AnimeFragment.this);
        }
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            animeList = getArguments().getParcelableArrayList(ArgExtras.ANIME_LIST_EXTRA.getText());
        }
        // Test sharedPrefUtil
        sharedPrefsUtil.getObject("SomeData", String.class);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.anime_fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this, viewModelFactory).get(AnimeViewModel.class);
        setLiveDataObserver();
        viewDataBinding.setViewModel(mViewModel);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareRecyclerView();
        setAdapter();
    }

    public void prepareRecyclerView() {
        viewDataBinding.animeListRv.setHasFixedSize(true);
        viewDataBinding.animeListRv.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        viewDataBinding.animeListRv.addItemDecoration(new DividerItemDecoration(viewDataBinding.animeListRv.getContext(), DividerItemDecoration.VERTICAL));
        viewDataBinding.animeListRv.setItemAnimator(new DefaultItemAnimator());
    }

    private void setLiveDataObserver() {
    }

    private void setAdapter() {
        if (animeList == null || animeList.isEmpty()) {
            viewDataBinding.noItemTV.setVisibility(View.VISIBLE);
        } else {
            viewDataBinding.noItemTV.setVisibility(View.GONE);
            adapterAnimeList = new AdapterAnimeList(animeList, this);
            viewDataBinding.animeListRv.setAdapter(adapterAnimeList);
        }
    }

    @Override
    public void onAnimeDetailClicked(AnimeData anime) {
        Toast.makeText(getBaseFragmentContext(), anime.toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        App.getInstance().clearMainComponent();
    }
}
