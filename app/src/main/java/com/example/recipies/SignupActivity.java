package com.example.recipies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.text.TextUtils.isEmpty;

public class SignupActivity extends AppCompatActivity {
    TextInputLayout firstName,lastName,Username;
    Animation usernameAnim,SignupButtonAnim,firstnameanim,lastnameanim;
    Button register;
    private DatabaseReference userRef, newuserRef;
    private String firstname,lastname,username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setViewparams();
        setAnims();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();

            }
        });
    }
    void setAnims(){
        usernameAnim= AnimationUtils.loadAnimation(this,R.anim.usernameanim);
        firstnameanim= AnimationUtils.loadAnimation(this,R.anim.firstnameanim);
        lastnameanim= AnimationUtils.loadAnimation(this,R.anim.firstnameanim);
        SignupButtonAnim= AnimationUtils.loadAnimation(this,R.anim.firstnameanim);


        Username.setAnimation(usernameAnim);
        lastName.setAnimation(lastnameanim);
        firstName.setAnimation(firstnameanim);
        register.setAnimation(SignupButtonAnim);

    }
    void setViewparams(){
        Username=findViewById(R.id.username);
        firstName=findViewById(R.id.fname);
        lastName=findViewById(R.id.lname);
        register=findViewById(R.id.signup);
        //mageView=findViewById(R.id.logo_image);
    }
    void registerUser(){
        if (isEmpty(lastName.getEditText().getText().toString() ) ||isEmpty(firstName.getEditText().getText().toString()) || isEmpty(Username.getEditText().getText().toString())) {
            Toast.makeText(getApplicationContext(),"Please Fill All the Fields",Toast.LENGTH_SHORT).show();

        }
        else if ((!Username.getEditText().getText().toString().matches("[a-zA-Z]+"))||(!firstName.getEditText().getText().toString().matches("[a-zA-Z]+"))|| (!lastName.getEditText().getText().toString().matches("[a-zA-Z]+"))) {
            Toast.makeText(getApplicationContext(),"Only Alphabets are allowed",Toast.LENGTH_LONG).show();
        }

        else if(firstName.getEditText().getText().toString().length()<3 || lastName.getEditText().getText().toString().length()<3 || Username.getEditText().getText().toString().length()<3)
        {
            Toast.makeText(getApplicationContext(),"All fields must be of more than 3 Characters",Toast.LENGTH_LONG).show();
        }
        else
        {


            userRef = FirebaseDatabase.getInstance().getReference();
            userRef.child("Users").child(Username.getEditText().getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        Toast.makeText(getApplicationContext(),"Username Already Exists",Toast.LENGTH_LONG).show();
                    } else {

                        newuserRef = FirebaseDatabase.getInstance().getReference("Users/"+Username.getEditText().getText().toString());
                        firstname=firstName.getEditText().getText().toString().trim();
                        lastname=lastName.getEditText().getText().toString().trim();
                        username = Username.getEditText().getText().toString();
                        User user=new User(firstname,lastname);

                        newuserRef.setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });

                        Intent intent = new Intent(SignupActivity.this, HomeScreenActivity.class);
                        intent.putExtra("Username",username);
                        startActivity(intent);


                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
                }
            });




        }







    }






}


