package com.example.minimoneybox;

import android.animation.Animator;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private Button btn_sign_in;
    private TextInputLayout til_email;
    private EditText et_email;
    private TextInputLayout til_password;
    private EditText et_password;
    private TextInputLayout til_name;
    private EditText et_name;
    private LottieAnimationView pigAnimation;

    private static final String EMAIL_REGEX = "[^@]+@[^.]+\\..+";
    private static final String NAME_REGEX = "[a-zA-Z]{6,30}";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[A-Z]).{10,50}$";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        setUpAnimation();

    }

    private void setUpViews() {

        btn_sign_in = findViewById(R.id.btn_sign_in);
        til_email = findViewById(R.id.til_email);
        et_email = findViewById(R.id.et_email);
        til_password = findViewById(R.id.til_password);
        et_password = findViewById(R.id.et_password);
        til_name = findViewById(R.id.til_name);
        et_name = findViewById(R.id.et_name);
        pigAnimation = findViewById(R.id.animation);

        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validEmail() | !validPassword() | !validName()){
                    return;
                }

                Toast.makeText(LoginActivity.this, R.string.input_valid, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setUpAnimation(){

        pigAnimation.playAnimation();

        pigAnimation.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                pigAnimation.playAnimation();
                pigAnimation.setRepeatCount(LottieDrawable.INFINITE);
                pigAnimation.setMinFrame(131);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private boolean validEmail(){
        if (Pattern.matches(EMAIL_REGEX, et_email.getText().toString())) {
            til_email.setError(null);
            return true;
        } else {
            til_email.setError(getString(R.string.email_address_error));
            return false;
        }
    }
    private boolean validPassword(){
        if (Pattern.matches(PASSWORD_REGEX, et_password.getText().toString())) {
            til_password.setError(null);
            return true;
        } else {
            til_password.setError(getString(R.string.password_error));
            return false;
        }
    }
    private boolean validName(){
        String nameInput = et_name.getText().toString().trim();

        if (nameInput.isEmpty()){
            til_name.setError(null);
            return true;
        } else if (Pattern.matches(NAME_REGEX, et_name.getText().toString())) {
            til_name.setError(null);
            return true;
        } else {
            til_name.setError(getString(R.string.full_name_error));
            return false;
        }
    }

}
