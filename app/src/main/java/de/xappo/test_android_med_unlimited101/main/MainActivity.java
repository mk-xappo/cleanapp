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
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        mRecyclerView.addItemDecoration(itemDecoration);

        mRepositories = new ArrayList<>();

        mRepositories.add(new Repository("1", "a", "x", "www.aaa.de", "www.aaa.owner.de", true));
        mRepositories.add(new Repository("2", "b", "y", "www.bbb.de", "www.bbb.owner.de", true));
        mRepositories.add(new Repository("3", "c", "z", "www.ccc.de", "www.ccc.owner.de", false));

        mAdapter = new RepositoryAdapter(this, mRepositories);

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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final List<String> items = new ArrayList<>();
        items.add(mRepositories.get(position).getHtml_url());
        items.add(mRepositories.get(position).getOwner_html_url());
        CharSequence[] charSequences = items.toArray(new CharSequence[items.size()]);

        int checkedIten = -1;

        builder.setSingleChoiceItems(charSequences, checkedIten, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(which)));
                Log.i(TAG, "showDialogForRepository() onClick() Repo: " + mRepositories.get(which).toString());
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
