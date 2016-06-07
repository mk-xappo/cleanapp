package de.xappo.test_android_med_unlimited101.main;

import java.util.List;

/**
 * Created by knoppik on 07.06.16.
 */
public interface MainView {
    void setItems(List<Repository> repositories);

    List<Repository> getItems();

    void showDialogForRepository(int position);

}
