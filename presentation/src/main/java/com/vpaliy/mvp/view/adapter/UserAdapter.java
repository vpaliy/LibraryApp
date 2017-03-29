package com.vpaliy.mvp.view.adapter;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.otto.Bus;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.multiplechoice.BaseAdapter;
import com.vpaliy.multiplechoice.MultiMode;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.view.utils.Constant;
import com.vpaliy.mvp.view.utils.Permission;
import com.vpaliy.mvp.view.utils.animationUtils.ScaleBuilder;
import com.vpaliy.mvp.view.utils.eventBus.InternalAction;
import com.vpaliy.mvp.view.wrapper.TransitionWrapper;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class UserAdapter extends BaseAdapter{

    private static final String TAG=UserAdapter.class.getSimpleName();
    private static final float SCALE_F=0.95f;

    @NonNull
    private List<UserModel> data;

    private LayoutInflater inflater;

    private Bus eventBus;
    private boolean isClicked;


    public UserAdapter(@NonNull Context context, @NonNull List<UserModel> data,
                       @NonNull Bus eventBus, @NonNull MultiMode mode) {
        super(mode,true);
        this.inflater=LayoutInflater.from(context);
        this.data=data;
        this.eventBus=eventBus;
        this.isClicked=false;
    }

    //Gets called after an activity has been recreated
    public UserAdapter(@NonNull Context context, @NonNull List<UserModel> data,
                       @NonNull Bus eventBus, @NonNull MultiMode mode,
                       @NonNull Bundle savedState) {
        super(mode,true,savedState);
        this.inflater=LayoutInflater.from(context);
        this.eventBus=eventBus;
        this.data=data;
        this.eventBus=eventBus;
        this.isClicked=false;
    }


    public class UserHolder extends BaseViewHolder {

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

            //show the details of the user on click
            if(!isMultiModeActivated()) {
                //the item can be clicked only once
                if (!isClicked) {
                    isClicked = true;
                    UserModel model = data.get(getAdapterPosition());
                    Bundle args = new Bundle();
                    args.putInt(Constant.ID, model.getID());
                    if (Permission.checkForVersion(Build.VERSION_CODES.LOLLIPOP)) {
                        args.putString(Constant.TRANSITION_NAME, image.getTransitionName());
                    }
                    eventBus.post(new InternalAction<>(TransitionWrapper.build(args, image), -1));
                }
            }
            super.onClick(v);
        }

        @Override
        public void updateBackground() {
            //will do later
        }

        @Override
        public void enterState() {
            super.enterState();
            ScaleBuilder.start(itemView,SCALE_F)
                    .accelerate()
                    .execute();
        }

        @Override
        public void exitState() {
            super.exitState();
            if (itemView.getScaleY() < 1.f) {
                ScaleBuilder.start(itemView,1.f)
                        .accelerate()
                        .execute();
            }
        }

        @Override
        public void animatedState() {
            itemView.setScaleX(SCALE_F);
            itemView.setScaleY(SCALE_F);
        }

        @Override
        public void defaultState() {
            if(itemView.getScaleX()<1f) {
                itemView.setScaleX(1.f);
                itemView.setScaleY(1.f);
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
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public void removeAt(int index) {
        data.remove(index);
        notifyItemRemoved(index);
    }


}
