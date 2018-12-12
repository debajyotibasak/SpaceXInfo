package com.debajyoti.spacexinfo.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.debajyoti.spacexinfo.R;
import com.debajyoti.spacexinfo.api.model.PastLaunch;
import com.debajyoti.spacexinfo.interfaces.MissionClickListener;
import com.debajyoti.spacexinfo.interfaces.RocketClickListener;
import com.debajyoti.spacexinfo.utils.AppUtils;

import java.util.List;

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.MyViewHolder> {

    private List<PastLaunch> missionList;
    private Context context;
    private Handler handler = new Handler();
    private final MissionClickListener missionClickListener;

    public MissionAdapter(Context context) {
        this.context = context;
        try {
            this.missionClickListener = ((MissionClickListener) context);
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement the interfaces");
        }
    }

    public void addMissionList(List<PastLaunch> missionList) {
        this.missionList = missionList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MissionAdapter.MyViewHolder(
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.list_item_mission, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindTo(missionList.get(position));
    }

    @Override
    public int getItemCount() {
        if (missionList == null) {
            return 0;
        } else {
            return missionList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView txvMissionName, txvMissionDate, txvRocketName;
        private ImageView imvMissionPatch;

        MyViewHolder(View view) {
            super(view);
            txvMissionName = view.findViewById(R.id.txvMissionName);
            txvMissionDate = view.findViewById(R.id.txvMissionDate);
            txvRocketName = view.findViewById(R.id.txvRocketName);
            imvMissionPatch = view.findViewById(R.id.imvMissionPatch);
        }

        void bindTo(PastLaunch pastLaunch) {

            txvMissionName.setText(pastLaunch.getMissionName());
            txvMissionDate.setText(AppUtils.convertDateFromUTCtoIST(pastLaunch.getLaunchDateUtc()));
            txvRocketName.setText(pastLaunch.getRocketId().getRocketName());
            loadImage(pastLaunch.getLinks().getMissionPatch());

            itemView.setOnClickListener(v -> missionClickListener.onMissionItemClick(pastLaunch.getFlightNumber()));
        }

        private void loadImage(String url) {
            Glide.with(context)
                    .load(url)
                    .apply(new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.ic_placeholder))
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            handler.post(() -> loadImage(url));
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(imvMissionPatch);
        }
    }
}
