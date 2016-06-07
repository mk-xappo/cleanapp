package de.xappo.test_android_med_unlimited101.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.xappo.test_android_med_unlimited101.R;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mMainPresenter;
    private List<Repository> mRepositories;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        mMainPresenter = new MainPresenterImpl(this);
        mRepositories = Collections.emptyList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter.onResume();

    }

    @Override
    protected void onDestroy() {
        mMainPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setItems(List<Repository> repositories) {
        mRepositories = repositories;

        mAdapter = new RepositoryAdapter(repositories);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public List<Repository> getItems() {
        return mRepositories;
    }


    @Override
    public void showDialogForRepository(int position) {

    }
}
