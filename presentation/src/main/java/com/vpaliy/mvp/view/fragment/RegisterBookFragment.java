package com.vpaliy.mvp.view.fragment;

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
import com.vpaliy.mvp.mvp.contract.RegisterBookContract;
import com.vpaliy.mvp.view.adapter.RegisterBookAdapter;
import com.vpaliy.mvp.view.utils.eventBus.InternalAction;
import com.vpaliy.mvp.view.view.LockableSlider;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.circleindicator.CircleIndicator;

import static com.vpaliy.mvp.mvp.contract.RegisterBookContract.Presenter;

public class RegisterBookFragment extends Fragment
        implements RegisterBookContract.View{

    private Presenter presenter;
    private RegisterBookAdapter adapter;

    @BindView(R.id.slider)
    protected LockableSlider slider;

    @BindView(R.id.indicator)
    protected CircleIndicator indicator;

    @Inject
    private Bus eventBus;

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
        return inflater.inflate(R.layout.fragment_register,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view!=null) {
            ButterKnife.bind(this,view);
            adapter=new RegisterBookAdapter(getFragmentManager());
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
    public void showInputError(@NonNull String message) {

    }

    @Override
    public void proceed() {
        slider.next();
    }

    @Subscribe
    public void onUserInput(@NonNull InternalAction<Void> action) {

    }

    @Inject
    @Override
    public void attachPresenter(@NonNull Presenter presenter) {
        this.presenter=presenter;
    }
}
