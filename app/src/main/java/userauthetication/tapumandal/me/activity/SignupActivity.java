package userauthetication.tapumandal.me.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import userauthetication.tapumandal.me.R;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private String name;
    private String email;
    private String password;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        findViewById(R.id.tv_go_back_signin).setOnClickListener(this);
        findViewById(R.id.bt_signup).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser == null){
            Toast.makeText(getApplicationContext(), "No sign in record", Toast.LENGTH_LONG).show();
        }else{

            Toast.makeText(getApplicationContext(), "Record find", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_go_back_signin:
                startActivity(new Intent(this, MainActivity.class));
                Toast.makeText(getApplicationContext(), "GO Back to Signin", Toast.LENGTH_LONG).show();

            case R.id.bt_signup:
                this.registerUser();
        }
    }

    private void registerUser(){
        this.name = ((EditText) findViewById(R.id.et_name)).getText().toString().trim();
        this.email = ((EditText) findViewById(R.id.et_user_name)).getText().toString().trim();
        this.password = ((EditText) findViewById(R.id.et_password)).getText().toString().trim();

        Toast.makeText(getApplicationContext(), this.name+"-"+this.email+"-"+this.password, Toast.LENGTH_LONG).show();


        mAuth.createUserWithEmailAndPassword(this.email, this.password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                            Toast.makeText(getApplicationContext(), "Authentication Successfull.", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
