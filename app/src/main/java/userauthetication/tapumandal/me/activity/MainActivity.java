package userauthetication.tapumandal.me.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import userauthetication.tapumandal.me.R;
import userauthetication.tapumandal.me.model.ProfileModel;
import userauthetication.tapumandal.me.service.SharedData;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    private TextView textView;
    private EditText editText;

    private FirebaseAuth mAuth;

    private SharedData sharedData;
    FirebaseUser currentUser;

    private ProfileModel profileModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        sharedData = new SharedData();

        findViewById(R.id.tv_not_registered_user).setOnClickListener(this);
        findViewById(R.id.bt_signin).setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Check the user if found then redirect to Profile Activity;
        if(!sharedData.get("login", "uid", getApplicationContext()).isEmpty()){

            startActivity(new Intent(getApplicationContext(), ProfileActivity.class).putExtra("profile", profileModel ).putExtra("currentUser", currentUser));
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_not_registered_user:
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                break;
            
            case R.id.bt_signin:
                userLogin();
                break;
        }
    }

    private void userLogin() {

        String email = ((EditText) findViewById(R.id.et_user_name)).getText().toString().trim();
        String password = ((EditText) findViewById(R.id.et_password)).getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            currentUser = mAuth.getCurrentUser();

                            //what is it?
                            assert currentUser != null;
                            sharedData.set("login", "uid", currentUser.getUid(), getApplicationContext());

                            profileModel = new ProfileModel(currentUser.getUid(),"My Name",currentUser.getEmail(),"0123456789");
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class).putExtra("profile", profileModel ).putExtra("currentUser", currentUser));
                        } else {
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
