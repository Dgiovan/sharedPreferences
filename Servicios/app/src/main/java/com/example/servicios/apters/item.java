package com.example.servicios.apters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.servicios.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class item extends RecyclerView.Adapter<item.VH> {

    public ArrayList<Itemejemplo> items;
    Context context;

    public item(ArrayList<item.Itemejemplo> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item,parent,false);
        return new VH(view);
    }
    public class VH extends RecyclerView.ViewHolder {
        TextView titulo;
        ImageView imagen;
        public VH(@NonNull View v) {
            super(v);
        titulo = v.findViewById(R.id.text);
        imagen = v.findViewById(R.id.image);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

        try {
            holder.titulo.setText(items.get(position).getItem().getString("shorttitle"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class Itemejemplo
    {
        JSONObject objeto;
        public Itemejemplo (JSONObject object)
        {
            this.objeto = object;
        }

        public JSONObject getItem(){return objeto;}
    }
}
