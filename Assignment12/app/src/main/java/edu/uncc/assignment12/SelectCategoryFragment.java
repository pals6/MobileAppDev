package edu.uncc.assignment12;

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
import android.widget.ArrayAdapter;
import android.widget.TextView;

import edu.uncc.assignment12.databinding.FragmentSelectCategoryBinding;


public class SelectCategoryFragment extends Fragment {

    String[] mCategories = {"Housing", "Transportation", "Food", "Health", "Other"};
    CategoryAdapter adapter;
    String category;
    RecyclerView recyclerView;

    public SelectCategoryFragment() {
        // Required empty public constructor
    }

    FragmentSelectCategoryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectCategoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter=new CategoryAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancelSelectCategory();
            }
        });


    }

    SelectCategoryListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectCategoryListener) {
            mListener = (SelectCategoryListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SelectCategoryListener");
        }
    }
    class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

        @NonNull
        @Override
        public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= getLayoutInflater().inflate(R.layout.category_row_item,parent,false);
            return  new CategoryViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
            String category=mCategories[position];
            holder.setupUI(category);

        }

        @Override
        public int getItemCount() {
            return mCategories.length;
        }
        class CategoryViewHolder extends RecyclerView.ViewHolder{
            TextView textViewCategoriesList;
            String mCategory;
            public CategoryViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewCategoriesList=itemView.findViewById(R.id.textViewCategoriesList);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.selectCategory(mCategory);
                    }
                });
            }
            public void setupUI(String category ){
                textViewCategoriesList.setText(category);
                mCategory=category;
            }
        }
    }

    interface SelectCategoryListener {
        void selectCategory(String category);
        void onCancelSelectCategory();
    }

}