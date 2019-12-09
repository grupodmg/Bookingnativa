package com.example.booking;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdaptador extends RecyclerView.Adapter<RecyclerAdaptador.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,descripcion;
        ImageView fotoHotel;

        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            title=(TextView)itemView.findViewById( R.id.txthotel );
            descripcion=(TextView)itemView.findViewById( R.id.txtdescripcion );
            fotoHotel=(ImageView) itemView.findViewById( R.id.imghotel );

        }
    }
    public List<HotelModelo>Hotellista;

    public RecyclerAdaptador(List<HotelModelo> hotellista) {
        Hotellista = hotellista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.itemhotel,parent,false );
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        holder.title.setText(Hotellista.get(position).getTitulo() );
        holder.descripcion.setText(Hotellista.get(position).getDescripcion() );
        holder.fotoHotel.setImageResource(Hotellista.get(position).getFotohotel() );

    }

    @Override
    public int getItemCount() {

        return Hotellista.size();
    }
}
