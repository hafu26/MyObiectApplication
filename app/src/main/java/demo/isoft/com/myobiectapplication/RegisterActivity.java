package demo.isoft.com.myobiectapplication;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import demo.isoft.com.myobiectapplication.urls.Urls;

public class RegisterActivity extends Activity implements View.OnClickListener {
    private AuthCodeView mauthCodeView;
    private RadioGroup radioGroup;
    private RadioButton radioButton_male;
    private RadioButton radioButton_female;
    private EditText idengtify_editext;
    private EditText username_edittext;
    private EditText email_edittext;
    private EditText phone_edittext;
    private EditText passwd_edittext;
    private EditText confirm_passwd_edittext;
    private String sexName;
    private int usersex;
    private static final String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    public static final String PHONE ="^(1)([0-9]{10})$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        idengtify_editext = (EditText) findViewById(R.id.register_idengtify_editext);
        username_edittext = (EditText) findViewById(R.id.register_uername_edittext);
        email_edittext = (EditText) findViewById(R.id.register_email_edittext);
        phone_edittext = (EditText) findViewById(R.id.register_phone_edittext);
        passwd_edittext = (EditText) findViewById(R.id.register_passwd_edittext);
        confirm_passwd_edittext = (EditText) findViewById(R.id.register_confirm_passwd_edittext);
        radioGroup = (RadioGroup) findViewById(R.id.register_sex);
        radioButton_male = (RadioButton) findViewById(R.id.register_sex_checkBox);
        radioButton_female = (RadioButton) findViewById(R.id.register_sex_checkBox2);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if (radioButton_male.getId() == checkedId) {
                    sexName = radioButton_male.getText().toString();
                    usersex=1;
                } else {
                    sexName = radioButton_female.getText().toString();
                    usersex=0;
                }
            }
        });
        initUI();


    }

    private void initUI() {
        mauthCodeView = (AuthCodeView) findViewById(R.id.AuthCodeView);
        findViewById(R.id.register_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.register_button:
                String codeString = idengtify_editext.getText().toString().trim();
                String usernameString = username_edittext.getText().toString().trim();
                String emailString = email_edittext.getText().toString().trim();
                String phoneString = phone_edittext.getText().toString().trim();
                String passwdString = passwd_edittext.getText().toString().trim();
                String confirmPasswdString = confirm_passwd_edittext.getText().toString().trim();
              /*  if (usernameString.length() == 0) {
                    Toast.makeText(this, "请填写用户名！", Toast.LENGTH_LONG).show();
                    break;
                }
                if (emailString.length() == 0){
                    Toast.makeText(this, "请填写邮箱！", Toast.LENGTH_LONG).show();
                    break;
                }
                if (emailString.length() != 0) {
                    if (Pattern.matches(EMAIL, emailString)==false) {
                        Toast.makeText(this, "邮箱格式错误！", Toast.LENGTH_LONG).show();
                        email_edittext.setText("");
                        break;
                    }
                }
                if (passwdString.length() == 0) {
                    Toast.makeText(this, "请填写密码！", Toast.LENGTH_LONG).show();
                    break;
                }
                if (confirmPasswdString.length() == 0) {
                    Toast.makeText(this, "请填写确认密码！", Toast.LENGTH_LONG).show();
                    break;
                }
                if (passwdString.equals(confirmPasswdString)==false){
                    Toast.makeText(this, "两次密码填写不一致！", Toast.LENGTH_LONG).show();
                    confirm_passwd_edittext.setText("");
                    break;
                }
                if (Pattern.matches(PHONE, phoneString)==false) {
                    Toast.makeText(this, "电话号码格式错误！", Toast.LENGTH_LONG).show();
                    phone_edittext.setText("");
                    break;
                }
                if (codeString.length() == 0) {
                    Toast.makeText(this, "请填写验证码！", Toast.LENGTH_LONG).show();
                    break;
                }*/
                if (codeString.equals(mauthCodeView.getAuthCode())) {
//                    Toast.makeText(this, "验证码验证正确！", Toast.LENGTH_LONG).show();
                    send(usernameString,phoneString,emailString,passwdString);

//                    ********************************************************

                  /*  String path="userphone="+phoneString+"&&useremail="+emailString+"&&username="+usernameString+"&&password="+passwdString+"&&usertype="+1+"&&usersex"+usersex;
                    String strUrl= Urls.REGISTER_URL;
                    URL url=null;
                    try {
                        url=new URL(strUrl);
                        HttpURLConnection urlCon= (HttpURLConnection) url.openConnection();
                        urlCon.setDoInput(true);
                        urlCon.setDoOutput(true);
                        urlCon.setRequestMethod("POST");
                        urlCon.setUseCaches(false);
                        urlCon.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                        urlCon.setRequestProperty("Charset",
                                "utf-8");
                        urlCon.connect();
                        DataOutputStream dop =new DataOutputStream(urlCon.getOutputStream());
                        dop.writeBytes( URLEncoder.encode(path.toString(),"utf-8"));
                        dop.flush();
                        dop.close();

                        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
                        String result="";
                        String readline=null;
                        while ((readline=bufferedReader.readLine())!=null){
                            result+=readline;
                        }
                        bufferedReader.close();
                        urlCon.disconnect();
                        Log.e("test1",result);

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }*/


                }
                if (codeString.equals(mauthCodeView.getAuthCode())==false){
                    Toast.makeText(this, "验证码错误！", Toast.LENGTH_LONG).show();
                    break;
                }
                break;
            default:
                break;
        }
    }
    /*private void registerData(String username,String phone,String email,String passwd){
        String url=Urls.REGISTER_URL;
        String path=url+"?userphone="+phone+"&&useremail="+email+"&&username="+username+"&&password="+passwd+"&&usertype="+1+"&&usersex"+usersex;
        RequestParams params=new RequestParams(url);
        //params.addBodyParameter("userphone",phone);
        *//*params.addBodyParameter("useremail",email);
        params.addBodyParameter("username",username);
        params.addBodyParameter("password",passwd);
        params.addBodyParameter("usertype","1");
        params.addBodyParameter("usersex",Integer.toString(usersex));*//*
        x.http().get(params, new Callback.CommonCallback<String>(){
            @Override
            public void onSuccess(String result) {
                Log.e("test","success");
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("test","error");
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }*/
    private void send(final String username, final String phone, final String email, final String passwd) {
        //开启线程，发送请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;

                URL url = null;
                try {
                    url = new URL("http://114.116.38.206:8080/Temp2/userzhuce?userphone="+phone+"&&useremail="+email+"&&username="+username+"&&password="+passwd+"&&usertype=1&&usersex="+Integer.toString(usersex));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                try {
                    connection = (HttpURLConnection) url.openConnection();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //设置请求方法
                try {
                    connection.setRequestMethod("GET");
                } catch (ProtocolException e) {
                    e.printStackTrace();
                }
                //设置连接超时时间（毫秒）
                connection.setConnectTimeout(5000);
                //设置读取超时时间（毫秒）
                connection.setReadTimeout(5000);

                //返回输入流
                InputStream in = null;
                try {
                    in = connection.getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //读取输入流
                reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder result = new StringBuilder();
                String line;
                try {

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                show(result.toString());
            }

        }).start();
    }

    private void show(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.e("test",result);
            }
        });
    }

}