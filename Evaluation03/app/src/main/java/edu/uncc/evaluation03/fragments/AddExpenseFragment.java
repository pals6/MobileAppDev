package edu.uncc.evaluation03.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.uncc.evaluation03.R;
import edu.uncc.evaluation03.databinding.FragmentAddExpenseBinding;
import edu.uncc.evaluation03.models.Expense;
import edu.uncc.evaluation03.models.Priority;

public class AddExpenseFragment extends Fragment {
    public AddExpenseFragment() {
        // Required empty public constructor
    }

    FragmentAddExpenseBinding binding;
    String selectedCategory;
    Priority selectedPriority;

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public void setSelectedPriority(Priority selectedPriority) {
        this.selectedPriority = selectedPriority;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddExpenseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(selectedCategory != null){
            binding.textViewSelectedCategory.setText(selectedCategory);
        } else {
            binding.textViewSelectedCategory.setText("N/A");
        }

        if(selectedPriority != null){
            binding.textViewSelectedPriority.setText(selectedPriority.getName());
        } else {
            binding.textViewSelectedPriority.setText("N/A");
        }

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancelAddExpense();
            }
        });

        binding.buttonSelectCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSelectCategory();
            }
        });

        binding.buttonSelectPriority.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSelectPriority();
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.editTextName.getText().toString();
                String amountStr = binding.editTextAmount.getText().toString();
                double amount=0;
                if(name.isEmpty()){
                    Toast.makeText(getActivity(), "Enter valid  name!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try{
                    amount = Double.parseDouble(amountStr);
                } catch (NumberFormatException e){
                    Toast.makeText(getActivity(), "Enter valid  amount !", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(selectedCategory == null){
                    Toast.makeText(getActivity(), "Select a valid category!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (selectedPriority==null){
                    Toast.makeText(getActivity(), "Select a valid priority!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Expense expense = new Expense(name, selectedCategory, amount, selectedPriority);
                Log.d("expense", expense.getName());
                mListener.onAddExpense(expense);

            }
        });
    }


    AddExpenseListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof AddExpenseListener) {
            mListener = (AddExpenseListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement AddExpenseListener");
        }
    }

    public interface AddExpenseListener {
        void onAddExpense(Expense expense);
        void gotoSelectCategory();
        void gotoSelectPriority();
        void onCancelAddExpense();
    }
}