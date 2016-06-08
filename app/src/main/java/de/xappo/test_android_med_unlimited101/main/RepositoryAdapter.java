package de.xappo.test_android_med_unlimited101.main;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.xappo.test_android_med_unlimited101.R;

/**
 * Created by knoppik on 07.06.16.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private final MainView mMainView;
    private List<Repository> mRepositories;

    public RepositoryAdapter(MainView mainView, List<Repository> repositories) {
        mRepositories = repositories;
        mMainView = mainView;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //FIXME: Get LayoutInflater in constructor and reuse
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Repository repository = mRepositories.get(position);
        holder.mTextViewName.setText(repository.getName());
        holder.mTextViewDescription.setText(repository.getDescription());
        holder.mTextViewLogin.setText(repository.getLogin());
        if (!repository.isFork()) {
            holder.mLinearLayout.setBackgroundColor(Color.LTGRAY);
        } else {
            holder.mLinearLayout.setBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        @BindView(R.id.linearlayout) LinearLayout mLinearLayout;
        @BindView(R.id.textview_name) TextView mTextViewName;
        @BindView(R.id.textview_description) TextView mTextViewDescription;
        @BindView(R.id.textview_login) TextView mTextViewLogin;


        public RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            mMainView.showDialogForRepository(getLayoutPosition());
            return true;
        }
    }
}
