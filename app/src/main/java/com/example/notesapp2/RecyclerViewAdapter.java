package com.example.notesapp2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    Context context;
    ArrayList<Note> arrNotes=new ArrayList<>();
    DataBaseHelper dataBaseHelper;



    RecyclerViewAdapter(Context context,ArrayList<Note> arrNotes,DataBaseHelper dataBaseHelper){

        this.context=context;
        this.arrNotes=arrNotes;
        this.dataBaseHelper=dataBaseHelper;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.txtTitlec.setText(arrNotes.get(position).getTitle());
        holder.txtContentc.setText(arrNotes.get(position).getContent());

        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                deleteItem(position);

                return true;
            }

        });

    }

    @Override
    public int getItemCount() {
        return arrNotes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView txtTitlec,txtContentc;
        LinearLayout llrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitlec=itemView.findViewById(R.id.txtTitlec);
            txtContentc=itemView.findViewById(R.id.txtContentc);
            llrow=itemView.findViewById(R.id.llrow);

        }
    }

    public void deleteItem(int pos){

        AlertDialog dialog=new AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure want to delete")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dataBaseHelper.noteDao().delete(new Note(arrNotes.get(pos).getId(),arrNotes.get(pos).getTitle(),arrNotes.get(pos).getContent()));

                        ((MainActivity)context).showNotes();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();

    }
}
