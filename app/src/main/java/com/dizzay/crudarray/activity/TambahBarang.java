package com.dizzay.crudarray.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dizzay.crudarray.R;
import com.dizzay.crudarray.model.BarangModel;

public class TambahBarang extends AppCompatActivity {

    EditText editTextNama, editTextStok;
    Button button;
    String id, edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_barang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextNama = (EditText) findViewById(R.id.edit_text_nama);
        editTextStok = (EditText) findViewById(R.id.edit_text_stok);
        button = (Button) findViewById(R.id.button);

        editTextStok.setInputType(InputType.TYPE_CLASS_PHONE);
        editTextStok.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                editTextStok.setSelection(editTextStok.getText().toString().length());
            }
        });

        id = getIntent().getStringExtra("id");
        edit = getIntent().getStringExtra("edit");
        if(edit != null ){
            if(edit.equals("yes")){
                String nama = MainActivity.listBarang.get(Integer.valueOf(id)).getBarangNama();
                String stok = MainActivity.listBarang.get(Integer.valueOf(id)).getBarangStok();
                editTextNama.setText(nama);
                editTextStok.setText(stok);
                editTextNama.setSelection(nama.length());
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = editTextNama.getText().toString();
                String stok = editTextStok.getText().toString();
                if(nama.equals("") || nama == null){
                    editTextNama.setError("Isi nama");
                    editTextNama.requestFocus();
                }else if(stok.equals("") || stok == null){
                    editTextStok.setError("Isi Stok");
                    editTextStok.requestFocus();
                }else{
                    if(edit != null ) {
                        if (edit.equals("yes")) {
                            BarangModel barangModel = new BarangModel();
                            barangModel.setBarangNama(nama);
                            barangModel.setBarangStok(stok);
                            MainActivity.listBarang.set(Integer.valueOf(id), barangModel);
                            MainActivity.adapter.notifyDataSetChanged();
                            finish();
                        }
                    }else{
                        int idMax = MainActivity.listBarang.size();
                        BarangModel barangModel = new BarangModel();
                        barangModel.setBarangId(String.valueOf(idMax+1));
                        barangModel.setBarangNama(nama);
                        barangModel.setBarangStok(stok);
                        MainActivity.listBarang.add(barangModel);
                        MainActivity.adapter.notifyDataSetChanged();
                        finish();
                    }
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
