package com.android.myteamtc;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    EditText loginid;
    EditText loginpw;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginid = findViewById(R.id.register_loginid);
        loginpw = findViewById(R.id.register_loginpw);
        loginbtn = findViewById(R.id.register_loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.register_loginbtn:


                        String str_url = "http://172.30.1.26:8081/최상위메인주소/login";
                        // 서버와 통신할 데이터 값
                        String j_username = String.valueOf(loginid);
                        String j_password = String.valueOf(loginpw);

                        // 서버와 통신할 최종 데이터 값 "이름 = 값"형식, 여러 개를 보낼 경우 데이터 사이에 &으로 구별
                        String send_data = "j_username=" + j_username + "&j_password=" + j_password;

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
                                System.out.println("result : " + receive_data);
                            } else {
                                // 연결 실패시 오류 메세지 출력
                                System.out.println(conn.getResponseMessage());
                            }
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        Intent intent = new Intent(LoginActivity.this, Fragment_Home.class);
                        startActivity(intent);

                        finish();

                }
            }
        });
    }
}
