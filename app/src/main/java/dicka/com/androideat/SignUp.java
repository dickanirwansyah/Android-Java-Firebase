package dicka.com.androideat;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import dicka.com.androideat.model.User;

public class SignUp extends AppCompatActivity {

    private Button btnSigUp;
    private MaterialEditText edtPhone;
    private MaterialEditText edtName;
    private MaterialEditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //deklarasi element android
        btnSigUp = (Button) findViewById(R.id.btnSignUp);
        edtPhone = (MaterialEditText) findViewById(R.id.edtPhone);
        edtName = (MaterialEditText) findViewById(R.id.edtName);
        edtPassword = (MaterialEditText) findViewById(R.id.edtPassword);

        //init firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        //button sign up
        btnSigUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                final ProgressDialog dialog = new ProgressDialog(SignUp.this);
                dialog.setMessage("Singup User..");
                dialog.show();

                table_user.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check
                        if(dataSnapshot.child(edtPhone.getText().toString()).exists()){
                            dialog.dismiss();
                            Toast.makeText(SignUp.this, "Maaf nomor handphone ini sudah terdaftar",
                                    Toast.LENGTH_SHORT).show();
                        }else{

                            dialog.dismiss();
                            User newUser =
                                    new User(edtName.getText().toString(),
                                            edtPassword.getText().toString());
                            table_user.child(edtPhone.getText().toString()).setValue(newUser);
                            Toast.makeText(SignUp.this, "Proses Sign Up berhasil",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
