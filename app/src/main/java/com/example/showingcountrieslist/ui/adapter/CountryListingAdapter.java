package com.example.showingcountrieslist.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.showingcountrieslist.R;
import com.example.showingcountrieslist.model.CountriesEntity;

import java.util.ArrayList;
import java.util.List;

public class CountryListingAdapter extends RecyclerView.Adapter<CountryListingAdapter.MyHandler> implements Filterable {

    public List<CountriesEntity> listOfCountryDetails;
    public List<CountriesEntity> filteristlistOfCountryDetails;
    Context context;

    public CountryListingAdapter(List<CountriesEntity> listModelCityLocality, Context contex) {
        this.listOfCountryDetails=listModelCityLocality;
        this.filteristlistOfCountryDetails=listModelCityLocality;
        this.context=contex;
    }

    @NonNull
    @Override
    public MyHandler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.adapter_layout,viewGroup,false);
        MyHandler myHandler=new MyHandler(view);
        return myHandler;
    }


    @Override
    public void onBindViewHolder(@NonNull MyHandler viewHolder, int i) {
        viewHolder.tv_countryname.setText(filteristlistOfCountryDetails.get(i).getName());
          viewHolder.tv_population.setText(filteristlistOfCountryDetails.get(i).getPopulation().toString());
          // Image link is not working
        Glide.with(context).load(filteristlistOfCountryDetails.get(i).getFlag()).error(R.drawable.placeholder).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return filteristlistOfCountryDetails.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteristlistOfCountryDetails = listOfCountryDetails;
                } else {
                    List<CountriesEntity> filteredList = new ArrayList<>();
                    for (CountriesEntity row : listOfCountryDetails) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    filteristlistOfCountryDetails = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteristlistOfCountryDetails;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                filteristlistOfCountryDetails = (ArrayList<CountriesEntity>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static  class MyHandler extends RecyclerView.ViewHolder {

       TextView tv_countryname;
        TextView tv_population;
        ImageView imageView;
        public MyHandler(@NonNull View itemView) {
            super(itemView);
            tv_countryname=itemView.findViewById(R.id.tv_countryname);
            tv_population=itemView.findViewById(R.id.tv_population);
            imageView=itemView.findViewById(R.id.iv_countrflag);

        }
    }

}
