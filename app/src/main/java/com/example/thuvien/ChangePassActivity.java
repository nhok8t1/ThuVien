package com.example.thuvien;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thuvien.dao.ThuThuDAO;
import com.example.thuvien.model.ThuThu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ChangePassActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText edOldPass, edNewPass, edReNewPass;
    private Button btUpdate, btCancel;
    private ThuThuDAO dao;
    private List<ThuThu> list;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        //edOldPass = findViewById(R.id.edOldPass);
        edNewPass = findViewById(R.id.edNewPass);
        edReNewPass = findViewById(R.id.edReNewPass);
        btUpdate = findViewById(R.id.btnUpdatePass);
        btCancel = findViewById(R.id.btnCancelPass);
        progressDialog = new ProgressDialog(this);
        dao = new ThuThuDAO(this);
        list = new ArrayList<>();
        list = dao.getAll();
        btUpdate.setOnClickListener(this);
        btCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btCancel) {
            finish();
        }
        if (view == btUpdate) {
            progressDialog.show();
            //String oldPass = edOldPass.getText().toString();
            String newPass = edNewPass.getText().toString();
            String reNewPass = edReNewPass.getText().toString();
            SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
            SharedPreferences.Editor edit = pref.edit();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (newPass.equalsIgnoreCase(reNewPass)) {
                user.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
                        } else {
                            Log.w(TAG, "changePassword:failure", task.getException());
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            } else {
                Toast.makeText(getApplicationContext(), "Mật khẩu không khớp", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
            /*ThuThu thuThu = new ThuThu();
            for (ThuThu i : list) {
                if (i.getMaTT().equalsIgnoreCase(pref.getString("USERNAME", null))) {
                    thuThu = i;
                    break;
                }
            }
            String pass = pref.getString("PASSWORD", null);
            if (pass.equalsIgnoreCase(oldPass) && newPass.equalsIgnoreCase(reNewPass)) {
                thuThu.setMatKhau(newPass);
                dao.updatePass(thuThu);
                edOldPass.setText("");
                edNewPass.setText("");
                edReNewPass.setText("");
                Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_LONG).show();
            }*/
        }
    }
}