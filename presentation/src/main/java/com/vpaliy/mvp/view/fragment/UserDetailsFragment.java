package com.vpaliy.mvp.view.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.vpaliy.domain.model.UserModel;
import com.vpaliy.mvp.App;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.di.component.DaggerFragmentComponent;
import com.vpaliy.mvp.di.module.PresenterModule;
import com.vpaliy.mvp.mvp.contract.UserDetailsContract;
import com.vpaliy.mvp.view.utils.Constant;
import com.vpaliy.mvp.view.utils.Permission;
import com.vpaliy.mvp.view.view.SquareImage;

import butterknife.ButterKnife;

import javax.inject.Inject;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import butterknife.BindView;
import butterknife.Unbinder;

import android.annotation.TargetApi;

import static com.vpaliy.mvp.mvp.contract.UserDetailsContract.Presenter;

public class UserDetailsFragment extends Fragment
    implements UserDetailsContract.View{

    private Presenter presenter;
    private int ID;

    @BindView(R.id.userImage)
    protected SquareImage userImage;

    private Unbinder unbinder;

    public static UserDetailsFragment newInstance(int ID) {
        Bundle args=new Bundle();
        args.putInt(Constant.ID,ID);
        UserDetailsFragment fragment=new UserDetailsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if(savedInstanceState==null) {
            savedInstanceState=getArguments();
        }
        ID=savedInstanceState.getInt(Constant.ID);
        initializeInjection();
    }

    private void initializeInjection() {
        DaggerFragmentComponent.builder()
                .applicationComponent(App.app().provideAppComponent())
                .presenterModule(new PresenterModule())
                .build().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.fragment_user_details,container,false);
        unbinder=ButterKnife.bind(this,root);
        return root;
    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.start(ID);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    //should be invoked only for version greater than L
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void startPostponedTransition(){
        userImage.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                userImage.getViewTreeObserver().removeOnPreDrawListener(this);
                getActivity().startPostponedEnterTransition();
                return true;
            }
        });
    }

    @Override
    public void showUser(@NonNull UserModel model) {
        View root=getView();
        if(root!=null) {
            byte[] image=model.getUserImage();
            DrawableRequestBuilder<?> builder;
            if(image==null){
                builder=Glide.with(this)
                        .fromResource()
                        .load(R.mipmap.ic_launcher);

            }else{
                builder=Glide.with(this)
                        .fromBytes()
                        .load(image);
            }
            builder.into(new ImageViewTarget<GlideDrawable>(userImage) {
                @Override
                protected void setResource(GlideDrawable resource) {
                    userImage.setImageDrawable(resource);
                    if(Permission.checkForVersion(Build.VERSION_CODES.LOLLIPOP)){
                        startPostponedTransition();
                    }
                }
            });
        }
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull Presenter presenter) {
        this.presenter=presenter;
        presenter.onAttachView(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(Constant.ID,ID);
        super.onSaveInstanceState(outState);
    }
}
