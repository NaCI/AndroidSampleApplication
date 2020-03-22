package com.naci.mytestapplication.ui.main;

import android.os.Bundle;

import com.naci.mytestapplication.App;
import com.naci.mytestapplication.R;
import com.naci.mytestapplication.ui.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getInstance().getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        replaceFragment(MainFragment.newInstance());
    }
}
