package com.example.snowman;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginTabFragment  extends Fragment {
    EditText email,pass;
    Button btnlogin;
    FirebaseAuth fAuth;
    float v=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment,container, false
        );

        email = root.findViewById(R.id.email);
        pass =  root.findViewById(R.id.pass);
        btnlogin = root.findViewById(R.id.login);

        email.setAlpha(v);
        pass.setAlpha(v);
        btnlogin.setAlpha(v);

        email.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        pass.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btnlogin.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String xemail = email.getText().toString().trim();
                String xpassword = pass.getText().toString().trim();

                if (TextUtils.isEmpty(xemail)){
                    email.setError("Email is Required.");
                    return;
                }
                if (TextUtils.isEmpty(xpassword)){
                    pass.setError("Password is Required.");
                    return;
                }

                fAuth.signInWithEmailAndPassword(xemail,xpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(getContext(),"Logged", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getActivity(), choose.class));
                        } else{
                            Toast.makeText(getContext(),"Error ! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        return root;

    }


}
