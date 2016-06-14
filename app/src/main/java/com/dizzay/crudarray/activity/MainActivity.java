package com.dizzay.crudarray.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dizzay.crudarray.R;
import com.dizzay.crudarray.adapter.MainAdapter;
import com.dizzay.crudarray.model.BarangModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    public static final ArrayList<BarangModel> listBarang = new ArrayList<>();
    public static MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        listBarang.clear();
        BarangModel barangModel = new BarangModel();
        barangModel.setBarangId("1");
        barangModel.setBarangNama("HP LG");
        barangModel.setBarangStok("10");
        listBarang.add(barangModel);

        BarangModel barangModel2 = new BarangModel();
        barangModel2.setBarangId("2");
        barangModel2.setBarangNama("HP Lenovo");
        barangModel2.setBarangStok("30");
        listBarang.add(barangModel2);

        BarangModel barangModel3 = new BarangModel();
        barangModel3.setBarangId("3");
        barangModel3.setBarangNama("HP Asus");
        barangModel3.setBarangStok("20");
        listBarang.add(barangModel3);

        BarangModel barangModel4 = new BarangModel();
        barangModel4.setBarangId("4");
        barangModel4.setBarangNama("HP iPhone 5");
        barangModel4.setBarangStok("50");
        listBarang.add(barangModel4);
        adapter = new MainAdapter(getApplicationContext(), listBarang);
        listView.setAdapter(adapter);
        listView.deferNotifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                final CharSequence[] item = {"Edit","Delete"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(item[which].equals("Edit")){
                            Intent intent = new Intent(getApplicationContext(), TambahBarang.class);
                            intent.putExtra("edit", "yes");
                            intent.putExtra("id", String.valueOf(position));
                            startActivity(intent);
                        }else if(item[which].equals("Delete")){
                            listBarang.remove(position);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                startActivity(new Intent(getApplicationContext(), TambahBarang.class));
                return true;
            default:
                break;
        }
        return true;
    }
}
