package ru.pe9.android.aanetworkingexercise;

import android.content.Context;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener {

    private static final String SEARCH_STRING_KEY = "SEARCH_STRING_KEY";
    private static final String LAST_SEARCH_REQUEST_KEY = "LAST_SEARCH_REQUEST_KEY";
    private String lastSearchRequest = "";


    private RecyclerView recyclerView;
    private OnlineImageListAdapter listAdapter;

    private EditText searchText = null;
    private Button searchButton = null;
    private TextView statusText = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();

        searchText = findViewById(R.id.searchString);
        searchText.setOnEditorActionListener(this);
        statusText = findViewById(R.id.statusText);
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        lastSearchRequest = savedInstanceState.getString(LAST_SEARCH_REQUEST_KEY);

        if (!lastSearchRequest.isEmpty()) {
            searchUsingRequest(lastSearchRequest);
        }

        searchText.setText(savedInstanceState.getString(SEARCH_STRING_KEY));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(SEARCH_STRING_KEY, searchText.getText().toString());
        outState.putString(LAST_SEARCH_REQUEST_KEY, lastSearchRequest);

//        Log.w("ERROR285", "Saved instance state");
    }



    private void initRecyclerView() {
        recyclerView = findViewById(R.id.networkItemsRecyclerView);
        listAdapter = new OnlineImageListAdapter();

        RecyclerView.LayoutManager layoutManager;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(this, 2);
        }
        else {
            layoutManager = new GridLayoutManager(this, 3);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);

        //hideKeyboard(searchButton);
    }


    private static IGiphyImagesSearchService giphySearchService = new GiphyNetworkModule().giphySearchService;
    private Callback<GiphySearchResponse> callback = new Callback<GiphySearchResponse>() {
        @Override
        public void onResponse(Call<GiphySearchResponse> call, Response<GiphySearchResponse> response) {
            if (!response.isSuccessful()) {
                statusText.setText("Request is unsuccessful, code:" + response.code());
            }
            else {
                statusText.setText("Request is successful, code:" + response.code());

                GiphySearchResponse giphySearchResponse = response.body();

                listAdapter.SetData(giphySearchResponse.GetImagesData());
                listAdapter.notifyDataSetChanged();
            } // if isSuccessful() //

            hideKeyboard(searchText);
        } // onResponse() ///

        @Override
        public void onFailure(Call call, Throwable t) {
            statusText.setText("Network problems :(");
        }
    }; // Request Callback /////


    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


    // Search button clicked /////
    @Override
    public void onClick(View v) {
        statusText.setText("Searching...");
        lastSearchRequest = searchText.getText().toString();
        searchUsingRequest(lastSearchRequest);
    }

    private void searchUsingRequest(String request) {
        Call<GiphySearchResponse> call = giphySearchService.getImages(request);
        call.enqueue(callback);
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            onClick(v);
            return true;
        }
        return false;
    }


}// End of class //
