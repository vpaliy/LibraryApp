package com.vpaliy.mvp.view.adapter;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.view.utils.Constant;
import com.vpaliy.mvp.view.utils.Permission;
import com.vpaliy.mvp.view.utils.eventBus.InternalAction;
import com.vpaliy.mvp.view.wrapper.TransitionWrapper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder>{

    private static final String TAG=UserAdapter.class.getSimpleName();

    @NonNull
    private List<UserModel> data;

    private LayoutInflater inflater;

    private Bus eventBus;
    private boolean isClicked;

    public UserAdapter(@NonNull Context context,@NonNull List<UserModel> data, @NonNull Bus eventBus) {
        this.inflater=LayoutInflater.from(context);
        this.data=data;
        this.eventBus=eventBus;
        this.isClicked=false;
    }


    public class UserHolder extends RecyclerView.ViewHolder
                implements View.OnClickListener {

        @BindView(R.id.icon) ImageView image;
        @BindView(R.id.firstName) TextView firstName;
        @BindView(R.id.lastName) TextView lastName;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //only once can be clicked
            if(!isClicked) {
                isClicked=true;
                UserModel model=data.get(getAdapterPosition());
                Bundle args=new Bundle();
                args.putString(Constant.ID,model.getID());
                if(Permission.checkForVersion(Build.VERSION_CODES.LOLLIPOP)) {
                    args.putString(Constant.TRANSITION_NAME, image.getTransitionName());
                }
                eventBus.post(new InternalAction<>(TransitionWrapper.build(args,image),-1));
            }
        }

        public void onBindData() {
            UserModel user=data.get(getAdapterPosition());
            firstName.setText(user.getFirstName());
            lastName.setText(user.getLastName());
            if(Permission.checkForVersion(Build.VERSION_CODES.LOLLIPOP)) {
                image.setTransitionName("User:"+Integer.toString(getAdapterPosition()));
            }
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
