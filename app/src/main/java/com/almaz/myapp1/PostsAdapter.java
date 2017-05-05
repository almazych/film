package com.almaz.myapp1;


import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private List<Result> mResults;

    public PostsAdapter(List<Result> mResults) {
        this.mResults = mResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Result result = mResults.get(position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.result.setText(Html.fromHtml(result.getTitle(), Html.FROM_HTML_MODE_LEGACY));
        } else {
            holder.result.setText(Html.fromHtml(result.getTitle()));
        }
    }

    @Override
    public int getItemCount() {
        if (mResults == null){
            return 0;
        }
        return mResults.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView result;

        public ViewHolder(View itemView) {
            super(itemView);
            result = (TextView) itemView.findViewById(R.id.item_post);
        }
    }
}
