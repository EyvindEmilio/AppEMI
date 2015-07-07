package com.eyvind.ifae.emiapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class Fragment_PARTES extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public static Fragment_PARTES newInstance(int sectionNumber) {
            Fragment_PARTES fragment = new Fragment_PARTES();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public Fragment_PARTES() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_partes, container, false);

            super.onCreate(savedInstanceState);
            /*setContentView(R.layout.main);*/

            ListView listView = (ListView) rootView.findViewById(R.id.listView1);

            partesHolder data = new partesHolder("Eyvind Emilio Tinini Coaquira","9981765",rootView.getContext());
            partesHolder data1 = new partesHolder("Eyvind 1","998",rootView.getContext());
            partesHolder data2 = new partesHolder("Judy Adriana Quispe Geronimo","998",rootView.getContext());
            partesHolder data3 = new partesHolder("Eyvind 3","998",rootView.getContext());
            partesHolder data4 = new partesHolder("Eyvind 4","998",rootView.getContext());

            ListaAdapter myAdapter = new ListaAdapter((Activity)rootView.getContext(), R.layout.parte_item_estudiante, new partesHolder[] { data, data1, data2, data3, data4 } );

            listView.setLongClickable(true);
            listView.setClickable(true);
            registerForContextMenu(listView);
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("Mensaje", "LONG");
                    AlertDialog.Builder aler = new AlertDialog.Builder(rootView.getContext());
                    aler.setPositiveButton("Hola", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    aler.setNegativeButton("dsa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog ll = aler.create();
                    ll.show();
                    return false;
                }
            });

            listView.setOnLongClickListener(new AdapterView.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.e("Mensaje", "LONG");
                    return false;
                }


                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Log.e("Mensaje", "LONG");
                    AlertDialog.Builder aler = new AlertDialog.Builder(rootView.getContext());
                    aler.setPositiveButton("Hola", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    aler.setNegativeButton("dsa", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog ll = aler.create();
                    ll.show();
                    return false;
                }
            });
            listView.setAdapter(myAdapter);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

    public class ListaAdapter extends ArrayAdapter<partesHolder> {
        private Activity myContext;
        private partesHolder [] objects;
        public ListaAdapter(Activity context, int textViewResourceId, partesHolder[] objects) {
            super(context, textViewResourceId, objects);
            myContext = context;
            this.objects = objects;
        }

        class ViewHolder {
            protected partesHolder data;
            protected TextView FullName;
            protected TextView Codigo;
            protected Spinner spin;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;

            if (convertView == null) {
                LayoutInflater inflator = myContext.getLayoutInflater();
                view = inflator.inflate(R.layout.parte_item_estudiante, null);
                final ViewHolder viewHolder = new ViewHolder();
                viewHolder.FullName = (TextView) view.findViewById(R.id.lista_fullName);
                viewHolder.Codigo = (TextView) view.findViewById(R.id.lista_Codigo);
                viewHolder.data = new partesHolder(
                        objects[position].getFullName(),
                        objects[position].getCodigo(),
                        myContext);
                viewHolder.spin = (Spinner) view.findViewById(R.id.spin);
                viewHolder.spin.setAdapter(viewHolder.data.getAdapter());
                viewHolder.spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        //viewHolder.data.setSelected(arg2);
                        //viewHolder.FullName.setText(viewHolder.data.getText());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }
                });

                viewHolder.FullName.setText(viewHolder.data.getFullName());
                viewHolder.FullName.setText(viewHolder.data.getCodigo());
                view.setTag(viewHolder);
                Log.d("DBGINF", viewHolder.FullName.getText() + "");
            } else {
                view = convertView;
            }

            ViewHolder holder = (ViewHolder) view.getTag();

            holder.FullName.setText(getItem(position).getFullName());
            holder.Codigo.setText(getItem(position).getCodigo());
            holder.spin.setSelection(getItem(position).getSelected());
            return view;
        }
    }

    public class partesHolder {
        private int selected;
        private String fullName;
        private String Codigo;
        private ArrayAdapter<CharSequence> adapter;
        public partesHolder(String full,String codigo,Context parent) {
            fullName = full;
            Codigo = codigo;
            adapter = ArrayAdapter.createFromResource(parent, R.array.parametros_asistencia, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        }

        public String getCodigo() { return Codigo; }


        public void setCodigo(String codigo) {
            Codigo = codigo;
        }

        public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ArrayAdapter<CharSequence> getAdapter() {
            return adapter;
        }
        public String getText() {
            return (String) adapter.getItem(selected);
        }
        public int getSelected() {
            return selected;
        }
        public void setSelected(int selected) {
            this.selected = selected;
        }
    }
}
