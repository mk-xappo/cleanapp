package de.xappo.test_android_med_unlimited101.main;

/**
 * Created by knoppik on 07.06.16.
 */
public interface MainPresenter {

    void onResume();

    void onLoadMore();

    //FIXME: Unused
    void onItemClicked(int position);

    void onDestroy();

}
