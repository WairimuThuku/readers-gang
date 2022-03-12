package ui;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myapp.readersgang.R;

import java.util.List;

import adapter.BooksAdapter;
import models.BookResponse;
import models.Item;
import network.ApiService;
import network.RetroClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private static final int MAX_RESULTS = 5;
    private List<Item> volumeInfoList;
    private ProgressDialog dialog;
    private ApiService api;
    private BooksAdapter adapter;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        api = RetroClient.getApiService();

        dialog = new ProgressDialog(SearchActivity.this);
        dialog.setMessage("Loading...");

        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new BooksAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void doSearch(final String query) {
        dialog.show();

        Call<BookResponse> call = api.getMyJSON(query, MAX_RESULTS);
        call.enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(Call<BookResponse> call, Response<BookResponse> response) {
                dialog.dismiss();

                if (response.isSuccessful()) {
                    volumeInfoList = response.body().getItems();
                    recyclerView.smoothScrollToPosition(0);
                    adapter.setVolumeInfo(volumeInfoList);
                }
            }

            @Override
            public void onFailure(Call<BookResponse> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }
}