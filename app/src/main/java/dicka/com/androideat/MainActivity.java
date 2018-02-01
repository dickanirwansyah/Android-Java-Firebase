package dicka.com.androideat;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn;
    Button btnSignUp;
    TextView textSlogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);

        textSlogan = (TextView) findViewById(R.id.txtSlogan);
        Typeface nabilaFaceFonts = Typeface.createFromAsset(getAssets(), "fonts/Nabila.ttf");
        textSlogan.setTypeface(nabilaFaceFonts);

        //button sign in deklarasi
        btnSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent loginSignIn = new Intent(MainActivity.this, SignIn.class);
                startActivity(loginSignIn);
            }
        });


        //button sign up deklarasi
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent prosesSignUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(prosesSignUp);
            }
        });
    }
}
