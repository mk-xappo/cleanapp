package de.xappo.test_android_med_unlimited101.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knoppik on 07.06.16.
 */
public class RequestRepositoriesInteractorImpl implements RequestRepositoriesInteractor {
    @Override
    public void findRepositories(OnFinishedListener onFinishedListener) {
        onFinishedListener.onFinished(createRepositoryList());

    }

    private List<Repository> createRepositoryList() {
        List<Repository> repositories = new ArrayList<Repository>();
        repositories.add(new Repository("repo1", "desc1", "login1"));
        repositories.add(new Repository("repo2", "desc2", "login2"));
        repositories.add(new Repository("repo3", "desc3", "login3"));
        return repositories;

    }
}
