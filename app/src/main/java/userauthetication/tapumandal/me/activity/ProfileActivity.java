package userauthetication.tapumandal.me.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import userauthetication.tapumandal.me.R;
import userauthetication.tapumandal.me.model.ProfileModel;
import userauthetication.tapumandal.me.service.SharedData;

public class ProfileActivity extends AppCompatActivity {

    private TextView proName, proEmail, proPhone;
    private FirebaseUser currentUser;
    private ProfileModel profileModel;
    private String uid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
//        currentUser = (FirebaseUser) getIntent().getSerializableExtra("currentUser");

        uid = new SharedData().get("login", "uid", this);
        profileData(uid);

        this.proName = (TextView) findViewById(R.id.tv_profile_name);
        this.proEmail = (TextView) findViewById(R.id.tv_profile_email);
        this.proPhone = (TextView) findViewById(R.id.tv_profile_phone);
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

    public void profileData(String uid){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("profiles/"+uid);

        // Read from the database
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                profileModel = dataSnapshot.getValue(ProfileModel.class);

                updateProfileView();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());
            }
        });

    }

    private void updateProfileView() {
        this.proName.setText(profileModel.getName());
        this.proEmail.setText(profileModel.getEmail());
        this.proPhone.setText(profileModel.getPhone());
    }

}
