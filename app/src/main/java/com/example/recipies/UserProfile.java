package com.example.recipies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {
    TextView username,firstname,lastname;
    String getintentvalue;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        setparams();
        Intent getin=getIntent();
        getintentvalue=getin.getStringExtra("Username");
        getData();
    }

    void setparams()
    {
        username=findViewById(R.id.uname);
        firstname=findViewById(R.id.fname);
        lastname=findViewById(R.id.lname);
    }

    void getData() {
        myRef= FirebaseDatabase.getInstance().getReference("Users/"+getintentvalue);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User value = dataSnapshot.getValue(User.class);
                String fname = value.getFirstname();
                String lname = value.getLastname();
                username.setText(getintentvalue);
                firstname.setText(fname);
                lastname.setText(lname);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}