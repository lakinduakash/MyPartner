package mypartner.ultimatex.com.mypartner;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import mypartner.ultimatex.com.mypartner.model.PartnerId;

public class PartnerIdListAdapter extends RecyclerView.Adapter<PartnerIdListAdapter.ViewHolder> {

    private ArrayList<PartnerId> partnerIdArrayList;

    public PartnerIdListAdapter(ArrayList<PartnerId> partnerIdArrayList) {
        this.partnerIdArrayList = partnerIdArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.list_item_view, parent, false
        );
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String gender = partnerIdArrayList.get(position).getGender();
        String city = partnerIdArrayList.get(position).getHome_town();
        int id = partnerIdArrayList.get(position).getId();
        String idS = gender + " " + id;
        holder.userId.setText(idS);
        holder.city.setText(city);
    }

    @Override
    public int getItemCount() {
        return partnerIdArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView userId;
        TextView city;


        ViewHolder(View view) {
            super(view);
            userId = view.findViewById(R.id.textView_user_id);
            city = view.findViewById(R.id.textView_city);
        }
    }
}
