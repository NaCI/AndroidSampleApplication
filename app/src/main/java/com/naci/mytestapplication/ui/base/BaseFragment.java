package com.naci.mytestapplication.ui.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import com.naci.mytestapplication.util.AndroidUtil;

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {

    @LayoutRes
    protected abstract int getLayoutResID();

    private ProgressBar progressDialog;
    protected T viewDataBinding;
    private Context mContext;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidUtil.setTransparentStatusBar(getBaseActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewDataBinding = DataBindingUtil.inflate(inflater, getLayoutResID(), container, false);
        if (viewDataBinding == null) {
            return inflater.inflate(getLayoutResID(), container, false);
        }
        viewDataBinding.setLifecycleOwner(this);
        return viewDataBinding.getRoot();
    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }

    public Context getBaseFragmentContext() {
        return mContext;
    }

    /*
    public void sendFirebaseLogEvent(String screenName){
        try {
            ((BaseActivity)getActivity()).sendScreenTracking(screenName);
        } catch (NullPointerException e){
            Log.e(TAG, "sendFirebaseLogEvent():NullPointerException");
        } catch(Exception e) {
            Log.e(TAG, "sendFirebaseLogEvent()");
        }
    }
    */

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
