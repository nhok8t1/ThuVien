package com.example.thuvien;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thuvien.dao.ThuThuDAO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btLogin, btCancel;
    CheckBox chkRememberPass;
    String strUser, strPass;
    ThuThuDAO dao;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        edUsername = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btLogin = findViewById(R.id.btnLogin);
        btCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        progressDialog = new ProgressDialog(this);
        dao = new ThuThuDAO(this);
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);
        edUsername.setText(user);
        edPassword.setText(pass);
        chkRememberPass.setChecked(rem);
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edUsername.setText("");
                edPassword.setText("");
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSignIn();
            }
        });
    }

    private void onClickSignIn() {
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        strUser = edUsername.getText().toString().trim();
        strPass = edPassword.getText().toString().trim();
        if (TextUtils.isEmpty(strUser)) {
            Toast.makeText(getApplicationContext(), "Không được để trống username", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(strPass)) {
            Toast.makeText(getApplicationContext(), "Không được để trống password", Toast.LENGTH_SHORT).show();
        }
        progressDialog.show();
        fAuth.signInWithEmailAndPassword(strUser, strPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "signInWithEmail:success");
                    progressDialog.dismiss();
                    rememberUser(strUser, strPass);
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    i.putExtra("us", strUser);
                    startActivity(i);
                    finish();
                } else {
                    progressDialog.dismiss();
                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                    //Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
                /*if (strUser.isEmpty() || strPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
                } else {
                    if (dao.checklogin(strUser, strPass) > 0) {
                        rememberUser(strUser, strPass);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        i.putExtra("us", strUser);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }*/
    }

    public void rememberUser(String u, String p) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        /*if (!status) {
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }*/
        edit.putString("USERNAME", u);
        edit.putString("PASSWORD", p);
        edit.commit();
    }
}