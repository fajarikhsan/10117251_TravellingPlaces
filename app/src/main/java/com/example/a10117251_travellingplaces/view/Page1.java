package com.example.a10117251_travellingplaces.view;

//Tanggal pengerjaan : 08-08-2020
////NIM : 10117251
////Nama : Fajar Ikhsanul Faaizin
////Kelas : IF-8

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.a10117251_travellingplaces.R;


public class Page1 extends Fragment implements View.OnClickListener {

    View view;
    private ImageButton ib_places, ib_profil, ib_favPlaces, ib_exit;

    public Page1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_page1, container, false);
        ib_places = (ImageButton) view.findViewById(R.id.ib_places);
        ib_places.setOnClickListener(this);
        ib_profil = (ImageButton) view.findViewById(R.id.ib_profil);
        ib_profil.setOnClickListener(this);
        ib_favPlaces = (ImageButton) view.findViewById(R.id.ib_favPlaces);
        ib_favPlaces.setOnClickListener(this);
        ib_exit = (ImageButton) view.findViewById(R.id.ib_exit);
        ib_exit.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_places :
                Intent i1 = new Intent(getActivity(), PlacesActivity.class);
                startActivity(i1);
                break;
            case R.id.ib_profil :
                Intent i2 = new Intent(getActivity(),ActivityProfile.class);
                startActivity(i2);
                break;
            case R.id.ib_favPlaces :
                Intent i3 = new Intent(getActivity(), FavActivity.class);
                startActivity(i3);
                break;
            case R.id.ib_exit :
                System.exit(0);
                break;
        }
    }
}
