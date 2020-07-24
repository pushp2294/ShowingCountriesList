package com.example.showingcountrieslist.ui.activities;

import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.showingcountrieslist.DI.ViewModelProviderFactory;
import com.example.showingcountrieslist.R;
import com.example.showingcountrieslist.model.CountriesEntity;
import com.example.showingcountrieslist.roomdatabase.DaoInterface;
import com.example.showingcountrieslist.ui.adapter.CountryListingAdapter;
import com.example.showingcountrieslist.util.Constants;
import com.example.showingcountrieslist.util.RecyclerItemClickListener;
import com.example.showingcountrieslist.viewModels.MainActivityViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    @Inject
    ViewModelProviderFactory providerFactory;
    MainActivityViewModel mainActivityViewModel;
    @Inject
    DaoInterface daoInterface;
    RecyclerView rv_countrlisting;
    LinearLayoutManager linearLayoutManager;
    SearchView searchView;
    CountryListingAdapter countryListingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityViewModel = ViewModelProviders.of(this, providerFactory).get(MainActivityViewModel.class);
        rv_countrlisting = findViewById(R.id.rv_countrlisting);
        searchView = findViewById(R.id.searchview);
        linearLayoutManager = new LinearLayoutManager(this);
        callApi();
        LiveData<List<CountriesEntity>> listLiveData = daoInterface.getAllList();
        listLiveData.observe(this, new Observer<List<CountriesEntity>>() {
            @Override
            public void onChanged(List<CountriesEntity> countriesEntityList) {
                setAdapter(countriesEntityList);
            }
        });
    }

    public void callApi() {
        if (Constants.isNetworkAvailable(this)) {
            MutableLiveData<String> CheckingForErrors = mainActivityViewModel.reFreshTheNewCountrylist();
            CheckingForErrors.observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    if (s.equalsIgnoreCase("error"))
                        Toast.makeText(MainActivity.this, "Something went wrong , data has not refreshed", Toast.LENGTH_LONG).show();
                    else if (s.equalsIgnoreCase("success")) {
                        Toast.makeText(MainActivity.this, "Data refreshed.", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(this, "Please connect to internet to refresh data.", Toast.LENGTH_LONG).show();
        }

    }

    public void setAdapter(List<CountriesEntity> countriesEntityList) {
        if (countriesEntityList != null && !countriesEntityList.isEmpty()) {
            if (countryListingAdapter == null) {
                countryListingAdapter = new CountryListingAdapter(countriesEntityList, this);
                rv_countrlisting.setLayoutManager(linearLayoutManager);
                rv_countrlisting.setAdapter(countryListingAdapter);
            } else {
                countryListingAdapter.notifyDataSetChanged();
            }
        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (countryListingAdapter != null)
                    countryListingAdapter.getFilter().filter(newText);
                return false;
            }
        });

        rv_countrlisting.addOnItemTouchListener(new RecyclerItemClickListener(this, rv_countrlisting,

                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        Intent intent1 = new Intent(MainActivity.this, MainActivity2.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("Flag", String.valueOf(countryListingAdapter.filteristlistOfCountryDetails.get(position).getFlag()));
                        bundle.putString("Countryname", countryListingAdapter.filteristlistOfCountryDetails.get(position).getName());
                        bundle.putString("capital", countryListingAdapter.filteristlistOfCountryDetails.get(position).getCapital());
                        bundle.putString("population", String.valueOf(countryListingAdapter.filteristlistOfCountryDetails.get(position).getPopulation()));
                        bundle.putString("latlong", String.valueOf(countryListingAdapter.filteristlistOfCountryDetails.get(position).getLatlng().get(0)));
                        intent1.putExtras(bundle);
                        startActivity(intent1);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }


                }));
    }
}