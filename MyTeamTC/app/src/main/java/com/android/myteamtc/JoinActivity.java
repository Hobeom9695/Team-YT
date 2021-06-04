package com.android.myteamtc;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


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
    Button register_joinbtn, register_cancle, btnIdCheck;

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
        btnIdCheck = findViewById(R.id.btnIdCheck);

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

        btnIdCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.btnIdCheck:

                        String str_url = "http://172.30.1.26:8081/최상위메인주소/login";
                        // 서버와 통신할 데이터 값
                        String j_username = String.valueOf(joinId);

                        // 서버와 통신할 최종 데이터 값 "이름 = 값"형식, 여러 개를 보낼 경우 데이터 사이에 &으로 구별
                        String send_data = "j_username=" + j_username;

                        try
                        { /* 연결설정 및 시작 */
                            // URL 객체 생성
                            URL url = new URL(str_url);
                            // Http통신 객체 생성
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            // Http 메소드 입력(입력하지 않을 경우 기본 GET)
                            conn.setRequestMethod("POST");
                            // 서버에 연결되는 Timeout 시간 설정
                            conn.setConnectTimeout(5000);
                            // InputStream 읽어 오는 Timeout 시간 설정
                            conn.setReadTimeout(5000);
                            // OutputStream으로 POST 데이터를 넘겨주겠다는 설정
                            conn.setDoOutput(true);
                            // InputStream으로 서버로부터 응답을 받겠다는 설정
                            conn.setDoInput(true);
                            // 연결 시작
                            conn.connect();

                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            // alert 추가
                            Toast.makeText(getApplicationContext(), "서버 통신에러", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                        /* 서버로 값을 전달 */
                        try
                        {
                            // URL 객체 생성
                            URL url = new URL(str_url);
                            // Http통신 객체 생성
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            // Data를 담기 위한 OutputStream 객체 생성
                            OutputStream out = conn.getOutputStream();
                            // 서버로 보낼 데이터 값 설정
                            out.write(send_data.getBytes("UTF-8"));
                            // 서버로 값 전송
                            out.flush();
                            // Outputstream를 닫고 시스템 자원 해제

                            out.close();

                            /* 서버로 부터 결과값 수신 */
                            // InputStream 초기화
                            InputStream is = null;
                            // BufferedReader 초기화
                            BufferedReader in = null;
                            // 서버로부터 수신받을 데이터
                            String receive_data = "";
                            // 연결 성공시
                            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                                // Data를 담기 위한 InputStream 객체 생성
                                is = conn.getInputStream();
                                in = new BufferedReader(new InputStreamReader(is), 8 * 1024);
                                String line = null;
                                StringBuffer buff = new StringBuffer();
                                // BufferedReader로 부터 받아온 내용이 있다면
                                while ((line = in.readLine()) != null) {
                                    // 문자열을 더한다
                                    buff.append(line + "");
                                }
                                // 최종적으로 서버 응답값을 변수에 저장
                                receive_data = buff.toString().trim();
                                // 결과값 출력
                                // alert추가

                                System.out.println("result : " + receive_data);
                            } else {
                                // 연결 실패시 오류 메세지 출력
                                System.out.println(conn.getResponseMessage());
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            Toast.makeText(getApplicationContext(),"에러",Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                }
            }
        });

    }

    void register() {
        Log.w("register", "회원 가입 하는중");
        try{
            String userId = joinId.getText().toString();
            String userPwd = joinPw.getText().toString();
            String userName = joinName.getText().toString();
            String userTelNum = joinPhNum.getText().toString();
            String nickName = joinNick.getText().toString();
            String eMail = joinEmAddress.getText().toString();
            String company = joinComp.getText().toString();
            Log.w("앱에서 보낸값", userId+", "+userPwd+", "+userName+", "+userTelNum+", "+nickName+", "+eMail+", "+company);

            RegisterActivity register = new RegisterActivity();
            String result = register.execute(userId, userPwd, userName, userTelNum, nickName, eMail, company).get();
            Log.w("받은값", result);
        }

        catch (Exception e) { Toast.makeText(getApplicationContext(), "회원가입에러",Toast.LENGTH_SHORT).show(); }
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
