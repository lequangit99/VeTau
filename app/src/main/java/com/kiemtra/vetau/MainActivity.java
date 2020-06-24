package com.kiemtra.vetau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kiemtra.vetau.adapter.CustomAdapter;
import com.kiemtra.vetau.data.DBContext;
import com.kiemtra.vetau.model.VeTau;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private FloatingActionButton floatingActionButton;
    private DBContext dbContext;
    private CustomAdapter customAdapter;
    private List<VeTau> veTauList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.mylistview);
        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        dbContext = new DBContext(this);
        veTauList = dbContext.getAllVeTau();

        Collections.sort(veTauList, new Comparator<VeTau>() {
            @Override
            public int compare(VeTau veTau, VeTau veTau1) {
                return (int) (veTau.getmDonGia() - veTau1.getmDonGia());
            }
        });

        setAdapter();

        registerForContextMenu(listView);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra("code",1);
                startActivity(intent);

            }
        });

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();


        if (item.getItemId()==R.id.menu_xoa){
            VeTau veTau = veTauList.get(menuInfo.position);
            dbContext.deleteVeTau(veTau.getmId());
            updateListStudent();
            return true;
        }
        if (item.getItemId()==R.id.menu_sua){
            VeTau veTau = veTauList.get(menuInfo.position);
            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            intent.putExtra("code", 2);
            intent.putExtra("id", veTau.getmId());
            intent.putExtra("gadi",veTau.getmGaDi());
            intent.putExtra("gaden",veTau.getmGaDen());
            intent.putExtra("gia",veTau.getmDonGia());
            intent.putExtra("loaive", veTau.ismLoaiVe());
            startActivity(intent);
        }

        return super.onContextItemSelected(item);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    private void setAdapter() {
        if (customAdapter == null) {
            customAdapter = new CustomAdapter(this, R.layout.items_vetau, veTauList);
            listView.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            listView.setSelection(customAdapter.getCount()-1);
        }
    }

    public void updateListStudent(){
        veTauList.clear();
        veTauList.addAll(dbContext.getAllVeTau());
        if(customAdapter!= null){
            customAdapter.notifyDataSetChanged();
        }
    }


}