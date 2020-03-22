package com.naci.mytestapplication.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;

import com.naci.mytestapplication.util.NavigationUtils;

public abstract class BaseActivity extends AppCompatActivity {

    //TODO : toolbar eklenecek
    //TODO : navigation component eklenecek

    @LayoutRes
    protected abstract int getLayoutResID();

    @IdRes
    protected abstract int getFragmentContainerId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResID());
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            hideKeyboard();
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    public void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }


    public void addFragment(BaseFragment fragment) {
        NavigationUtils.addFragment(getSupportFragmentManager(), fragment, getFragmentContainerId());
    }

    public void replaceFragment(BaseFragment fragment) {
        NavigationUtils.replaceFragment(getSupportFragmentManager(), fragment, getFragmentContainerId());
    }

    public void removeFragment(BaseFragment fragment) {
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    public <T extends BaseFragment> void returnToFragment(Class<T> fragment) {
        getSupportFragmentManager().popBackStack(fragment.getName(), 0);
    }
}
