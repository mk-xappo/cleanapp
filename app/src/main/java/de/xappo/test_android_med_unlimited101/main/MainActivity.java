package de.xappo.test_android_med_unlimited101.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.xappo.test_android_med_unlimited101.R;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";
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

        mMainPresenter = new MainPresenterImpl(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRepositories = new ArrayList<>();

        mRepositories.add(new Repository("1", "a", "x", "www.aaa.de"));
        mRepositories.add(new Repository("2", "b", "y", "www.bbb.de"));
        mRepositories.add(new Repository("3", "c", "z", "www.ccc.de"));

        mAdapter = new RepositoryAdapter(mRepositories);

        mRecyclerView.setAdapter(mAdapter);

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
        mRepositories.clear();
        mRepositories.addAll(repositories);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public List<Repository> getItems() {
        return mRepositories;
    }


    @Override
    public void showDialogForRepository(int position) {

    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Error sending request!", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "showError() Message: " + throwable.getMessage());


    }
}
