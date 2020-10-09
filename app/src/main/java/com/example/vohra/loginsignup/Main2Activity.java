package com.example.vohra.loginsignup;

import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    EditText et1, et2, et3, et4,etp;
    Button btn1, btn2;
    DatabaseHelper db;
    int no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);
        etp = (EditText) findViewById(R.id.etp);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        db=new DatabaseHelper(this);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(i);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n=et1.getText().toString();
                String em=et2.getText().toString();
                String pass=et3.getText().toString();
                String cpass=et4.getText().toString();
                String pho=etp.getText().toString();




                if(!invalidemail(em)){
                    et2.setError("Invalid email");
                }
                if(!invalidpassword(pass)){
                    et3.setError("Invalid password");
                }
                if(!invalidpassword(cpass)){
                    et4.setError("Invalid password");
                }
                if(!pass.equals(cpass)){
                    et3.setError("Passwords donot match");
                    et4.setError("Passwords donot match");
                }
                if(!phonelength(pho))
                {
                    etp.setError("Cannot be less than 10 Numbers");
                }

                if(isNullOrBlank(n)||isNullOrBlank(em)||isNullOrBlank(pass)||isNullOrBlank(cpass)||isNullOrBlank(pho)){
                    Toast.makeText(Main2Activity.this,"No field should be empty",Toast.LENGTH_SHORT).show();
                }
                if(invalidemail(em)&&invalidpassword(pass)&&invalidpassword(cpass)&&pass.equals(cpass)){
                    if(db.checkAlreadyExist(em)){
                        Random rand=new Random();
                        no=rand.nextInt(900000)+100000;
                        String m=Integer.toString(no);
                        if (db.insertdata(n,em,pass,pho))
                        {
                            Toast.makeText(Main2Activity.this, "s", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Main2Activity.this, "No s", Toast.LENGTH_SHORT).show();
                        }

                        final ProgressDialog progressDialog = new ProgressDialog(Main2Activity.this,
                                R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setMessage("Creating Account...");
                        progressDialog.show();
                        new android.os.Handler().postDelayed(
                                new Runnable() {
                                    public void run() {
                                        onSignupSuccess();
                                        progressDialog.dismiss();
                                    }
                                }, 3000);
                        Intent i=new Intent(getApplicationContext(),MainActivity.class);
                        PendingIntent pi=PendingIntent.getActivity(getApplicationContext(),0,i,0);
                        SmsManager sms=SmsManager.getDefault();
                        sms.sendTextMessage(pho,null,"Your Otp is : " + m,pi,null);
                        Toast.makeText(getApplicationContext(),"OTP SENT SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                        Toast.makeText(Main2Activity.this,"Account Created",Toast.LENGTH_LONG).show();
                        et1.setText("");
                        et2.setText("");
                        et3.setText("");
                        et4.setText("");
                        etp.setText("");
                    }
                    else{
                        Toast.makeText(Main2Activity.this,"Email already exists",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    } public void onSignupSuccess() {
        btn1.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    private boolean invalidemail(String email) {
        if (email == null) {
            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    private boolean invalidpassword(String pass) {
        if (pass != null && pass.length() >= 6) {
            return true;
        } else {
            return false;
        }
    }
    boolean isNullOrBlank(String s)
    {
        return (s==null || s.trim().equals(""));
    }
    private boolean phonelength(String pho)
    {
        if(pho.length()== 10)
        {
            return  true;
        }
        else
        {
            return false;
        }
    }
}

