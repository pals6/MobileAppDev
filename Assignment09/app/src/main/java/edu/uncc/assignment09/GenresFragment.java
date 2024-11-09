package edu.uncc.assignment09;

import android.content.Context;
import android.os.Bundle;
import edu.uncc.assignment09.Book;
import edu.uncc.assignment09.Data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import edu.uncc.assignment09.databinding.FragmentGenresBinding;

public class GenresFragment extends Fragment {
    public GenresFragment() {
        // Required empty public constructor
    }

    FragmentGenresBinding binding;

    //ArrayList<String> genres;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGenresBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    ArrayList<String> mGenres = Data.getAllGenres();
    ArrayAdapter<String> adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //genres=Data.getAllGenres();

        adapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,android.R.id.text1,mGenres);
        binding.listView.setAdapter(adapter);
        getActivity().setTitle("Genres");

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String genre=mGenres.get(position);
                mListener.gotoBooksForGenre(genre);
            }
        });
    }

    GenresListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof GenresListener) {
            mListener = (GenresListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement GenresListener");
        }
    }

    interface GenresListener {
        void gotoBooksForGenre(String genre);
    }
}