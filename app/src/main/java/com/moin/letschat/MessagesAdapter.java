package com.moin.letschat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class MessagesAdapter extends FirebaseRecyclerAdapter<Message, RecyclerView.ViewHolder> {

    private static int TYPE_SELF = 1;
    private static int TYPE_OTHER = 2;

    public MessagesAdapter(@NonNull FirebaseRecyclerOptions<Message> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, @NonNull Message message) {

        if (getItemViewType(position) == TYPE_SELF) {
            ((SelfMessagesViewHolder) holder).setViewHolder(message);
        } else {
            ((MessagesViewHolder) holder).setViewHolder(message);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_SELF) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_self, parent, false);
            return new SelfMessagesViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message, parent, false);
            return new MessagesViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Message message = getItem(position);
        if (message.getUid().equals(MainActivity.mUid)) return TYPE_SELF;
        else return TYPE_OTHER;
    }

    static class MessagesViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView messageTextView;
        ImageView photoImageView;

        public MessagesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
        }

        public void setViewHolder(Message message) {
            nameTextView.setText(message.getName());
            if (message.getPhotoUrl()!=null) {
                messageTextView.setVisibility(View.GONE);
                photoImageView.setVisibility(View.VISIBLE);
                Glide.with(photoImageView.getContext())
                        .load(message.getPhotoUrl())
                        .into(photoImageView);
            } else {
                messageTextView.setVisibility(View.VISIBLE);
                photoImageView.setVisibility(View.GONE);
                messageTextView.setText(message.getText());
            }
        }
    }

    static class SelfMessagesViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView messageTextView;
        ImageView photoImageView;

        public SelfMessagesViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            photoImageView = itemView.findViewById(R.id.photoImageView);
        }

        public void setViewHolder(Message message) {
            nameTextView.setText(message.getName());
            if (message.getPhotoUrl()!=null) {
                messageTextView.setVisibility(View.GONE);
                photoImageView.setVisibility(View.VISIBLE);
                Glide.with(photoImageView.getContext())
                        .load(message.getPhotoUrl())
                        .into(photoImageView);
            } else {
                messageTextView.setVisibility(View.VISIBLE);
                photoImageView.setVisibility(View.GONE);
                messageTextView.setText(message.getText());
            }
        }
    }
}
