package de.xappo.test_android_med_unlimited101.main;

import java.util.List;

/**
 * Created by knoppik on 07.06.16.
 */
public interface MainPresenter {

    void onResume();

    void onLoadMore();

    void onItemClicked(int position);

    void onDestroy();

    void addItems(List<Repository> repositories);

    RequestRepositoriesInteractor getRequestRepositoriesInteractor();


}
