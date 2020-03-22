package com.naci.mytestapplication.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.naci.mytestapplication.R;
import com.naci.mytestapplication.ui.base.BaseFragment;

public class NavigationUtils {

    public static void addFragment(FragmentManager fragmentManager, Fragment fragment, int container) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment previousFragment = fragmentManager.findFragmentById(container);


        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);

        ft.show(fragment)
            .add(container, fragment)
            .addToBackStack(fragment.getClass().getName())
            .commit();

        if (previousFragment != null) {
            ft.hide(previousFragment);
        }

    }

    public static void replaceFragment(FragmentManager fragmentManager, Fragment fragment, int container) {
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);

        ft.replace(container, fragment)
            .addToBackStack(fragment.getClass().getName())
            .commit();
    }

    public static void clearAllFragment(FragmentManager fragmentManager) {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public static void removeFragment(FragmentManager fragmentManager, BaseFragment fragment) {
        fragmentManager.beginTransaction().remove(fragment).commit();
    }

    public static <T extends BaseFragment> void returnToFragment(FragmentManager fragmentManager, Class<T> fragment) {
        fragmentManager.popBackStack(fragment.getName(), 0);
    }

    public static <T extends BaseFragment> T getFragmentByTag(FragmentManager fragmentManager, Class<T> fragment) {
        return (T) fragmentManager.findFragmentByTag(fragment.getSimpleName());
    }


}
