package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<ListItem> mListItemsList;
    private Context context;

    public MyAdapter(List<ListItem> mListItemsList, Context context) {
        this.mListItemsList = mListItemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.author.setText(mListItemsList.get(position).getAuthor());
        holder.description.setText(mListItemsList.get(position).getDescription());
        holder.title.setText(mListItemsList.get(position).getTitle());

        String urlImage = mListItemsList.get(position).getUrlToImage();

        Glide.with(context).load(urlImage).into(holder.url);

    }

    @Override
    public int getItemCount() {
        return mListItemsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView description;
        public TextView author;
        public ImageView url;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textViewTitle);
            description = itemView.findViewById(R.id.textViewDescription);
            author = itemView.findViewById(R.id.textViewAuthor);
            url = itemView.findViewById(R.id.imageUrl);

        }
    }

}
