package com.vpaliy.mvp.view.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.vpaliy.domain.model.UserModel;

import java.util.List;

@SuppressWarnings("WeakerAccess")
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    @NonNull
    private List<UserModel> data;

    @NonNull
    private Context context;

    public UserAdapter(@NonNull Context context,@NonNull List<UserModel> data) {
        this.context=context;
        this.data=data;
    }


    public class UserHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

        public UserHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
           // Action<UserModel> action=new Action<>(data.get(getAdapterPosition()));

        }

        public void onBindData() {

        }
    }

    //TODO apply filter
    public void appendData(@NonNull List<UserModel> users) {
        if(!users.isEmpty()) {
            data.addAll(users);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {

    }
}
