package com.vpaliy.mvp.view.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.vpaliy.mvp.App;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.di.component.DaggerFragmentComponent;
import com.vpaliy.mvp.di.module.PresenterModule;
import com.vpaliy.mvp.mvp.contract.RegisterUserContract;
import com.vpaliy.mvp.view.adapter.RegisterUserAdapter;
import com.vpaliy.mvp.view.utils.eventBus.InternalAction;
import com.vpaliy.mvp.view.utils.snackbarUtils.SnackbarWrapper;
import com.vpaliy.mvp.view.view.LockableSlider;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

import static com.vpaliy.mvp.mvp.contract.RegisterUserContract.Presenter;
import static com.vpaliy.mvp.mvp.contract.RegisterUserContract.VerifyInput;

public class RegisterUserFragment extends Fragment
        implements RegisterUserContract.View{

    private Presenter presenter;
    private RegisterUserAdapter adapter;

    @BindView(R.id.slider)
    protected LockableSlider slider;

    @BindView(R.id.indicator)
    protected CircleIndicator indicator;

    @Inject
    protected Bus eventBus;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        presenter.onAttachView(this);
        View view=inflater.inflate(R.layout.fragment_register,container,false);
        ButterKnife.bind(this,view);
        slider=ButterKnife.findById(view,R.id.slider);
        indicator=ButterKnife.findById(view,R.id.indicator);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view!=null) {
            adapter=new RegisterUserAdapter(getFragmentManager());
            slider.setAdapter(adapter);
            indicator.setViewPager(slider);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        eventBus.register(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        eventBus.unregister(this);
    }

    @Override
    public void showInputError(String message) {
        View root=getView();
        if(root!=null) {
            ObjectAnimator shakeAnimation=ObjectAnimator.ofFloat(root,View.TRANSLATION_X,-25f,25f);
            shakeAnimation.setDuration(100);
            shakeAnimation.setRepeatCount(2);
            shakeAnimation.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    root.setTranslationY(0f);
                }
            });
            shakeAnimation.start();
            SnackbarWrapper
                    .start(root,message,2000)
                    .show();
        }
    }

    @Subscribe
    public void onUserInput(@NonNull InternalAction<String> action) {
        presenter.verify(VerifyInput.verify(action.getActionCode(),action.getData()));
    }

    @Override
    public void proceed() {
        slider.next();
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull Presenter presenter) {
        this.presenter=presenter;
    }

}
