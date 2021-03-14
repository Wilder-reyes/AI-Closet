package com.example.snowman;

import android.app.AppComponentFactory;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SignupTabFragment extends Fragment {

    EditText xfull_name, xemail, xpassword;
    Button sing_up;
    FirebaseAuth fAuth;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container, false
        );

        sing_up = root.findViewById(R.id.sign_up);
        xfull_name = root.findViewById(R.id.full_name);
        xemail = root.findViewById(R.id.email);
        xpassword = root.findViewById(R.id.pass);
        sing_up = root.findViewById(R.id.sign_up);

        fAuth = FirebaseAuth.getInstance();
        fAuth = FirebaseAuth.getInstance();


        sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = xemail.getText().toString().trim();
                String password = xpassword.getText().toString().trim();
                String full_name = xfull_name.getText().toString().trim();



                if (TextUtils.isEmpty(email)){
                    xemail.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    xpassword.setError("Password is Required.");
                    return;
                }
                if (TextUtils.isEmpty(full_name)){
                    xfull_name.setError("Name is Required.");
                    return;
                }

                if (password.length() < 6){
                    xpassword.setError("The password must be longer than 6 characters.");
                    return;
                }

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getContext(),"User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), choose.class));
                        } else {
                            Toast.makeText(getContext(),"Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
        return root;
    }

    
}
