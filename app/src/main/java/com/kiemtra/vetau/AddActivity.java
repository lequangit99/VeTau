package com.kiemtra.vetau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.kiemtra.vetau.data.DBContext;
import com.kiemtra.vetau.model.VeTau;

import java.util.Vector;

public class AddActivity extends AppCompatActivity {
    private EditText edtGaDi, edtGaDen, edtDonGia;
    private RadioButton rdtMotChieu, rdtKhuHoi;
    private Button btnDongY, btnQuayVe;
    private DBContext dbContext;
    private int code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        dbContext = new DBContext(this);

        final Intent intent = getIntent();
        code = intent.getIntExtra("code",0);

        if (code == 2){
            edtGaDi.setText(intent.getStringExtra("gadi"));
            edtGaDen.setText(intent.getStringExtra("gaden"));
            edtDonGia.setText(String.valueOf(intent.getFloatExtra("gia",0)));
            if (intent.getBooleanExtra("loaive",false)){
                rdtMotChieu.setChecked(true);
            }
            if (!intent.getBooleanExtra("loaive",false)){
                edtDonGia.setText(String.valueOf(intent.getFloatExtra("gia",0)/2/0.95));
                rdtKhuHoi.setChecked(true);
            }
        }

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code == 1) {
                    VeTau veTau = createVeTau();
                    dbContext.addVeTau(veTau);
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                if (code == 2 ){
                    VeTau veTau = new VeTau();
                    veTau.setmId(intent.getIntExtra("id",0));
                    veTau.setmGaDi(edtGaDi.getText().toString());
                    veTau.setmGaDen(edtGaDen.getText().toString());
                    veTau.setmDonGia(Float.parseFloat(edtDonGia.getText().toString())/2);
                    boolean check = true;
                    if (rdtMotChieu.isChecked()){
                        check = true;
                    }
                    if (rdtKhuHoi.isChecked()){
                        check = false;
                    }
                    veTau.setmLoaiVe(check);

                    dbContext.updateVeTau(veTau);
                    Intent intent = new Intent(AddActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnQuayVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initView() {
        edtGaDi = (EditText) findViewById(R.id.edt_gadi);
        edtGaDen = (EditText) findViewById(R.id.edt_gaden);
        edtDonGia = (EditText) findViewById(R.id.edt_dongia);
        rdtMotChieu= (RadioButton) findViewById(R.id.rdt_motChieu);
        rdtKhuHoi = (RadioButton) findViewById(R.id.rdt_khuHoi);
        btnDongY = (Button) findViewById(R.id.btn_dongY);
        btnQuayVe = (Button) findViewById(R.id.btn_quayVe);
    }

    private VeTau createVeTau() {
        String gaDi = edtGaDi.getText().toString();
        String gaDen = edtGaDen.getText().toString();
        float donGia = Float.parseFloat(edtDonGia.getText().toString());
        boolean check = true;
        if (rdtMotChieu.isChecked()){
            check = true;
        }
        if (rdtKhuHoi.isChecked()){
            donGia = (float) (Float.parseFloat(edtDonGia.getText().toString())*2*0.95);
            check = false;
        }

        VeTau veTau = new VeTau(gaDi,gaDen,donGia,check);
        return veTau;
    }


}