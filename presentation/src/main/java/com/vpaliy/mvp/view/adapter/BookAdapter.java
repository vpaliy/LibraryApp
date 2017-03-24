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
import com.vpaliy.domain.model.BookModel;
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
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder>{

    @NonNull
    private List<BookModel> data;

    @NonNull
    private LayoutInflater inflater;

    private boolean isClicked;
    private Bus eventBus;

    public BookAdapter(@NonNull Context context,@NonNull List<BookModel> data, Bus eventBus) {
        this.inflater=LayoutInflater.from(context);
        this.data=data;
        this.eventBus=eventBus;

    }

    public class BookHolder extends RecyclerView.ViewHolder
                    implements View.OnClickListener {

        @BindView(R.id.icon) ImageView image;
        @BindView(R.id.author) TextView author;
        @BindView(R.id.title) TextView title;

        public BookHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //only once can be clicked
            if(!isClicked) {
                isClicked=true;
                BookModel model=data.get(getAdapterPosition());
                Bundle args=new Bundle();
                args.putString(Constant.ID,model.getID());
                if(Permission.checkForVersion(Build.VERSION_CODES.LOLLIPOP)) {
                    args.putString(Constant.TRANSITION_NAME, image.getTransitionName());
                }
                eventBus.post(new InternalAction<>(TransitionWrapper.build(args,image),-1));
            }
        }

        public void onBindData() {
            BookModel model=data.get(getAdapterPosition());
            author.setText(model.getAuthor());
            title.setText(model.getTitle());
            if(Permission.checkForVersion(Build.VERSION_CODES.LOLLIPOP)) {
                image.setTransitionName("Book:"+Integer.toString(getAdapterPosition()));
            }
        }
    }

    //TODO apply filter
    public void appendData(@NonNull List<BookModel> books) {
        if(!books.isEmpty()) {
            data.addAll(books);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.adapter_user_item,parent,false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int position) {
        holder.onBindData();
    }
}