package lvt.newsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import lvt.newsapp.News;
import lvt.newsapp.R;

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
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        TextView title;

        public NewsHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.news_item_des);
        }
    }
}
