package kg.geektech.newsapp40.ui.autehentication.ui.login;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentPhoneNumberBinding;


public class PhoneNumberFragment extends Fragment {
    FragmentPhoneNumberBinding binding;
    FirebaseAuth firebaseAuth;
    private String mVerificationId;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    Button btn;
    EditText inputNum, codeInput;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPhoneNumberBinding.inflate(LayoutInflater.from(requireContext()), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = view.findViewById(R.id.btn_continue);
        inputNum = view.findViewById(R.id.phoneEt);
        codeInput = view.findViewById(R.id.codeEt);
        firebaseAuth = FirebaseAuth.getInstance();
        btn.setOnClickListener(v -> {
            String phone = inputNum.getText().toString().trim();
            if (TextUtils.isEmpty(phone)) {
                inputNum.setError("Error");
            }else {
                register(phone);
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
             signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.
                    ForceResendingToken forceResendingToken) {
                super.onCodeSent(verificationId, forceResendingToken);
                mVerificationId = verificationId;
             //получить код
            }
        };
    }

    public void register(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(phoneNumber)              // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity((Activity) requireContext())  // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    public void checkingSmsCode(String smsCode) {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(smsCode)              // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity((Activity) requireContext())  // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
        String code = codeInput.getText().toString().trim();
        if (TextUtils.isEmpty(code)) {
            codeInput.setError("Error");
            return;
        }
        else {
            register(code);
            NavController navController = Navigation.findNavController(requireActivity(),R.id.phoneNumberFragment);
            navController.navigate(R.id.boardFragment);
       }

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener((Executor) this, task -> {
                    if (task.isSuccessful()) {
                        String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
                       // FirebaseUser phone = task.getResult().getUser();
                        NavController navController = Navigation.findNavController(requireActivity(),R.id.phoneNumberFragment);
                        navController.navigate(R.id.boardFragment);
                        // Update UI
                    }else {
                        Toast.makeText(getContext(),"Error",Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
