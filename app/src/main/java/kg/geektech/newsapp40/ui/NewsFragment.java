package kg.geektech.newsapp40.ui;

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

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentNewsBinding;
import kg.geektech.newsapp40.databinding.FragmentProfileBinding;
import kg.geektech.newsapp40.models.News;


public class NewsFragment extends Fragment {
       private FragmentNewsBinding binding;
       TextView root;

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
        /*Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        String[] splitDate = formattedDate.split(",");
        binding.editText.setText(splitDate[0]);*/
//        Date news = Calendar.getInstance().getTime();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a",Locale.getDefault());
//        String date = simpleDateFormat.format(text);
        News news = new News(text,System.currentTimeMillis());

        Bundle bundle = new Bundle();
        bundle.putSerializable("news",news);
        getParentFragmentManager().setFragmentResult("rk_news",bundle);

        close();
        }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}
