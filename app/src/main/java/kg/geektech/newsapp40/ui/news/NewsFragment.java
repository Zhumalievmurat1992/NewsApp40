package kg.geektech.newsapp40.ui.news;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import kg.geektech.newsapp40.App;
import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentNewsBinding;
import kg.geektech.newsapp40.databinding.FragmentProfileBinding;
import kg.geektech.newsapp40.models.News;


public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                save();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void save() {
        String text = binding.editText.getText().toString();
        News news = new News(text, System.currentTimeMillis(),"Description");

//        Bundle bundle = new Bundle();
//        bundle.putSerializable("news", news);
//        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        App.dataBase.newsDao().insertNews(news);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}
