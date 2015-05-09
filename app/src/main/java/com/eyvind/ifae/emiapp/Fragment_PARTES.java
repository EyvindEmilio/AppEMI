package com.eyvind.ifae.emiapp;

import android.app.Activity;
import android.content.Context;
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
            View rootView = inflater.inflate(R.layout.fragment_partes, container, false);

            super.onCreate(savedInstanceState);
            /*setContentView(R.layout.main);*/

            ListView listView = (ListView) rootView.findViewById(R.id.listView1);

            DataHolder data = new DataHolder(rootView.getContext());
            DataHolder data1 = new DataHolder(rootView.getContext());
            DataHolder data2 = new DataHolder(rootView.getContext());
            DataHolder data3 = new DataHolder(rootView.getContext());
            DataHolder data4 = new DataHolder(rootView.getContext());

            DataAdapter d = new DataAdapter((Activity)rootView.getContext(), R.layout.parte_item_estudiante, new DataHolder[] { data, data1, data2, data3, data4 } );

            listView.setAdapter(d);

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }



    public class DataAdapter extends ArrayAdapter<DataHolder> {

        private Activity myContext;

        public DataAdapter(Activity context, int textViewResourceId, DataHolder[] objects) {
            super(context, textViewResourceId, objects);
            myContext = context;
        }

        // We keep this ViewHolder object to save time. It's quicker than findViewById() when repainting.
        class ViewHolder {
            protected DataHolder data;
            protected TextView text;
            protected Spinner spin;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = null;

            // Check to see if this row has already been painted once.
            if (convertView == null) {

                // If it hasn't, set up everything:
                LayoutInflater inflator = myContext.getLayoutInflater();
                view = inflator.inflate(R.layout.parte_item_estudiante, null);

                // Make a new ViewHolder for this row, and modify its data and spinner:
                final ViewHolder viewHolder = new ViewHolder();
                viewHolder.text = (TextView) view.findViewById(R.id.text);
                viewHolder.data = new DataHolder(myContext);
                viewHolder.spin = (Spinner) view.findViewById(R.id.spin);
                viewHolder.spin.setAdapter(viewHolder.data.getAdapter());

                // Used to handle events when the user changes the Spinner selection:
                viewHolder.spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                        viewHolder.data.setSelected(arg2);
                        viewHolder.text.setText(viewHolder.data.getText());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                    }

                });

                // Update the TextView to reflect what's in the Spinner
                viewHolder.text.setText(viewHolder.data.getText());

                view.setTag(viewHolder);

                Log.d("DBGINF", viewHolder.text.getText() + "");
            } else {
                view = convertView;
            }

            // This is what gets called every time the ListView refreshes
            ViewHolder holder = (ViewHolder) view.getTag();
            holder.text.setText(getItem(position).getText());
            holder.spin.setSelection(getItem(position).getSelected());

            return view;
        }
    }

    public class DataHolder {

        private int selected;
        private ArrayAdapter<CharSequence> adapter;

        public DataHolder(Context parent) {
            adapter = ArrayAdapter.createFromResource(parent, R.array.choices, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
