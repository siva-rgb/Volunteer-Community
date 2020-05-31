package com.example.voluenteertask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    EditText reg_username,reg_fullname,reg_email,reg_password;
    Button btn_register;
    TextView txt_login;

    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reg_username=findViewById(R.id.register_username);
        reg_fullname=findViewById(R.id.register_fullname);
        reg_email=findViewById(R.id.register_email);
        reg_password=findViewById(R.id.register_password);
        btn_register=findViewById(R.id.register);
        txt_login=findViewById(R.id.txt_login);

        auth=FirebaseAuth.getInstance();

        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd=new ProgressDialog(RegisterActivity.this);
                pd.setTitle("Please wait...");
                pd.show();

                String str_username=reg_username.getText().toString();
                String str_fullName=reg_fullname.getText().toString();
                String str_email=reg_email.getText().toString();
                String str_password=reg_password.getText().toString();

                if (TextUtils.isEmpty(str_username)|| TextUtils.isEmpty(str_fullName)|| TextUtils.isEmpty(str_email)||TextUtils.isEmpty(str_password))
                {
                    Toast.makeText(RegisterActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else if (str_password.length()<6)
                {
                    Toast.makeText(RegisterActivity.this, "Password must be greater than 6", Toast.LENGTH_SHORT).show();
                }

                else
                {
                    register(str_username,str_fullName,str_email,str_password);
                }
            }
        });

    }

    private void register(final String userName, final String fullName, final String email, String password)
    {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterActivity.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    FirebaseUser firebaseUser=auth.getCurrentUser();
                    String userId=firebaseUser.getUid();
                    reference=FirebaseDatabase.getInstance().getReference().child("Users").child(userId);
                    HashMap<String ,Object>hashMap=new HashMap<>();
                    hashMap.put("id",userId);
                    hashMap.put("userName",userName);
                    hashMap.put("fullName",fullName);
                    hashMap.put("email",email);
                    hashMap.put("bio","");
                    hashMap.put("imageurl","https://firebasestorage.googleapis.com/v0/b/instagram-9dd7d.appspot.com/o/profile_image.png?alt=media&token=1f6cd9b7-caad-4ecf-8c8a-bb9c2cf70109");

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                pd.dismiss();
                                Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });

                }
                else
                {
                    pd.dismiss();
                    Toast.makeText(RegisterActivity.this, "You Cannot Register with this email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
