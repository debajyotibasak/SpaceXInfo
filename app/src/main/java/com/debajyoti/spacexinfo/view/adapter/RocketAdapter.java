package com.debajyoti.spacexinfo.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.debajyoti.spacexinfo.R;
import com.debajyoti.spacexinfo.api.model.Rocket;
import com.debajyoti.spacexinfo.interfaces.RocketClickListener;

import java.util.List;

public class RocketAdapter extends RecyclerView.Adapter<RocketAdapter.MyViewHolder> {

    private List<Rocket> rocketList;
    private Context context;
    private final RocketClickListener rocketClickListener;

    public RocketAdapter(Context context) {
        this.context = context;
        try {
            this.rocketClickListener = ((RocketClickListener) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement the interfaces");
        }
    }

    public void addRocketList(List<Rocket> rocketList) {
        this.rocketList = rocketList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RocketAdapter.MyViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.list_item_rocket, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindTo(rocketList.get(position));
    }

    @Override
    public int getItemCount() {
        if (rocketList == null) {
            return 0;
        } else {
            return rocketList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txvRocketName, txvRocketDescription;
        private ImageView imvRocket;

        MyViewHolder(View view) {
            super(view);
            txvRocketName = view.findViewById(R.id.txvRocketName);
            txvRocketDescription = view.findViewById(R.id.txvRocketDescription);
            imvRocket = view.findViewById(R.id.imvRocket);
        }

        void bindTo(Rocket rocket) {
            txvRocketName.setText(rocket.getRocketName());
            txvRocketDescription.setText(rocket.getDescription());

            Glide.with(context)
                    .load(rocket.getFlickrImages().get(0))
                    .apply(new RequestOptions()
                            .placeholder(R.drawable.ic_placeholder)
                            .error(R.drawable.ic_placeholder))
                    .into(imvRocket);

            itemView.setOnClickListener(v -> rocketClickListener.onRocketItemClick(rocket.getId()));
        }
    }
}
