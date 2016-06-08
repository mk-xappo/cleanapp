package de.xappo.test_android_med_unlimited101.main;

import android.app.Activity;

import java.util.List;

/**
 * Created by knoppik on 07.06.16.
 */
public interface RequestRepositoriesInteractor {

    interface OnResponseListener {
        void onFinished(List<Repository> repositories);
        void onError(Throwable throwable);
    }

    void findRepositories(final Activity activity, final OnResponseListener onResponseListener);

    List<Repository> getRepositories();

}
