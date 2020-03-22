package com.naci.mytestapplication.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.naci.mytestapplication.App;
import com.naci.mytestapplication.R;
import com.naci.mytestapplication.databinding.MainFragmentBinding;
import com.naci.mytestapplication.ui.animelist.AnimeFragment;
import com.naci.mytestapplication.ui.base.BaseFragment;
import com.naci.mytestapplication.ui.customview.CustomProgressBar;
import com.naci.mytestapplication.util.ViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import timber.log.Timber;

public class MainFragment extends BaseFragment<MainFragmentBinding> implements TextView.OnEditorActionListener {

    @Inject
    ViewModelFactory viewModelFactory;

    @Inject
    CustomProgressBar customProgressBar;

    @Inject
    @Named("TestModule")
    String testModuleString;

    private MainViewModel mainViewModel;


    static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.w("Test di text testModuleString : %s", testModuleString);
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.main_fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if (getActivity() != null && getActivity().getApplication() != null) {
            App.getInstance().getMainComponent(getBaseActivity()).inject(MainFragment.this);
        }
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);
        viewDataBinding.setViewModel(mainViewModel);
        setLiveDataObserver();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDataBinding.fetchAnimeListBTN.setOnClickListener(viewButton -> mainViewModel.onFetchClicked());
        viewDataBinding.animeGenreET.setOnEditorActionListener(this);
    }

    private void setLiveDataObserver() {
        mainViewModel.animeListResponse.observe(getViewLifecycleOwner(), animeModelResource -> {
            switch (animeModelResource.status) {
                case LOADING: {
                    customProgressBar.showProgress();
                    break;
                }
                case SUCCESS: {
                    Timber.d("Anime Response : %s", animeModelResource.data);
                    if(animeModelResource.data != null) {
                        getBaseActivity().replaceFragment(AnimeFragment.newInstance(new ArrayList<>(animeModelResource.data.getAnimeList())));
                    }
                    break;
                }
                case ERROR: {
                    Toast.makeText(getContext(), animeModelResource.message, Toast.LENGTH_SHORT).show();
                    break;
                } case COMPLETED: {
                    customProgressBar.hideProgress();
                    break;
                }
            }
        });
    }

    @Override
    public void onDetach() {
        App.getInstance().clearMainComponent();
        super.onDetach();
    }

    @Override
    public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
        if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_SEARCH){
            mainViewModel.onFetchClicked();
        }
        return false;
    }
}
