package com.almaz.myapp1;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;



public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    Context context;

    public interface OnItemClickListener {
        void onItemClick(Result item);
    }

    private List<Result> mResults;
    private OnItemClickListener listener;

    public PostsAdapter(List<Result> mResults, OnItemClickListener listener) {
        this.mResults = mResults;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)  {
        holder.bind(mResults.get(position), listener);
    }

    @Override
    public int getItemCount() {
        if (mResults == null){
            return 0;
        }
        return mResults.size();
    }

    //метод для изменения списка
    public void changeDataSet(List<Result> results){
        mResults.clear();
        mResults.addAll(results);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView result;
        ImageView imagePos;

        public ViewHolder(View itemView) {
            super(itemView);

            result = (TextView) itemView.findViewById(R.id.item_post);
            imagePos = (ImageView) itemView.findViewById(R.id.imageView);
        }

        public void bind(final Result item, final OnItemClickListener listener) {
            result.setText(Html.fromHtml(item.getTitle()));
            Picasso.with(context).load(swipe(item.getPosterPath())).into(imagePos);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);

                }
            });
        }

        public String swipe(String a){
            return String.format("https://image.tmdb.org/t/p/w185_and_h278_bestv2%s",a); //Конкатенация url постера фильма
        }
    }

}
