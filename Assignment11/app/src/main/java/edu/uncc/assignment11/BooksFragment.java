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

import java.util.ArrayList;

import edu.uncc.assignment11.databinding.FragmentBooksBinding;

public class BooksFragment extends Fragment {
    private static final String ARG_PARAM_GENRE = "ARG_PARAM_GENRE";
    private String mGenre;

    public static BooksFragment newInstance(String genre) {
        BooksFragment fragment = new BooksFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_GENRE, genre);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mGenre = getArguments().getString(ARG_PARAM_GENRE);
        }
    }

    ArrayList<Book> mBooks = new ArrayList<>();

    public BooksFragment() {
        // Required empty public constructor
    }

    FragmentBooksBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBooksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    bookAdapter adapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Books");
        mBooks.clear();
        mBooks.addAll(Data.getBooksByGenre(mGenre));
        adapter=new bookAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeBooks();
            }
        });
    }

    BooksListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BooksListener) {
            mListener = (BooksListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement BooksListener");
        }
    }

    interface BooksListener{
        void closeBooks();
        void gotoBookDetails(Book book);
    }
    class bookAdapter extends RecyclerView.Adapter<bookAdapter.bookViewHolder>{
        @NonNull
        @Override
        public bookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=getLayoutInflater().inflate(R.layout.books_list,parent,false);
            return new bookViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull bookViewHolder holder, int position) {
            Book book=mBooks.get(position);
            holder.setUI(book);
        }

        @Override
        public int getItemCount() {
            return mBooks.size();
        }

        class bookViewHolder extends RecyclerView.ViewHolder{
            TextView textViewTitle, textViewAuthor, textViewGenre, textViewYear;
            Book mbook;
            public bookViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle=itemView.findViewById(R.id.textViewTitle);
                textViewAuthor=itemView.findViewById(R.id.textViewAuthor);
                textViewGenre=itemView.findViewById(R.id.textViewGenre);
                textViewYear=itemView.findViewById(R.id.textViewYear);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.gotoBookDetails(mbook);
                    }
                });
            }
            void setUI(Book book){
                mbook=book;
                textViewTitle.setText(mbook.getTitle());
                textViewAuthor.setText(mbook.getAuthor());
                textViewGenre.setText(mbook.getGenre());
                textViewYear.setText(String.valueOf(mbook.getYear()));
            }
        }
    }
}