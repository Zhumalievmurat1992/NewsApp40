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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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
    private ProgressBar progressBar;
    private int CurrentProgressBar = 0;
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
        progressBar = view.findViewById(R.id.progress);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                save();
                CurrentProgressBar = CurrentProgressBar +10;
                progressBar.setProgress(CurrentProgressBar);
                progressBar.setMax(100);
            }
        });
    }

    private void readToFirestore(News news) {
        FirebaseFirestore.getInstance()
                .collection("news")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()){
                    }
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void save() {
        String text = binding.editText.getText().toString();
        News news = new News(text, System.currentTimeMillis(),"Description");
        saveToFirestore(news);
        readToFirestore(news);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("news", news);
//        getParentFragmentManager().setFragmentResult("rk_news", bundle);
        App.dataBase.newsDao().insertNews(news);
    }

    private void saveToFirestore(News news) {
        FirebaseFirestore.getInstance()
                .collection("news")
                .add(news)
        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()){
                    Toast.makeText(requireContext(), "Successfully", Toast.LENGTH_SHORT).show();
                    close();
                }else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}
