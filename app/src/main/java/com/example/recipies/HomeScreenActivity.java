package com.example.recipies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class HomeScreenActivity extends AppCompatActivity {
Button profile;
private String intentstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        profile=findViewById(R.id.button_profile);
        Intent in=getIntent();
        intentstore=in.getStringExtra("Username");
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeScreenActivity.this,UserProfile.class);
                intent.putExtra("Username",intentstore);
                startActivity(intent);

            }
        });
    }


}
