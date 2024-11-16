package com.example.notesapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity2 extends AppCompatActivity {

    EditText title,content;
    FloatingActionButton done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        done=findViewById(R.id.done);
        title=findViewById(R.id.title);
        content=findViewById(R.id.content);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Title= title.getText().toString();
                String Content= content.getText().toString();

                Intent intentBack=new Intent(MainActivity2.this, MainActivity.class);
                intentBack.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                if (!Content.isEmpty())
                {
                    intentBack.putExtra("STRING_1", Title);
                    intentBack.putExtra("STRING_2", Content);
                }
                    startActivity(intentBack);
            }
        });

    }
}