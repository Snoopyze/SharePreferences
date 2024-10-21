package com.example.sharepreferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edta, edtb, edtkq;
    Button btntong, btnxoa;
    TextView txtlichsu;
    String lichsu = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edta = findViewById(R.id.edit_text_a);
        edtb = findViewById(R.id.edit_text_b);
        edtkq = findViewById(R.id.edit_text_kq);
        btntong = findViewById(R.id.btnTong);
        btnxoa = findViewById(R.id.btnXoa);
        txtlichsu = findViewById(R.id.tvls);

        SharedPreferences myprefs =
                getSharedPreferences("mysave",MODE_PRIVATE);
        lichsu = myprefs.getString("ls","");
        txtlichsu.setText(lichsu);

        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                int kq = a + b;
                edtkq.setText(kq+"");
                lichsu += "2211505312229 - Kết quả: " + a+" + "+b+" = "+kq;
                txtlichsu.setText(lichsu);
                lichsu +="\n"; // Xuống dòng
            }
        });

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lichsu ="";
                txtlichsu.setText(lichsu);

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences myprefs =
                getSharedPreferences("mysave",MODE_PRIVATE);
        SharedPreferences.Editor myedit = myprefs.edit();
        myedit.putString("ls",lichsu);
        myedit.commit();
    }

}