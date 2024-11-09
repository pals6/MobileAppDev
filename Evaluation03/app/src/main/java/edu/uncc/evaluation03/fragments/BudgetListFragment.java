package edu.uncc.evaluation03.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.uncc.evaluation03.R;
import edu.uncc.evaluation03.databinding.FragmentBudgetListBinding;
import edu.uncc.evaluation03.models.Expense;

public class BudgetListFragment extends Fragment {
    public BudgetListFragment() {
        // Required empty public constructor
    }
    private ArrayList<Expense> mExpenses = new ArrayList<>();
    FragmentBudgetListBinding binding;
    BudgetAdapter budgetAdapter;

    int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBudgetListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        budgetAdapter=new BudgetAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(budgetAdapter);
        String operation;

        mExpenses.clear();
        mExpenses.addAll(mListener.getAllExpenses());
        double totalBudget;
//        Log.d("total count", String.valueOf(mExpenses.size()));
//        for (i=0; i< mExpenses.size();i++){
//            Log.d("amount"+i, String.valueOf(mExpenses.get(i).getAmount()));
//            totalBudget+= mExpenses.get(i).getAmount();
//        }
        binding.textViewBudgetTotal.setText(String.valueOf(totalBudget()));

        binding.buttonAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoAddExpense();
                binding.textViewBudgetTotal.setText(String.valueOf(totalBudget()));

            }
        });

        binding.buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clearAllExpenses();
                mExpenses.clear();
                binding.textViewBudgetTotal.setText(String.valueOf(totalBudget()));

                //inding.textViewBudgetTotal.setText();
                budgetAdapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSortAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpenses.sort( (expense1, expense2) -> Double.compare(expense2.getAmount(), expense1.getAmount()));
                //mExpenses.sort((o1, o2) -> Double.compare(o2.getAmount(), o1.getAmount()));

                //mExpenses.addAll(mListener.getAllExpenses());
                budgetAdapter.notifyDataSetChanged();
            }
        });

        binding.imageViewSortDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    BudgetListListener mListener;
    public double totalBudget(){
        double totalBudget=0;
        Log.d("total count", String.valueOf(mExpenses.size()));
        for (i=0; i< mExpenses.size();i++){
            Log.d("amount"+i, String.valueOf(mExpenses.get(i).getAmount()));
            totalBudget+= mExpenses.get(i).getAmount();
        }

        return totalBudget;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BudgetListListener) {
            mListener = (BudgetListListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement BudgetListListener");
        }
    }
    class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder>{
        @NonNull
        @Override
        public BudgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.budget_row_item,parent,false);
            BudgetViewHolder billViewHolder=new BudgetViewHolder(view);
            return billViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull BudgetViewHolder holder, int position) {
            Expense expense=mExpenses.get(position);
            holder.setupUI(expense);
        }

        @Override
        public int getItemCount() {
            return mExpenses.size();
        }

        class BudgetViewHolder extends RecyclerView.ViewHolder{
            TextView textViewBudgetTitleRow,textViewAmountROw,textViewBudgetCategoryRow,textViewPriorityRow;
            ImageView imageViewDelete;
            Expense mExpense;
            public BudgetViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewBudgetTitleRow=itemView.findViewById(R.id.textViewBudgetTitleRow);
                textViewAmountROw=itemView.findViewById(R.id.textViewAmountROw);
                textViewBudgetCategoryRow=itemView.findViewById(R.id.textViewBudgetCategoryRow);
                textViewPriorityRow=itemView.findViewById(R.id.textViewPriorityRow);
                imageViewDelete=itemView.findViewById(R.id.imageViewDelete);

                imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.deleteExpense(mExpense);
                        mExpenses.remove(mExpense);
                        binding.textViewBudgetTotal.setText(String.valueOf(totalBudget()));
                        notifyDataSetChanged();
                    }
                });


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.gotoExpenseSummary(mExpense);
                    }
                });
            }
            public void setupUI(Expense expense){
                mExpense=expense;
                textViewBudgetTitleRow.setText(expense.getName());
                textViewAmountROw.setText(String.valueOf(expense.getAmount()));
                textViewBudgetCategoryRow.setText(expense.getCategory());
                textViewPriorityRow.setText(expense.getPriority().getName());

            }
        }
    }

    public interface BudgetListListener {
        void gotoAddExpense();
        void deleteExpense(Expense expense);
        void clearAllExpenses();
        ArrayList<Expense> getAllExpenses();
        void gotoExpenseSummary(Expense expense);
    }
}