package de.xappo.test_android_med_unlimited101.main;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import de.xappo.test_android_med_unlimited101.main.exceptions.BufferedReaderNotClosedException;

/**
 * Created by knoppik on 07.06.16.
 */
public class RequestRepositoriesInteractorImpl implements RequestRepositoriesInteractor {
    private static final String TAG = "ReqRepositoriesIntImpl";

    public static final String URL_HTTPS_API_GITHUB_COM_USERS_XING_REPOS = "https://api.github.com/users/xing/repos";
    public static final String URL_QUESTIONMARK = "?";
    public static final String URL_PARAM_KEY_ACCESS_TOKEN = "access_token=";
    public static final String URL_PARAM_VALUE_ACCESS_TOKEN = "4282103b6820b1295a9e20e2d3c07fe20bb6bfcf";
    public static final String URL_AMPERSAND = "&";
    public static final String URL_PARAM_KEY_PAGE = "page=";
    public static final String URL_PARAM_KEY_PER_PAGE = "per_page=";
    public static final int URL_PARAM_VALUE_PER_PAGE = 10;


    private Throwable mThrowable;
    private List<Repository> mRepositories;
    private int mCurrentPage = 0;
    private boolean mRequestCompleted;

    public RequestRepositoriesInteractorImpl() {
        mRepositories = new ArrayList<>();
    }

    public List<Repository> getRepositories() {
        return mRepositories;
    }

    //FIXME: May be better to pass the presenter instead of the activity
    @Override
    public void findRepositories(final Activity activity, final OnResponseListener onResponseListener) {
        mRequestCompleted = false;
        Log.i(TAG, "findRepositories()");
        mCurrentPage++;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "findRepositories() run()");
                    mRepositories = getFormattedRepositories(mCurrentPage);
                    mThrowable = null;
                    mRequestCompleted = true;
                } catch (JSONException e) {
                    mThrowable = e;
                } catch (IOException e) {
                    mThrowable = e;
                } catch (BufferedReaderNotClosedException e) {
                    mThrowable = e;
                }
            }
        }).start();


        //FIXME: I don't like this, Using retrofit I wouldn't need this loop
        do {
            //FIXME: We could run into an endless loop. So we shall work with a timeout value
        } while ((mRequestCompleted == false) && (mThrowable == null));


        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mThrowable == null) {
                    onResponseListener.onFinished(mRepositories);
                } else {
                    onResponseListener.onError(mThrowable);
                }
            }
        });
    }

    private List<Repository> getFormattedRepositories(int page) throws JSONException, IOException, BufferedReaderNotClosedException {
        JSONArray jsonArray = new JSONArray(requestRepositories(page));
        List<Repository> repositories = new ArrayList<>();
        Log.i(TAG, "mRepositories: " + repositories);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String description = jsonObject.getString("description");
            String html_url = jsonObject.getString("html_url");
            //FIXME: fork Flag doesn't seem to be missing, so no handling for this case was implemented, we shall maybe use Boolean instead of boolean
            boolean fork = jsonObject.getBoolean("fork");
            JSONObject owner = jsonObject.getJSONObject("owner");
            String login = owner.getString("login");
            String owner_html_url = owner.getString("html_url");
            repositories.add(new Repository(name, description, login, html_url, owner_html_url, fork));
        }
        return repositories;
    }

    private String requestRepositories(int page) throws IOException, BufferedReaderNotClosedException {
        StringBuilder stringBuilder = new StringBuilder();
        String urlStr = URL_HTTPS_API_GITHUB_COM_USERS_XING_REPOS + URL_QUESTIONMARK + URL_PARAM_KEY_ACCESS_TOKEN + URL_PARAM_VALUE_ACCESS_TOKEN + URL_AMPERSAND +
                URL_PARAM_KEY_PAGE + page + URL_AMPERSAND + URL_PARAM_KEY_PER_PAGE + URL_PARAM_VALUE_PER_PAGE;
        URL url = new URL(urlStr);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        final int responseCode = httpURLConnection.getResponseCode();
        //FIXME: Handle other cases!
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            //FIXME: Try-With-Statement may have been better
            try {
                bufferedReader.close();
            } catch (IOException e) {
                throw new BufferedReaderNotClosedException(e);
            }
        }
        httpURLConnection.disconnect();
        return stringBuilder.toString();
    }


}
