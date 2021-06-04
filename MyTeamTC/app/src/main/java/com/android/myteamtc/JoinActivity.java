package com.android.myteamtc;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class JoinActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    EditText joinId;
    EditText joinPw;
    EditText joinPwCheck;
    EditText joinName;
    EditText joinPhNum;
    EditText joinNick;
    EditText joinEmAddress;
    EditText joinComp;
    Button register_joinbtn, register_cancle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        joinId = findViewById(R.id.register_joinId);
        joinPw = findViewById(R.id.register_joinPw);
        joinPwCheck = findViewById(R.id.register_joinPwCheck);
        joinName = findViewById(R.id.register_joinName);
        joinPhNum = findViewById(R.id.register_joinPhNum);
        joinNick = findViewById(R.id.register_joinNick);
        joinEmAddress = findViewById(R.id.register_joinEmAddress);
        joinComp = findViewById(R.id.register_joinComp);

        register_joinbtn = findViewById(R.id.register_joinbtn);
        register_cancle = findViewById(R.id.register_cancle);

        register_joinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId())  {
                    case R.id.register_joinbtn:
                        register();
                        Intent intent = new Intent(JoinActivity.this, Fragment_Home.class);
                        startActivity(intent);

                        finish();

                }
            }
        });

    }

    void register() {
        Log.w("register", "회원 가입 하는중");
        try{
            String id = joinId.getText().toString();
            String password = joinPw.getText().toString();
            String user_name = joinName.getText().toString();
            String user_telnum = joinPhNum.getText().toString();
            String nickname = joinNick.getText().toString();
            String email = joinEmAddress.getText().toString();
            String company = joinComp.getText().toString();
            Log.w("앱에서 보낸값", id+", "+password+", "+user_name+", "+user_telnum+", "+nickname+", "+email+", "+company);

            RegisterActivity register = new RegisterActivity();
            String result = register.execute(id, password, user_name, user_telnum, nickname, email, company).get();
            Log.w("받은값", result);
        }
        catch (Exception e) {  }
    }


//    public void onBtn2Finish(View v) { finish();}
//
//    public void onBtnPost(View v) {
//        joinId.setText("");
//        joinPw.setText("");
//        joinName.setText("");
//        joinPhNum.setText("");
//        joinNick.setText("");
//        joinEmAddress.setText("");
//        joinComp.setText("");
//
//
//        String sUrl = getString(R.string.server_addr) + "/security/joinForm";
//
//            try {
//                ContentValues values = new ContentValues();
//                values.put("id", String.valueOf(joinId));
//                values.put("password", String.valueOf(joinPw));
//                values.put("user_name", String.valueOf(joinName));
//                values.put("user_telnum", String.valueOf(joinPhNum));
//                values.put("nickname", String.valueOf(joinNick));
//                values.put("email", String.valueOf(joinEmAddress));
//                values.put("company", String.valueOf(joinComp));
//
//                JoinActivity.NetwortTask networkTask = new JoinActivity.NetwortTask(sUrl, values);
//                networkTask.execute();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//    }
//
//    public class NetwortTask extends AsyncTask<Void, Void, String> {
//
//        private String url;
//        private ContentValues values;
//
//        public NetwortTask(String url, ContentValues values) {
//            this.url = url;
//            this.values = values;
//        }
//
//        @Override
//        protected String doInBackground(Void... params) {
//            String result;
//            RequestHttpURLConnection requestHttpURLConnection =
//                    new RequestHttpURLConnection();
//
//            result = requestHttpURLConnection.request(url, values);
//
//            return result;
//        }

//        @Override
//        protected void onPostExecute(String s) {
//
//        }
//    }
}
