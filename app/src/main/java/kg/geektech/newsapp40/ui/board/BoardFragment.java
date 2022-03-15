package kg.geektech.newsapp40.ui.board;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import kg.geektech.newsapp40.Prefs;
import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentBoardBinding;



public class BoardFragment extends Fragment {
    FragmentBoardBinding binding;
    SpringDotsIndicator dotsIndicator;
    FirebaseAuth firebaseAuth;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentBoardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 viewPager2 = view.findViewById(R.id.ScreenViewPager);
        dotsIndicator = view.findViewById(R.id.dots_indicator);
        BoardAdapter adapter = new BoardAdapter();
        viewPager2.setAdapter(adapter);
        dotsIndicator.setViewPager2(viewPager2);
        binding.tvSkip.setOnClickListener(view12 -> close());
        binding.btnGetStarted.setOnClickListener(view1 -> close());

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (position == 2) {
                    binding.tvSkip.setVisibility(View.INVISIBLE);
                } else {
                    binding.tvSkip.setVisibility(View.VISIBLE);
                }
                if (position == 2) {
                    binding.btnGetStarted.setVisibility(View.VISIBLE);
                } else {
                    binding.btnGetStarted.setVisibility(View.INVISIBLE);
                }
            }
        });
        requireActivity().getOnBackPressedDispatcher()
                .addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        requireActivity().finish();
                    }
                });
    }

    private void close() {
        Prefs prefs = new Prefs(requireContext());
        prefs.saveBoardState();
        //prefs.isBoardShown();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }
}