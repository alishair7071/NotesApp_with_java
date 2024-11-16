package com.example.notesapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout emptyLinear;
    Button btnCreate;
    FloatingActionButton fab;
    RecyclerView recycler;

    DataBaseHelper dataBaseHelper;

    String Title,Content;

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

        idFind();
        showNotes();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentGo=new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intentGo);

            }
        });

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("STRING_1") && intent.hasExtra("STRING_2")) {
            Title = intent.getStringExtra("STRING_1");
            Content = intent.getStringExtra("STRING_2");

            setOnRecycler();


        }



        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fab.performClick();

            }
        });

    }


    public void showNotes() {


        ArrayList<Note> arrNotes=(ArrayList<Note>) dataBaseHelper.noteDao().getAllNotes();
        if (arrNotes.size()>0){

            emptyLinear.setVisibility(View.GONE);
            recycler.setVisibility(View.VISIBLE);

            recycler.setAdapter(new RecyclerViewAdapter(this,arrNotes,dataBaseHelper));

        }else {

            emptyLinear.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.GONE);
        }


    }


    private void idFind(){

        emptyLinear=findViewById(R.id.emptyLinear);
        btnCreate=findViewById(R.id.btnCreate);
        fab=findViewById(R.id.fab);
        recycler=findViewById(R.id.recycler);

        dataBaseHelper=DataBaseHelper.getInstance(this);

        recycler.setLayoutManager(new GridLayoutManager(this,2));


    }

    public void setOnRecycler(){


        if (!Content.isEmpty()){

            dataBaseHelper.noteDao().addNote(new Note(Title,Content));
            showNotes();
           // title.setText("");
           // content.setText("");



        }else {
            Toast.makeText(MainActivity.this, "Please Enter Something", Toast.LENGTH_SHORT).show();
            showNotes();
        }


    }


}