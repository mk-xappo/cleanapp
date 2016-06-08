package de.xappo.test_android_med_unlimited101.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import de.xappo.test_android_med_unlimited101.main.recyclerviewextensions.DividerItemDecoration;
import de.xappo.test_android_med_unlimited101.main.recyclerviewextensions.EndlessRecyclerViewScrollListener;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = "MainActivity";
    private MainPresenter mMainPresenter;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter = new MainPresenterImpl(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        mAdapter = new RepositoryAdapter(mMainPresenter);

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                customLoadMoreDataFromApi();
            }
        });

    }

    private void customLoadMoreDataFromApi() {
        mMainPresenter.onLoadMore();
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
    public void addItems(List<Repository> repositories) {
        mMainPresenter.addItems(repositories);
        mAdapter.notifyItemRangeChanged(mAdapter.getItemCount(), repositories.size());
    }


    @Override
    public void showDialogForRepository(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final List<String> items = new ArrayList<>();
        items.add(mMainPresenter.getRequestRepositoriesInteractor().getRepositories().get(position).getHtml_url());
        items.add(mMainPresenter.getRequestRepositoriesInteractor().getRepositories().get(position).getOwner_html_url());
        CharSequence[] charSequences = items.toArray(new CharSequence[items.size()]);

        int checkedIten = -1;

        builder.setSingleChoiceItems(charSequences, checkedIten, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(which)));
                switch (which) {
                    case 0:
                        startActivity(browserIntent);
                        dialog.dismiss();
                        break;
                    case 1:
                        startActivity(browserIntent);
                        dialog.dismiss();
                        break;
                    default:
                        dialog.dismiss();
                        break;
                }
            }
        });

        builder.setTitle("Go to repository url or owner url?");

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(this, "Error sending request!", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "showError() Message: " + throwable.getMessage());
    }


}
