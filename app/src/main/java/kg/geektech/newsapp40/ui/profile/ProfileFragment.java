package kg.geektech.newsapp40.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigator;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.jar.Attributes;

import kg.geektech.newsapp40.Prefs;
import kg.geektech.newsapp40.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {
    private Uri uri;
    private Intent intent;
    private Prefs prefs;
    private FragmentProfileBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prefs = new Prefs(requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Prefs prefs = new Prefs(requireContext());
        name(prefs);
        image();
    }

    private ActivityResultLauncher<Intent> launcher = registerForActivityResult
            (new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                uri = result.getData().getData();
                                try {
                                    prefs.saveImage(String.valueOf(uri));
                                } catch (NullPointerException ignored) {
                                }
                                binding.photo.setImageURI(uri);
                            }
                        }
                    });

    private void image() {
        binding.photo.setOnClickListener(view -> {
            intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            launcher.launch(intent);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            if (prefs.getImage() != null) uri = Uri.parse(prefs.getImage());
            Glide.with(requireContext()).load(uri).circleCrop().into(binding.photo);
        } catch (NullPointerException ignored) {
            Glide.with(requireContext()).load(uri).circleCrop().into(binding.photo);
        }
    }

    private void name(Prefs prefs) {
        binding.editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                prefs.saveName(editable.toString());
            }
        });
        binding.editName.setText(prefs.getName());

    }

}