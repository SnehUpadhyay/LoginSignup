package com.example.vohra.loginsignup;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText et1,et2,et3;
    Button btn1,btn2,btn3;
    DatabaseHelper db;
    Session session;
    int RPS;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.SEND_SMS},RPS);}
        session=new Session(this);
        et1=(EditText) findViewById(R.id.et1);
        et2=(EditText) findViewById(R.id.et2);
        et3=(EditText) findViewById(R.id.et3);
        btn1=(Button) findViewById(R.id.btn1);
        btn2=(Button) findViewById(R.id.btn2);
        btn3=(Button) findViewById(R.id.btn3);
                if(session.loggedin())
        {
            Intent i=new Intent(MainActivity.this,NavActivity.class);
            startActivity(i);
            finish();
        }
        db=new DatabaseHelper(this);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=et1.getText().toString();
                String pass=et2.getText().toString();
                String cpass=et3.getText().toString();
                if(!invalidemail(em)){
                    et1.setError("Invalid email");
                }
                if(!invalidpassword(pass)){
                    et2.setError("Invalid password");
                }
                if(!invalidpassword(cpass)){
                    et3.setError("Invalid password");
                }
                if(!pass.equals(cpass)){
                    et2.setError("Passwords donot match");
                    et3.setError("Passwords donot match");
                }
                if(invalidemail(em)&&invalidpassword(pass)&&invalidpassword(cpass)&&pass.equals(cpass)){

                    if(db.checkAlreadyExist(em)){
                        et1.setError("Account does not exists");

                    }
                    if(!db.checkAlreadyExist(em)){
                        int i=db.Login(em,pass);
                        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this,
                                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("LOGGING IN...");
                        progressDialog.show();
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        onLoginSuccess();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);

                        if(i==1){Toast.makeText(MainActivity.this,"Successful login",Toast.LENGTH_SHORT).show();

                            session.setLoggedin(true);

                            try{
                                FileOutputStream fos=openFileOutput("pvg.txt",MODE_PRIVATE);
                                fos.write(em.getBytes());
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Intent i1=new Intent(MainActivity.this,NavActivity.class);
                            startActivity(i1);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Check Username/Password",Toast.LENGTH_SHORT).show();
                                }
                    }

                }


            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(i);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Main4Activity.class);
                startActivity(i);
            }
        });

    }
    public void onLoginSuccess() {
        btn1.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }
    private boolean invalidemail(String email){
        if(email==null){return false;}
        else{return Patterns.EMAIL_ADDRESS.matcher(email).matches();}
    }
    private boolean invalidpassword(String pass){
        if(pass!=null && pass.length()>=6){
            return true;}
        else{
            return false;
        }
    }

}

