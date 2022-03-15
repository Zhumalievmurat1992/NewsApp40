package kg.geektech.newsapp40.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentBoardBinding;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.ViewHolder> {

    private String[] titles = new String[]{"salam", "privet", "hello"};
    private String[] desc = new String[]{" Добро пожаловать!!! ", " страница для ознакомление ", "всего"};
    Integer[] img = new Integer[]{R.drawable.img3, R.drawable.img, R.drawable.img_1};

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_board, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private TextView textDesc;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textDesc = itemView.findViewById(R.id.textDesc);
            imageView = itemView.findViewById(R.id.imageView);

        }

        public void bind(int position) {
            textTitle.setText(titles[position]);
            textDesc.setText(desc[position]);
            imageView.setImageResource(img[position]);

        }
    }
}
