package userauthetication.tapumandal.me.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
//        updateUI(currentUser);
        Toast.makeText(getApplicationContext(), (CharSequence) currentUser.getUid(), Toast.LENGTH_LONG).show();
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
    }
}
