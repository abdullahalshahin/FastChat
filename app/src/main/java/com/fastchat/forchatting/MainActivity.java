package com.fastchat.forchatting;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.fastchat.forchatting.Adapters.FragmentsAdapter;
import com.fastchat.forchatting.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        auth = FirebaseAuth.getInstance();

        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.setting:
                Intent goSettings = new Intent(MainActivity.this, Settings.class);
                startActivity(goSettings);
                /*Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();*/
                break;

            case R.id.groupChat:
                Intent goGroupChat = new Intent(MainActivity.this, groupChatActivity.class);
                startActivity(goGroupChat);
                break;

            case R.id.logout:
                auth.signOut();
                Intent back_sign_in = new Intent(MainActivity.this, SignIn.class);
                startActivity(back_sign_in);
                break;
        }
        return true;
    }
}