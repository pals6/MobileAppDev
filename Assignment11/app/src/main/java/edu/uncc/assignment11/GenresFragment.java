package edu.uncc.assignment11;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import edu.uncc.assignment11.databinding.FragmentGenresBinding;

public class GenresFragment extends Fragment {
    public GenresFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    FragmentGenresBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGenresBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    genreAdapter adapter;
    ArrayList<String> mGenres = Data.getAllGenres();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Genres");

        layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(layoutManager);

        adapter = new genreAdapter();
        binding.recyclerView.setAdapter(adapter);
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
    class genreAdapter extends RecyclerView.Adapter<genreAdapter.genreViewHolder>{
        @NonNull
        @Override
        public genreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=getLayoutInflater().inflate(R.layout.simple_row_item,parent,false);
            return new genreViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull genreViewHolder holder, int position) {
            String genre=mGenres.get(position);
            holder.setupUI(genre);
        }

        @Override
        public int getItemCount() {
            return mGenres.size();
        }

        class genreViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            String mgenre;
            public genreViewHolder(@NonNull View itemView) {
                super(itemView);
                textView=itemView.findViewById(R.id.textView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.gotoBooksForGenre(mgenre);
                    }
                });
            }
            public void setupUI(String genre){
                textView.setText(genre);
                mgenre=genre;
            }
        }
    }
}