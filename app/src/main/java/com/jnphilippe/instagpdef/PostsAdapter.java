package com.jnphilippe.instagpdef;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter <PostsAdapter.VideiHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public VideiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.item_posts,parent,false);
        return new VideiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideiHolder holder, int position) {
        Post post=posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class VideiHolder extends RecyclerView.ViewHolder{

        private TextView tvUsername,tvDescription;
        private ImageView ivImage;

        public VideiHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            ivImage=itemView.findViewById(R.id.ivImage);
            tvUsername=itemView.findViewById(R.id.tvUsername);
        }

        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            ParseFile image = post.getImage();
            if (image!=null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
        }
    }
}
