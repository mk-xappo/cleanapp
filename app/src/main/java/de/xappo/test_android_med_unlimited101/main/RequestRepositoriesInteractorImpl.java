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

/**
 * Created by knoppik on 07.06.16.
 */
public class RequestRepositoriesInteractorImpl implements RequestRepositoriesInteractor {
    private static final String TAG = "ReqRepositoriesIntImpl";

    private Throwable mThrowable;
    private List<Repository> mRepositories;

    @Override
    public void findRepositories(final Activity activity, final OnResponseListener onResponseListener) {
        Log.i(TAG, "findRepositories()");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i(TAG, "findRepositories() run()");
                    mRepositories = getRepositories();
                    mThrowable = null;
                } catch (JSONException e) {
                    mThrowable = e;
                } catch (IOException e) {
                    mThrowable = e;
                } catch (BufferedReaderNotClosedException e) {
                    mThrowable = e;
                }
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    private List<Repository> getRepositories() throws JSONException, IOException, BufferedReaderNotClosedException {
        JSONArray jsonArray = new JSONArray(requestRepositories());
        List<Repository> repositories = new ArrayList<>();
        Log.i(TAG, "mRepositories: " + repositories);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String name = jsonObject.getString("name");
            String description = jsonObject.getString("description");
            JSONObject owner = jsonObject.getJSONObject("owner");
            String login = owner.getString("login");
            String html_url = owner.getString("html_url");
            repositories.add(new Repository(name, description, login, html_url));
        }
        return repositories;
    }

    private String requestRepositories() throws IOException, BufferedReaderNotClosedException {
        StringBuilder stringBuilder = new StringBuilder();
        URL url = new URL("https://api.github.com/users/xing/repos");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        final int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader inputStreamReader = new InputStreamReader(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
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
