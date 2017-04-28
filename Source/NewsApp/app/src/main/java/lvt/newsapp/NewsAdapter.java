package lvt.newsapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {


    private ArrayList<News> NewsList;
    private Context context;

    public NewsAdapter(ArrayList<News> newsList, Context mcontext) {
        NewsList = newsList;
        context = mcontext;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        bindView(holder, position);
    }

    @Override
    public int getItemCount() {
        return NewsList.size();
    }

    public void bindView(NewsHolder holder, int position) {
        holder.title.setText(NewsList.get(position).getTitle());
        Glide.with(context).load(NewsList.get(position).getImageUrl()).fitCenter().centerCrop().into(holder.image);
        holder.date.setText(NewsList.get(position).getDate());
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView image;
        TextView date;
        public NewsHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_item_des);
            image = (ImageView) itemView.findViewById(R.id.news_item_img);
            date = (TextView) itemView.findViewById(R.id.news_item_time);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra(DetailActivity.NEWS_URL, NewsList.get(getAdapterPosition()).getUrl());
            context.startActivity(intent);
        }
    }
}
