package com.guestlogix.marianozorrilla.ui.presenter;

import androidx.annotation.CallSuper;

public abstract class BaseFragmentPresenter<View extends FragmentView> implements FragmentPresenter {

    protected View fragmentView;
    private boolean viewIsPaused;

    public BaseFragmentPresenter(View fragmentView) {
        this.fragmentView = fragmentView;
    }

    @CallSuper
    private void onViewBecameActive() {
        viewIsPaused = false;
    }

    @CallSuper
    @Override
    public void onStart() {

    }

    @CallSuper
    @Override
    public void onResume() {
        viewIsPaused = false;
        onViewBecameActive();
    }

    @CallSuper
    private void onViewPaused() {
        viewIsPaused = true;
    }

    @CallSuper
    @Override
    public void onPause() {
        viewIsPaused = true;
        onViewPaused();
    }

    @CallSuper
    @Override
    public void onDestroy() {
        fragmentView = null;
    }

    @CallSuper
    @Override
    public void onStop() {

    }
}