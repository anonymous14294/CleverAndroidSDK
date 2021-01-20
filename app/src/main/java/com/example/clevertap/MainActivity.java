package com.example.clevertap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.CleverTapInstanceConfig;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText name, email;
    Button loginSubmit,testbutton;
    CleverTapAPI clevertapDefaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //CleverTapInstanceConfig clevertapAdditionalInstanceConfig =  CleverTapInstanceConfig.createInstance(getApplicationContext(), "Z44-Z4K-K65Z", "TEST-341-441");
        //CleverTapAPI clevertapAdditionalInstance = CleverTapAPI.instanceWithConfig(clevertapAdditionalInstanceConfig);
        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.DEBUG);   //Set Log level to DEBUG log warnings or other important messages
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name =(EditText) findViewById(R.id.name);
        email=(EditText) findViewById(R.id.email);
        loginSubmit=(Button) findViewById(R.id.login);
        testbutton=(Button) findViewById(R.id.test);
        testbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clevertapDefaultInstance.pushEvent("Product viewed");
            }
        });

        loginSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();

//Update pre-defined profile properties
                profileUpdate.put("Name", name.getText().toString());
                profileUpdate.put("Email", email.getText().toString());
//Update custom profile properties
                //profileUpdate.put("Plan Type", "Silver");
                //profileUpdate.put("Favorite Food", "Pizza");

                clevertapDefaultInstance.pushProfile(profileUpdate);
            }
        });

    }

}
