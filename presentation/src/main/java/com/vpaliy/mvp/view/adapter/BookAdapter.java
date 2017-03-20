package com.vpaliy.mvp.view.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.vpaliy.domain.model.BookModel;
import com.vpaliy.mvp.view.utils.eventBus.Action;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder>{

    @NonNull
    private List<BookModel> data;

    @NonNull
    private Context context;

    public BookAdapter(@NonNull Context context,@NonNull List<BookModel> data) {
        this.context=context;
        this.data=data;
    }


    public class BookHolder extends RecyclerView.ViewHolder
                    implements View.OnClickListener {

        public BookHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            //Action<BookModel> action=new Action<>(data.get(getAdapterPosition()));

        }

        public void onBindData() {

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
        return null;
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int position) {

    }
}