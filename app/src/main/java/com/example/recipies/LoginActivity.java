package com.example.recipies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.text.TextUtils.isEmpty;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout username;
    TextView wel,sigin_to_continue,signup;
    ImageView logo;
    Button Login;
    private DatabaseReference loginuserref;
    Animation usernameAnim,LoginButtonAnim,textviewAnim,welcome,NewUserAnim;

    public static final int STARTUP_DELAY = 300;
    public static final int ANIM_ITEM_DURATION = 1000;
    public static final int EDITTEXT_DELAY = 300;
    public static final int BUTTON_DELAY = 500;
    public static final int VIEW_DELAY = 400;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setparams();
        setAnimation();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEmpty(username.getEditText().getText().toString()))
                {
                    username.setError("Username Required");
                }
                else {

                    accessuser();
                }
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
    }
    void setAnimation(){
        //usernameAnim= AnimationUtils.loadAnimation(this,R.anim.usernameanim);
        LoginButtonAnim=AnimationUtils.loadAnimation(this,R.anim.loginbuttonanim);
       // textviewAnim=AnimationUtils.loadAnimation(this,R.anim.textviewanim);
        //welcome=AnimationUtils.loadAnimation(this,R.anim.usernameanim);
        //NewUserAnim=AnimationUtils.loadAnimation(this,R.anim.newuseranim);

     /*   ViewCompat.animate(logo)
                .translationY(100)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(1.2f)).start();
        ViewCompat.animate(wel)
                .translationY(50)
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new DecelerateInterpolator(2.2f)).start();
*/


        //wel.setAnimation(welcome);
        username.setAnimation(usernameAnim);
        Login.setAnimation(LoginButtonAnim);
        sigin_to_continue.setAnimation(textviewAnim);
        signup.setAnimation(NewUserAnim);

    }
    void setparams(){
        username=findViewById(R.id.username);
        wel=findViewById(R.id.logo_name);
        sigin_to_continue=findViewById(R.id.slogan_name);
        signup=findViewById(R.id.signup);
        logo=findViewById(R.id.logo_image);
        Login=findViewById(R.id.signin);
    }
    void accessuser(){

        loginuserref = FirebaseDatabase.getInstance().getReference("Users/"+username.getEditText().getText().toString());
        loginuserref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    Intent intent=new Intent(LoginActivity.this,HomeScreenActivity.class);
                    intent.putExtra("Username",username.getEditText().getText().toString());
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Register To Proceed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
















    }


}




