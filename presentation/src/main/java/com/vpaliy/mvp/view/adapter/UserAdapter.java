package com.vpaliy.mvp.view.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private static final String TAG=UserAdapter.class.getSimpleName();

    @NonNull
    private List<UserModel> data;

    private LayoutInflater inflater;

    public UserAdapter(@NonNull Context context,@NonNull List<UserModel> data) {
        this.inflater=LayoutInflater.from(context);
        this.data=data;
    }


    public class UserHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

        @BindView(R.id.firstName) TextView firstName;
        @BindView(R.id.lastName) TextView lastName;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View v) {

        }

        public void onBindData() {
            UserModel user=data.get(getAdapterPosition());
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
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
        View view=inflater.inflate(R.layout.adapter_user_item,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
        holder.onBindData();
    }
}
