package com.chaturvediji.customer;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MachList extends ArrayAdapter<Machine> {
    private Activity context;
    private List<Machine> maclist;

    public MachList(Activity context, List<Machine> maclist){
        super(context,R.layout.list_layout,maclist);
        this.context=context;
        this.maclist=maclist;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View listviewitem=inflater.inflate(R.layout.list_layout,null,true);
        TextView tvid=(TextView) listviewitem.findViewById(R.id.textViewid);
        TextView tvty=(TextView) listviewitem.findViewById(R.id.textViewtype);
        TextView tvmanu=(TextView) listviewitem.findViewById(R.id.textViewmanuf);
        TextView tvmod=(TextView) listviewitem.findViewById(R.id.textViewmod);
        TextView tvdt=(TextView) listviewitem.findViewById(R.id.textViewdate);
        TextView tvrnt=(TextView) listviewitem.findViewById(R.id.textViewrent);
        TextView tvcnd=(TextView) listviewitem.findViewById(R.id.textViewcondi);
        TextView tvcu=(TextView) listviewitem.findViewById(R.id.textViewcust);

        Machine machineAddition=maclist.get(position);
        tvid.setText(machineAddition.getId());
        tvty.setText(machineAddition.getType());
        tvmanu.setText(machineAddition.getManuf());
        tvmod.setText(machineAddition.getModel());
        tvdt.setText(machineAddition.getDop());
        tvrnt.setText(machineAddition.getRent());
        tvcnd.setText(machineAddition.getCondi());
        tvcu.setText(machineAddition.getCid());
        return listviewitem;
    }
}