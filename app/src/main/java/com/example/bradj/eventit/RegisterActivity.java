package com.example.bradj.eventit;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtFname;
    private EditText txtLname;
    private EditText txtEmail;
    private EditText txtUserName;
    private EditText txtPassword;
    private View mProgressView;
    private View mRegisterFormView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptRegister();
            }
        });

        mRegisterFormView = findViewById(R.id.register_form);
        mProgressView = findViewById(R.id.register_progress);
        txtFname=findViewById(R.id.txtFirstName);
        txtLname=findViewById(R.id.txtLastName);
        txtEmail=findViewById(R.id.txtEmail);
        txtUserName=findViewById(R.id.txtUserName);
        txtPassword=findViewById(R.id.txtPassword);
    }

    private void attemptRegister() {
//        if (mAuthTask != null) {
//            return;
//        }

        // Reset errors.
        txtFname.setError(null);
        txtLname.setError(null);
        txtEmail.setError(null);
        txtUserName.setError(null);
        txtPassword.setError(null);

        // Store values at the time of the login attempt.
        String fname = txtFname.getText().toString();
        String lname = txtLname.getText().toString();
        String email = txtEmail.getText().toString();
        String username = txtUserName.getText().toString();
        String password = txtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;
        // Check for a valid firstName, if the user entered one.
        if (TextUtils.isEmpty(fname)) {
            txtFname.setError(getString(R.string.error_field_required));
            focusView = txtFname;
            cancel = true;
        }

        // Check for a valid Last name, if the user entered one.
        if (TextUtils.isEmpty(lname)) {
            txtLname.setError(getString(R.string.error_field_required));
            focusView = txtLname;
            cancel = true;
        }

        // Check for a valid username, if the user entered one.
        if (TextUtils.isEmpty(username)) {
            txtUserName.setError(getString(R.string.error_field_required));
            focusView = txtUserName;
            cancel = true;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            txtPassword.setError(getString(R.string.error_invalid_password));
            focusView = txtPassword;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            txtEmail.setError(getString(R.string.error_field_required));
            focusView = txtEmail;
            cancel = true;
        } else if (!isEmailValid(email)) {
            txtEmail.setError(getString(R.string.error_invalid_email));
            focusView = txtEmail;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
//            mAuthTask = new LoginActivity.UserLoginTask(email, password);
//            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mRegisterFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mRegisterFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}
