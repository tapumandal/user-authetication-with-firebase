package userauthetication.tapumandal.me.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import userauthetication.tapumandal.me.R;
import userauthetication.tapumandal.me.model.ProfileModel;

public class ProfileActivity extends AppCompatActivity {

    private TextView proName, proEmail, proPhone;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        ProfileModel profileModel = (ProfileModel) getIntent().getSerializableExtra("profile");
        currentUser = (FirebaseUser) getIntent().getSerializableExtra("currentUser");

        this.proName = (TextView) findViewById(R.id.tv_profile_name);
        this.proEmail = (TextView) findViewById(R.id.tv_profile_email);
        this.proPhone = (TextView) findViewById(R.id.tv_profile_phone);

        createProView(profileModel);
    }

    private void createProView(ProfileModel profileModel) {
        this.proName.setText(profileModel.getName());
        this.proEmail.setText(profileModel.getEmail());
        this.proPhone.setText(profileModel.getPhone());
    }

    public void signOut(View view){

//        Toast.makeText(getApplicationContext(), "LOG OUT", Toast.LENGTH_LONG).show();
        set("login", "uid", "");
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


    public void set(String infoType, String key, String value){
        SharedPreferences sharedPre = getSharedPreferences(infoType, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();

        editor.putString(key, value);
        editor.apply();
    }

}
