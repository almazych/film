package com.almaz.myapp1;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    Context context;
    Film film;



    private List<Result> mResults;

    public PostsAdapter(List<Result> mResults) {
        this.mResults = mResults;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)  {
        Result result = mResults.get(position);

        holder.result.setText(Html.fromHtml(result.getTitle()));

        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w185_and_h278_bestv2" + result.getPosterPath())
                .into(holder.imagePos);


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

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView result;
        ImageView imagePos;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            result = (TextView) itemView.findViewById(R.id.item_post);
            imagePos = (ImageView) itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

                App.getApi().getFilm(mResults.get(position).getId(), MainActivity.API_KEY, "ru-US").enqueue(new Callback<Film>() {
                    @Override
                    public void onResponse(Call<Film> call, Response<Film> response) {
                        if (response.isSuccessful()) {
                            Log.d("TAG", "Status Code = " + response.code());
                            film = response.body();

                        } else {
                            try {
                                Log.d("TAG", response.errorBody().string());
                            } catch (IOException ioe) {
                                Log.d("TAG", ioe.getLocalizedMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Film> call, Throwable t) {
                    }
                });

        }
    }

}
