package com.example.prawajazdy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewConfig {
    private Context context;
    private PytaniaAdapter adapter;

    public PytaniaAdapter getAdapter() {
        return adapter;
    }

    public PytaniaAdapter setConfig(RecyclerView recyclerView, Context context, List<Pytanie> pytania, List<String> keys) {
        this.context = context;
        this.adapter = new PytaniaAdapter(pytania, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

        return adapter;
    }

    class PytanieItemView extends RecyclerView.ViewHolder {
        private TextView pytanie;
        private ImageView image;
        private String key;

        public PytanieItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(context)
                .inflate(R.layout.pytanie_list_item, parent, false));

            this.pytanie = (TextView) itemView.findViewById(R.id.pytanie);
            this.image = (ImageView) itemView.findViewById(R.id.obrazek);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Pytanie object, String key) {
            pytanie.setText(object.getPytanie());
            this.key = key;
        }
    }

    class PytaniaAdapter extends RecyclerView.Adapter<PytanieItemView> implements Filterable {
        private List<Pytanie> mPytaniaList;
        private List<String> mKeys;
        private List<Pytanie> pytanieListFull;

        public PytaniaAdapter(List<Pytanie> pytanies, List<String> mKeys) {
            this.mPytaniaList = pytanies;
            this.mKeys = mKeys;
            this.pytanieListFull = new ArrayList<>(pytanies);
        }
        
        @NonNull
        @Override
        public PytanieItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PytanieItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PytanieItemView holder, int position) {
            holder.bind(mPytaniaList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mPytaniaList.size();
        }

        @Override
        public Filter getFilter() {
            return exampleFilter;
        }

        private Filter exampleFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Pytanie> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(pytanieListFull);
                } else {
                    String pattern = constraint.toString().toLowerCase().trim();

                    for (Pytanie pytanie : pytanieListFull) {
                        if (pytanie.getPytanie().toLowerCase().contains(pattern)) {
                            filteredList.add(pytanie);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mPytaniaList.clear();
                mPytaniaList.addAll((List)results.values);
                notifyDataSetChanged();
            }
        };
    }
}
