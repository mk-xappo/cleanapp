package de.xappo.test_android_med_unlimited101.main;

import java.util.List;

/**
 * Created by knoppik on 07.06.16.
 */
public interface MainView {
    void addItems(List<Repository> repositories);


    void showDialogForRepository(int position);

    void showError(Throwable throwable);
}
