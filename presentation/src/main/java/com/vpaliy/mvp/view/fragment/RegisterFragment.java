package com.vpaliy.mvp.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.vpaliy.mvp.R;
import com.vpaliy.mvp.view.utils.Constant;
import com.vpaliy.mvp.view.wrapper.InputWrapper;

import javax.inject.Inject;

import static butterknife.ButterKnife.findById;

public class RegisterFragment extends Fragment {

    private  InputWrapper inputWrapper;

    @Inject
    protected Bus bus;

    public static RegisterFragment newInstance(@NonNull InputWrapper wrapper) {
        RegisterFragment fragment=new RegisterFragment();
        Bundle args=new Bundle();
        args.putParcelable(Constant.INPUT_WRAPPER,wrapper);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null) {
            savedInstanceState=getArguments();
        }
        inputWrapper=savedInstanceState.getParcelable(Constant.INPUT_WRAPPER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register_input,container,false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if(view!=null) {
            Button submitButton=findById(view, R.id.submitButton);
            EditText input=findById(view,R.id.dataInput);
            TextView property=findById(view,R.id.property);
            property.setText(inputWrapper.getProperty());
            input.setText(inputWrapper.getProperty());
            submitButton.setBackgroundColor(inputWrapper.getColor());
            submitButton.setTextColor(inputWrapper.getColor());
            input.setInputType(inputWrapper.getInputType());
            //notify the presenter
            submitButton.setOnClickListener(v->{});

        }
    }
}
