package com.example.lab_03_working;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AddCityFragment extends DialogFragment {

    public interface AddCityDialogListener {
        void addCity(City city);
    }

    private static final String ARG_CITY = "city"; // final makes it a CONSTANT so it cant be edited anywhere else

    public static AddCityFragment newInstance(City city) { //need this newInstance to create a new Fragment when editing a City
        Bundle args = new Bundle();
        args.putSerializable(ARG_CITY, city);
        AddCityFragment f = new AddCityFragment();
        f.setArguments(args);
        return f;
    }

    private AddCityDialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddCityDialogListener) {
            listener = (AddCityDialogListener) context;
        } else {
            throw new RuntimeException(context + " must implement AddCityDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(requireContext()).inflate(R.layout.fragment_add_city, null);
        EditText cityEt = view.findViewById(R.id.edit_text_city_text);
        EditText provEt = view.findViewById(R.id.edit_text_province_text);

        City editing = null;
        Bundle args = getArguments(); // accessing serializable bundle using getArguments() and retrieving city object
        if (args != null) {
            editing = (City) args.getSerializable(ARG_CITY);
        }
        if (editing != null) {
            cityEt.setText(editing.getName());
            provEt.setText(editing.getProvince());
        }

        City finalEditing = editing;
        return new AlertDialog.Builder(requireContext())
                .setTitle(finalEditing == null ? "Add a city" : "Edit city")
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton(finalEditing == null ? "Add" : "Save", (d, w) -> {
                    String name = cityEt.getText().toString().trim(); // to prevent any errors
                    String prov = provEt.getText().toString().trim(); // to prevent any errors
                    if (finalEditing == null) {
                        listener.addCity(new City(name, prov));
                    } else {
                        finalEditing.setName(name);
                        finalEditing.setProvince(prov);
                        listener.addCity(null);
                    }
                })
                .create();
    }
}
