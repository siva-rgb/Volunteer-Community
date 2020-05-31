package com.example.voluenteertask.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
//import com.example.voluenteertask.Fragment.Model.Notification;
import com.example.voluenteertask.Fragment.Model.Post;
import com.example.voluenteertask.Fragment.Model.User;
import com.example.voluenteertask.Fragment.PostDetailsFragment;
import com.example.voluenteertask.Fragment.ProfileFragment;
import com.example.voluenteertask.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

//public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
//
//    private Context mContext;
//    private List<Notification> mNotifications;
//
//    public NotificationAdapter(Context mContext, List<Notification> mNotifications) {
//        this.mContext = mContext;
//        this.mNotifications = mNotifications;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(mContext).inflate(R.layout.notification_item, parent, false);
//
//        return new NotificationAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//
//        final Notification notification = mNotifications.get(position);
//
//        getUserInfo(holder.imageProfile, holder.username, notification.getUserid());
//        holder.comment.setText(notification.getText());
//
//        if (notification.isPost()) {
//            holder.postImage.setVisibility(View.VISIBLE);
//            getPostImage(holder.postImage, notification.getPostid());
//        } else {
//            holder.postImage.setVisibility(View.GONE);
//        }
//
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (notification.isPost()) {
//                    mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
//                            .edit().putString("postid", notification.getPostid()).apply();
//
//                    ((FragmentActivity)mContext).getSupportFragmentManager()
//                            .beginTransaction().replace(R.id.fragment_container, new PostDetailsFragment()).commit();
//                } else {
//                    mContext.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
//                            .edit().putString("profileid", notification.getUserid()).apply();
//
//                    ((FragmentActivity)mContext).getSupportFragmentManager()
//                            .beginTransaction().replace(R.id.fragment_container, new ProfileFragment()).commit();
//                }
//            }
//        });
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return mNotifications.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder{
//
//        public ImageView imageProfile;
//        public ImageView postImage;
//        public TextView username;
//        public TextView comment;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            imageProfile = itemView.findViewById(R.id.image_profile);
//            postImage = itemView.findViewById(R.id.post_image);
//            username = itemView.findViewById(R.id.username);
//            comment = itemView.findViewById(R.id.comment);
//        }
//    }
//
//    private void getUserInfo(final ImageView imageView, final TextView username, final String  publisher)
//    {
//        FirebaseDatabase.getInstance().getReference().child("Users").child(publisher).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
//                if (user.getImageurl().equals("default")) {
//                    imageView.setImageResource(R.mipmap.ic_launcher);
//                } else {
//                    Picasso.with(mContext).load(user.getImageurl()).into(imageView);
//                }
//                username.setText(user.getUsername());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private void getPostImage(final ImageView imageView, final String postId) {
//        FirebaseDatabase.getInstance().getReference().child("posts").child(postId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Post post = dataSnapshot.getValue(Post.class);
//                Picasso.with(mContext).load(post.getPostimage()).placeholder(R.mipmap.ic_launcher).into(imageView);
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//}
