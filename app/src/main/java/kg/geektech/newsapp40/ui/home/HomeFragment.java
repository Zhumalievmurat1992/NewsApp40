package kg.geektech.newsapp40.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kg.geektech.newsapp40.App;
import kg.geektech.newsapp40.ui.news.NewsAdapter;
import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentHomeBinding;
import kg.geektech.newsapp40.models.News;

public class HomeFragment extends Fragment {
    private NewsAdapter homeAdapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeAdapter = new NewsAdapter();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setAdapter(homeAdapter);
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFragment();
            }
        });
        getParentFragmentManager().setFragmentResultListener("rk_news",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                    }
                });


    List<News> newsList = App.dataBase.newsDao().getAllNews();
    homeAdapter.addItems(newsList);
}

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}