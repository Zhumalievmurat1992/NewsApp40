package kg.geektech.newsapp40.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import kg.geektech.newsapp40.App;
import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentNotificationsBinding;
import kg.geektech.newsapp40.models.News;
import kg.geektech.newsapp40.ui.news.NewsAdapter;

public class NotificationsFragment extends Fragment {
    private FragmentNotificationsBinding binding;
    private NewsAdapter notAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notAdapter = new NewsAdapter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setAdapter(notAdapter);
        List<News> newsList = App.dataBase.newsDao().getAllNews();
        notAdapter.addItems(newsList);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}