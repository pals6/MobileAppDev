package edu.uncc.evaluation03.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uncc.evaluation03.R;
import edu.uncc.evaluation03.databinding.FragmentAddExpenseBinding;
import edu.uncc.evaluation03.databinding.FragmentExpenseSummaryBinding;
import edu.uncc.evaluation03.models.Expense;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ExpenseSummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ExpenseSummaryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM_BILL = "ARG_PARAM_BILL";
    private Expense mExpense;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ExpenseSummaryFragment() {
        // Required empty public constructor
    }
    public static ExpenseSummaryFragment newInstance(Expense expense) {
        ExpenseSummaryFragment fragment = new ExpenseSummaryFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_BILL, expense);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mExpense = (Expense) getArguments().getSerializable(ARG_PARAM_BILL);
        }
    }

    FragmentExpenseSummaryBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExpenseSummaryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    ExpenseSummaryListener mListener;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.closeExpenseSummary();
            }
        });
        binding.textViewNameAndAmount.setText(mExpense.getName()+"("+mExpense.getAmount()+")");
        double categoryAmt=0;
        int i;
        //for (i=0;;)

        binding.textViewCategoryAndTotal.setText(mExpense.getCategory()+"("+"$" + String.format("%.2f", mExpense.getAmount()));
        binding.textViewPriorityName.setText(mExpense.getPriority().getName());
        binding.textViewPriorityDescription.setText(mExpense.getPriority().getDescription());


    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ExpenseSummaryListener) {
            mListener = (ExpenseSummaryListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement BillSummaryListener");
        }
    }
    public interface ExpenseSummaryListener {
        //void deleteBill(Expense expense);
        void closeExpenseSummary();
    }
}