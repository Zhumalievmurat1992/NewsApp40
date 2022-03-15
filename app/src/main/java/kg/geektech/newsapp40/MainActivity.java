package kg.geektech.newsapp40;

import android.content.ClipData;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import kg.geektech.newsapp40.databinding.ActivityMainBinding;
import kg.geektech.newsapp40.ui.autehentication.ui.login.PhoneNumberFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Prefs prefs;
    //private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // firebaseAuth = FirebaseAuth.getInstance();
        prefs = new Prefs(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,
                R.id.navigation_person).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

       if (!prefs.isBoardShown()){ navController.navigate(R.id.boardFragment);
           navController.navigate(R.id.phoneNumberFragment);
       }


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                if (navDestination.getId() == R.id.boardFragment) {
                    navView.setVisibility(View.GONE);
                    getSupportActionBar().hide();
                } else {
                    navView.setVisibility(View.VISIBLE);
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.option_menu)
            {prefs.clear();}
                Toast.makeText(MainActivity.this, "Очищено", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}