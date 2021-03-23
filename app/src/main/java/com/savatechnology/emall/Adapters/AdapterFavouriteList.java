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
import com.savatechnology.emall.Models.FavouriteList;
import java.util.List;

public class AdapterFavouriteList extends RecyclerView.Adapter<AdapterFavouriteList.MyViewHolder> {
    private List<FavouriteList> lists;
    public AdapterFavouriteList(List<FavouriteList> lists) {
        this.lists = lists;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourites, parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int resource = lists.get(position).getImageview1();
        String name = lists.get(position).getTextview1();
        String msg = lists.get(position).getTextview3();
        String time = lists.get(position).getTextview2();
        String line = lists.get(position).getDivider();

        holder.imageView.setImageResource(resource);
        holder.textView.setText(name);
        holder.textView3.setText(msg);
        holder.textView2.setText(time);
        holder.divider.setText(line);
    }
    @Override
    public int getItemCount() {
        return lists.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
         ImageView imageView;
         TextView textView;
         TextView textView2;
         TextView textView3;
         TextView divider;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview1);
            textView = (TextView)itemView.findViewById(R.id.textview);
            textView2 =(TextView) itemView.findViewById(R.id.textview2);
            textView3 = itemView.findViewById(R.id.textview3);
            divider = itemView.findViewById(R.id.divider);
        }
    }
}