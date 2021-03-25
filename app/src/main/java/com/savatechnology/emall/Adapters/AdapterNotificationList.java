package com.savatechnology.emall.Adapters;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.savatechnology.emall.R;
import com.savatechnology.emall.Models.NotificationList;
import java.util.List;

public class AdapterNotificationList extends RecyclerView.Adapter<AdapterNotificationList.MyViewHolder> {
    private List<NotificationList> lists;
    public AdapterNotificationList(List<NotificationList> lists) {
        this.lists = lists;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int image = lists.get(position).getimgNotification();
        String notification_title = lists.get(position).gettvNotificationTitle();
        String notification_date = lists.get(position).gettvDate();
        String notification_description = lists.get(position).gettvNotificationDescription();


        holder.imgNotification.setImageResource(image);
        holder.tvNotificationTitle.setText(notification_title);
        holder.tvDate.setText(notification_date);
        holder.tvNotificationDescription.setText(notification_description);

    }
    @Override
    public int getItemCount() {
        return lists.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgNotification;
        TextView tvNotificationTitle;
        TextView tvDate;
        TextView tvNotificationDescription;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNotification = itemView.findViewById(R.id.imgNotification);
            tvNotificationTitle = (TextView)itemView.findViewById(R.id.tvNotficationTitle);
            tvDate =(TextView) itemView.findViewById(R.id.tvDate);
            tvNotificationDescription =(TextView) itemView.findViewById(R.id.tvNotificationDescription);


        }
    }
}