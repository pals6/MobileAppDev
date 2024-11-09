package edu.uncc.assignment09;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.uncc.assignment09.databinding.FragmentBooksBinding;

public class BooksFragment extends Fragment {
    private static final String ARG_PARAM_GENRE = "ARG_PARAM_GENRE";
    private String mGenre;
    BookAdapter bookAdapter;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //bookAdapter=new BookAdapter(getActivity(),mBooks);
        getActivity().setTitle("Books");
        binding.textViewSetGenre.setText(mGenre);
        mBooks.clear();
        mBooks.addAll(Data.getBooksByGenre(mGenre));
        bookAdapter=new BookAdapter(getActivity(),mBooks);
        binding.booksListView.setAdapter(bookAdapter);
        Log.d("mBooks", "onViewCreated: "+mBooks.get(0));
        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeBooks();
            }
        });
        binding.booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book=mBooks.get(position);
                mListener.gotoBookDetails(book);
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
    public class BookAdapter extends ArrayAdapter<Book> {
        public BookAdapter(@NonNull Context context, @NonNull List<Book> objects) {
            super(context, R.layout.book_row_item, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView==null){
                convertView= LayoutInflater.from(getContext()).inflate(R.layout.book_row_item,parent,false);
            }

            Book book=mBooks.get(position);

            TextView textViewBookTitleOP=convertView.findViewById(R.id.textViewBookTitleOP);
            TextView textViewAuthorNameOP=convertView.findViewById(R.id.textViewAuthorNameOP);
            TextView textViewGenreOP=convertView.findViewById(R.id.textViewGenreOP);
            TextView textViewYearOP=convertView.findViewById(R.id.textViewYearOP);
            Log.d("bookTitle", book.getTitle());

            textViewBookTitleOP.setText(book.getTitle());
            textViewAuthorNameOP.setText(book.getAuthor());
            textViewGenreOP.setText(book.getGenre());
            textViewYearOP.setText(String.valueOf(book.getYear()));
            return convertView;
        }
    }

}