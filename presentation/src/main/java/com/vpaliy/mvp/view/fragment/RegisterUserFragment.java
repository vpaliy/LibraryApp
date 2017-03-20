package com.vpaliy.mvp.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vpaliy.mvp.App;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.di.component.DaggerFragmentComponent;
import com.vpaliy.mvp.di.module.PresenterModule;
import com.vpaliy.mvp.mvp.contract.BookDetailsContract;
import com.vpaliy.mvp.mvp.contract.RegisterUserContract;
import com.vpaliy.mvp.view.adapter.RegisterUserAdapter;
import com.vpaliy.mvp.view.view.LockableSlider;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import rx.internal.operators.BufferUntilSubscriber;

import static com.vpaliy.mvp.mvp.contract.RegisterUserContract.Presenter;

public class RegisterUserFragment extends Fragment
        implements RegisterUserContract.View{

    private Presenter presenter;
    private RegisterUserAdapter adapter;

    @BindView(R.id.slider)
    protected LockableSlider slider;


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
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(view!=null) {
            adapter=new RegisterUserAdapter(getFragmentManager());
            slider.setAdapter(adapter);
        }
    }

    @Override
    public void showInputError(String message) {

    }

    @Override
    public void proceed() {

    }

    @Inject
    @Override
    public void attachPresenter(@NonNull Presenter presenter) {
        this.presenter=presenter;
    }

}
