package de.xappo.test_android_med_unlimited101.main;


import android.app.Activity;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by knoppik on 07.06.16.
 */
public class MainPresenterImpl implements MainPresenter, RequestRepositoriesInteractor.OnResponseListener {

    private static final String TAG = "MainPresenterImpl";
    private MainView mMainView;
    @Inject
    RequestRepositoriesInteractor mRequestRepositoriesInteractor;


    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
        ((MyApp)((Activity) mainView).getApplication()).getNetComponent().inject(this);
        //mRequestRepositoriesInteractor = new RequestRepositoriesInteractorImpl();
    }

    @Override
    public void onResume() {
        if (mMainView != null) {
            //FIXME: Wanted to use this but had no reasons
        }
        Log.i(TAG, "onResume()");
        mRequestRepositoriesInteractor.findRepositories((Activity) mMainView, this);

    }

    @Override
    public void onLoadMore() {
        mRequestRepositoriesInteractor.findRepositories((Activity) mMainView, this);
    }

    @Override
    public void onItemClicked(int position) {
        if (mMainView != null) {
            mMainView.showDialogForRepository(position);
        }
    }

    @Override
    public void onDestroy() {
        mMainView = null;
        //FIXME: Release Interactor
    }

    @Override
    public void onFinished(List<Repository> repositories) {
        if (mMainView != null) {
            mMainView.addItems(repositories);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        mMainView.showError(throwable);
    }
}
