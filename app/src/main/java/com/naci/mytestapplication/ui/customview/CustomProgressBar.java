package com.naci.mytestapplication.ui.customview;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.naci.mytestapplication.R;

import timber.log.Timber;


public class CustomProgressBar extends Dialog {

    private ProgressBar progressBar;
    private Activity mActivity;

    public CustomProgressBar(Activity activity) {
        super(activity, R.style.AppTheme_NoActionBar);
        mActivity = activity;
        initDialog();
    }

    private void initDialog() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getWindow() != null) {
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            setContentView(R.layout.dialog_custom_progress_bar);

        }
        setCancelable(false);
        progressBar = findViewById(R.id.loader);
    }

    public void showProgress() {
        try {
            progressBar.setVisibility(View.VISIBLE);
            show();
        } catch (Exception e) {
            Timber.e("showProgress()", e);
        }
    }

    public void hideProgress() {
        try {
            progressBar.setVisibility(View.GONE);
            dismiss();
        } catch (Exception e) {
            Timber.e("hideProgress()", e);
        }
    }
}
