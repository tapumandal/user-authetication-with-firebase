package userauthetication.tapumandal.me.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import userauthetication.tapumandal.me.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private EditText editText;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.tv_not_registered_user).setOnClickListener(this);
        findViewById(R.id.bt_signin).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

//        Check the user if found then redirect to Profile Activity;
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){

            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_not_registered_user:
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                break;
            
            case R.id.bt_signin:
                userLogin();
        }
    }

    private void userLogin() {

    }
}
