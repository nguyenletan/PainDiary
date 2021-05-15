package com.example.paindiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.paindiary.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth auth;

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser == null) {
            //auth.signOut();
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        auth = FirebaseAuth.getInstance();
        View view = binding.getRoot();
        setContentView(view);

        setSupportActionBar(binding.appBar.toolbar);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home_page,
                R.id.nav_pain_entry,
                R.id.nav_daily_record,
                R.id.nav_report,
                R.id.nav_map)
                //to display the Navigation button as a drawer symbol,not being shown as an Up button
                .setOpenableLayout(binding.drawerLayout)
                .build();
        FragmentManager fragmentManager = getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentById(R.id.nav_host_fragment);

        NavController navController = navHostFragment.getNavController();

        //Sets up a NavigationView for use with a NavController.
        NavigationUI.setupWithNavController(binding.navView, navController);
        //Sets up a Toolbar for use with a NavController.
        NavigationUI.setupWithNavController(binding.appBar.toolbar, navController, mAppBarConfiguration);

    }
}