package userauthetication.tapumandal.me.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import userauthetication.tapumandal.me.R;
import userauthetication.tapumandal.me.model.ProfileModel;

public class ProfileActivity extends AppCompatActivity {

    private TextView proName, proEmail, proPhone;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        ProfileModel profileModel = (ProfileModel) getIntent().getSerializableExtra("profile");

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
}
