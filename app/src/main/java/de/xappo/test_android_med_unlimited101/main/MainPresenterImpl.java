package de.xappo.test_android_med_unlimited101.main;


import java.util.List;

/**
 * Created by knoppik on 07.06.16.
 */
public class MainPresenterImpl implements MainPresenter, RequestRepositoriesInteractor.OnFinishedListener {

    private MainView mMainView;
    private RequestRepositoriesInteractor mRequestRepositoriesInteractor;

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
        mRequestRepositoriesInteractor = new RequestRepositoriesInteractorImpl();
    }

    @Override
    public void onResume() {
        if (mMainView != null) {

        }
        mRequestRepositoriesInteractor.findRepositories(this);

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
    }

    @Override
    public void onFinished(List<Repository> repositories) {
        if (mMainView != null) {
            mMainView.setItems(repositories);
        }
    }
}
