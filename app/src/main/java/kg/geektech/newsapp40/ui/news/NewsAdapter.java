package kg.geektech.newsapp40.ui.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.ItemNewsBinding;
import kg.geektech.newsapp40.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ContViewHolder> {
    private ArrayList<News> list;
    private ItemNewsBinding binding;

    public NewsAdapter(){
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public ContViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemNewsBinding.inflate
                (LayoutInflater.from(parent.getContext()), parent, false);
        return new ContViewHolder(binding, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ContViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    public void addItem(News news) {
        list.add(0,news);
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void  addItems(List<News> newsList){
        list = (ArrayList<News> )newsList;
//        newsList.addAll(newsList);
        notifyDataSetChanged();
    }

    public static class ContViewHolder extends RecyclerView.ViewHolder {
         ItemNewsBinding binding;
         private Context context;

        public ContViewHolder(@NonNull ItemNewsBinding itemView, Context context) {
            super(itemView.getRoot());
            binding = itemView;
            this.context = context;

        }

        public void bind(News news) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd HH:mm:ss");
            String currentTime = simpleDateFormat.format(news.getCreatedAt());
            binding.textView.setText(news.getTitle());
            binding.tvTime.setText(currentTime);
            if (getAdapterPosition() %2  ==0){
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
            }else{
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
            }

        }
    }
}
