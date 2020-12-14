package com.example.prawajazdy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewConfig {
    private Context context;
    private PytaniaAdapter adapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Pytanie> pytania, List<String> keys) {
        this.context = context;
        this.adapter = new PytaniaAdapter(pytania, keys);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);
    }

    class PytanieItemView extends RecyclerView.ViewHolder {
        private TextView pytanie;
        private TextView odpowiedzA;
        private TextView odpowiedzB;
        private TextView odpowiedzC;

        private String key;

        public PytanieItemView(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(context)
                .inflate(R.layout.pytanie_list_item, parent, false));

            this.pytanie = (TextView) itemView.findViewById(R.id.pytanie);
            odpowiedzA = (TextView) itemView.findViewById(R.id.odpowiedzA);
            odpowiedzB = (TextView) itemView.findViewById(R.id.odpowiedzB);
            odpowiedzC = (TextView) itemView.findViewById(R.id.odpowiedzC);
        }

        @SuppressLint("SetTextI18n")
        public void bind(Pytanie object, String key) {
            pytanie.setText(object.getPytanie());
            if (object.getOdpowiedz_A().equals("") && object.getOdpowiedz_B().equals("") && object.getOdpowiedz_C().equals("")) {
                odpowiedzA.setText("Tak");
                odpowiedzB.setText("Nie");
            } else {
                odpowiedzA.setText(object.getOdpowiedz_A());
                odpowiedzB.setText(object.getOdpowiedz_B());
            }
            odpowiedzC.setText(object.getOdpowiedz_C());
            this.key = key;
        }
    }
    class PytaniaAdapter extends RecyclerView.Adapter<PytanieItemView> {
        private List<Pytanie> mPytaniaList;
        private List<String> mKeys;

        public PytaniaAdapter(List<Pytanie> pytanies, List<String> mKeys) {
            this.mPytaniaList = pytanies;
            this.mKeys = mKeys;
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
    }
}
