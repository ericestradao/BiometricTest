package com.example.user.biometrictest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometrics.BiometricPrompt;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends AppCompatActivity {

        private static final String TAG = MainActivity.class.getName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Executor newExecutor = Executors.newSingleThreadExecutor();
            FragmentActivity activity = this;

            final BiometricPrompt myBiometricPrompt = new BiometricPrompt(activity, newExecutor, new BiometricPrompt.AuthenticationCallback() {
                @Override

                public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                    super.onAuthenticationError(errorCode, errString);
                    if (errorCode == BiometricPrompt.ERROR_NEGATIVE_BUTTON) {
                    } else {

                        Log.d(TAG, "An unrecoverable error occurred");
                    }
                }

                @Override
                public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);

                    Log.d(TAG, "Fingerprint recognised successfully");
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();

                    Log.d(TAG, "Fingerprint not recognised");
                }
            });

            final BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()

                    .setTitle("Fingerprint authenticator")
                    .setSubtitle("This is an authenticator for login")
                    .setDescription("Please put your finger into the sensor")
                    .setNegativeButtonText("Cancel")

                    .build();

            findViewById(R.id.launchAuthentication).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myBiometricPrompt.authenticate(promptInfo);
                }
            });

        }
    }
