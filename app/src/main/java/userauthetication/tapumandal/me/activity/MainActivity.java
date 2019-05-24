package userauthetication.tapumandal.me.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import userauthetication.tapumandal.me.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_not_registered_user).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, Android!");

        switch (v.getId()) {
            case R.id.tv_not_registered_user:
                startActivity(new Intent(getApplicationContext(), SignupActivity.class));
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
