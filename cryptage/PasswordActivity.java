package com.sci.fergani.cryptage.cryptage;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sci.fergani.cryptage.R;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class PasswordActivity extends Activity implements OnClickListener {
    TextView myemailBD;
    Button Env, Reini;
    EditText Code;
    Session session = null;
    ProgressDialog pdialog = null;
    String rec, subject, textMessage;
    Context context= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_main);
        Env = (Button) findViewById(R.id.envoyez);
        Reini = (Button) findViewById(R.id.Reinitialiser);
        Code = (EditText) findViewById(R.id.TextCode);
        final SharedPreferences preferences = this.getSharedPreferences("user", MODE_PRIVATE);
        final SharedPreferences preferences1 = this.getSharedPreferences("NEW_USER", MODE_PRIVATE);
        myemailBD = (TextView) findViewById(R.id.textViewemail);
        String email1 = preferences.getString("email", "");
        String NEW_EMAIL = preferences1.getString("NEW_Email", "");

        setTitle("");

        if (preferences1.getBoolean("NEW_USER", false) == false) {

            myemailBD.setText(email1);
            rec =email1;

        } else {
            myemailBD.setText(NEW_EMAIL);
            rec=NEW_EMAIL;
        }
        Env.setOnClickListener(this);
    }


    public void Reinitialiser(View view) {

        String code_NEW = "312";

        if ((Code.getText().toString().equals(code_NEW))) {

            Intent i = new Intent(this,NewPassActivity.class);
            startActivity(i);
            code(8);

        }else {

            Code.setError("invalid code");
            Code.requestFocus();

    }}

    public String code (int length){
        Random random = new Random();
        int number = random.nextInt(10000000)+1;
        String New_Code = String.valueOf(number);
        Toast.makeText(this, "Num :"+New_Code, Toast.LENGTH_LONG).show();

        String randomText = "";
        double rand;
        char c1, c2;
        for (int i = 0; i < (int) (length / 2); i++) {
            rand = Math.random();
            c1 = (char) Math.round((rand * 26) + 65);
            c2 = (char) Math.round((rand * 9) + 48);
            if (c1 == 91) {
                c1 = 90;
            }
            if (c2 == 58) {
                c2 = 58;
            }
            randomText = randomText + c1 + c2;
        }
        return randomText;
    }







    @Override
    public void onClick(View v) {
        rec ="ramy008.rf@gmail.com";
        subject = "NEW USER";
        textMessage = "12546";
        Log.e("NO email"," ccccccccccc" );
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lacoste.amani@gmail.com", "ramyamani2013");



            }
        });


        pdialog = ProgressDialog.show(this, "", "Sending Mail...", true);
        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();



    }
    public class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params  ) {
            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("dayaramy1992@gmail.com"));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                message.setSubject(subject);
                message.setContent(textMessage, "text/html; charset=utf-8");
                Transport.send(message);
            } catch(MessagingException e) {
                e.printStackTrace();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            pdialog.dismiss();
         //   reciep.setText("");
           // msg.setText("");
           // sub.setText("");
            Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
        }
    }



}







