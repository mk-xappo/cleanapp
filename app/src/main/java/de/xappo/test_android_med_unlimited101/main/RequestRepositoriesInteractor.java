package de.xappo.test_android_med_unlimited101.main;

import java.util.List;

/**
 * Created by knoppik on 07.06.16.
 */
public interface RequestRepositoriesInteractor {

    interface OnFinishedListener {
        void onFinished(List<Repository> repositories);
    }

    void findRepositories(OnFinishedListener onFinishedListener);
}
