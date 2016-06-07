package de.xappo.test_android_med_unlimited101.main;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.xappo.test_android_med_unlimited101.R;

/**
 * Created by knoppik on 07.06.16.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

    private List<Repository> mRepositories;

    public RepositoryAdapter(List<Repository> repositories) {
        mRepositories = repositories;
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository, parent, false);
        return new RepositoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        Repository repository = mRepositories.get(position);
        holder.mTextViewName.setText(repository.getName());
        holder.mTextViewDescription.setText(repository.getDescription());
        holder.mTextViewLogin.setText(repository.getLogin());
    }

    @Override
    public int getItemCount() {
        return mRepositories.size();
    }

    public class RepositoryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textview_name) TextView mTextViewName;
        @BindView(R.id.textview_description) TextView mTextViewDescription;
        @BindView(R.id.textview_login) TextView mTextViewLogin;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
