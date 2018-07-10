package com.techieblossom.firebase.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.techieblossom.firebase.R;

import java.util.Arrays;
import java.util.List;

public class LoginScreen extends AppCompatActivity {

    private static final int REQUEST_SIGN_IN = 1000;
    private Button signInOutButton;
    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.TwitterBuilder().build());
        signInOutButton = findViewById(R.id.button);
        userName = findViewById(R.id.userName);

        if (isUserSignedIn()) {
            userHasSignedIn();
        }

        signInOutButton.setOnClickListener(v -> {
            if (!isUserSignedIn()) {
                startActivityForResult(
                        AuthUI.getInstance().
                                createSignInIntentBuilder().
                                setAvailableProviders(providers).build(),
                        REQUEST_SIGN_IN);
            } else {
                signOutUser();
            }
        });
    }

    private void signOutUser() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(task -> {
                    userHasSignedOut();
                });
    }

    private void userHasSignedOut() {
        signInOutButton.setText("Start With Firebase Login");
        userName.setVisibility(View.GONE);
        userName.clearComposingText();
    }

    private void userHasSignedIn() {
        signInOutButton.setText("Sign Out");
        userName.setVisibility(View.VISIBLE);
        userName.setText(getCurrentUser().getDisplayName());
    }

    private boolean isUserSignedIn() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    private FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SIGN_IN) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null)
                userHasSignedIn();
        }
    }
}
