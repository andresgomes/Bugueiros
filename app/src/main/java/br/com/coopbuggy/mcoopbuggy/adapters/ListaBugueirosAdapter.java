package br.com.coopbuggy.mcoopbuggy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.coopbuggy.mcoopbuggy.R;
import br.com.coopbuggy.mcoopbuggy.javaclass.Bugueiro;

/**
 * Created by andre on 03/11/2017.
 */

public class ListaBugueirosAdapter extends ArrayAdapter<Bugueiro> {

    private Context context;
    private List<Bugueiro> bugueiros = null;

    public ListaBugueirosAdapter (Context context, List<Bugueiro> bugueiros){
        super(context, 0, bugueiros);
        this.context = context;
        this.bugueiros = bugueiros;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Bugueiro bugueiro = bugueiros.get(position);

        if (view == null)
            view = LayoutInflater.from(context).inflate(R.layout.lista_bugueiros, null);

//        ImageView imageViewBugueiro = (ImageView) view.findViewById(R.id.image_view_bugueiro);
//        imageViewBugueiro.setImageResource(bugueiro.getFoto());

        TextView textViewNomeBugueiro = (TextView) view.findViewById(R.id.text_view_nome_bugueiro);
        textViewNomeBugueiro.setText(bugueiro.getNome());

        TextView textViewPlacaBugueiro = (TextView) view.findViewById(R.id.text_view_placa_bugueiro);
        textViewPlacaBugueiro.setText("Placa: " + bugueiro.getVeiculo().getPlaca());

        return view;
    }
}
