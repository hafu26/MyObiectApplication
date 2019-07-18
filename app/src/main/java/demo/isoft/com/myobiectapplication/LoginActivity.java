package demo.isoft.com.myobiectapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
    private EditText editText_phone;
    private EditText editText_passwd;
    private ImageView imageView_visiable;
    private CheckBox checkBox_rememberPasswd;
    private Button button_forgetPasswd;
    private Button button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_phone= (EditText) findViewById(R.id.login_phone_edittext);
        editText_passwd= (EditText) findViewById(R.id.register_passwd_edittext);
        imageView_visiable= (ImageView) findViewById(R.id.login_passwd_visiable_image);
        checkBox_rememberPasswd= (CheckBox) findViewById(R.id.login_remember_checkBox);
        button_forgetPasswd= (Button) findViewById(R.id.login_forget_checkBox);
        button_login= (Button) findViewById(R.id.login_button);
    }
}
