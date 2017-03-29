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
import com.vpaliy.domain.model.BookModel;
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
public class BookAdapter extends BaseAdapter{

    private static final float SCALE_F=0.75f;

    @NonNull
    private List<BookModel> data;

    @NonNull
    private LayoutInflater inflater;

    private boolean isClicked;
    private Bus eventBus;

    public BookAdapter(@NonNull Context context, @NonNull List<BookModel> data,
                       @NonNull Bus eventBus, @NonNull MultiMode mode) {
        super(mode,true);
        this.inflater=LayoutInflater.from(context);
        this.data=data;
        this.eventBus=eventBus;

    }

    public BookAdapter(@NonNull Context context, @NonNull List<BookModel> data,
                       @NonNull Bus eventBus, @NonNull MultiMode mode, @NonNull Bundle savedState){
        super(mode,true,savedState);
        this.inflater=LayoutInflater.from(context);
        this.data=data;
        this.eventBus=eventBus;
    }
    public class BookHolder extends BaseViewHolder {

        @BindView(R.id.icon) ImageView image;
        @BindView(R.id.author) TextView author;
        @BindView(R.id.title) TextView title;

        public BookHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //go ahead and show the details
            if(!isMultiModeActivated()) {
                //only once can be clicked
                if (!isClicked) {
                    isClicked = true;
                    BookModel model = data.get(getAdapterPosition());
                    Bundle args = new Bundle();
                    args.putInt(Constant.ID, model.getID());
                    if (Permission.checkForVersion(Build.VERSION_CODES.LOLLIPOP)) {
                        args.putString(Constant.TRANSITION_NAME, image.getTransitionName());
                    }
                    eventBus.post(new InternalAction<>(TransitionWrapper.build(args, image), -1));
                }
            }
            super.onClick(view);
        }

        public void onBindData() {
            BookModel model=data.get(getAdapterPosition());
            author.setText(model.getAuthor());
            title.setText(model.getTitle());
            if(Permission.checkForVersion(Build.VERSION_CODES.LOLLIPOP)) {
                image.setTransitionName("Book:"+Integer.toString(getAdapterPosition()));
            }
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
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public void removeAt(int index) {
        data.remove(index);
        notifyItemRemoved(index);
    }
}