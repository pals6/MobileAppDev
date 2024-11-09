package edu.uncc.assignment10;

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

import edu.uncc.assignment10.databinding.FragmentBillsBinding;

public class BillsFragment extends Fragment {
    public BillsFragment() {
        // Required empty public constructor
    }

    String sortAttribute = "Date", sortOrder = "ASC";

    public void setSortItems(String sortAttribute, String sortOrder) {
        this.sortAttribute = sortAttribute;
        this.sortOrder = sortOrder;
    }

    FragmentBillsBinding binding;

    private ArrayList<Bill> mBills = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBillsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    BillAdapter billAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBills.clear();
        mBills.addAll(mListener.getAllBills());
        billAdapter=new BillAdapter(getActivity(),mBills);
        binding.listView.setAdapter(billAdapter);
        billAdapter.notifyDataSetChanged();
        Log.d("BillsFragment", "Number of bills: " + mBills.size());


        binding.textViewSortedBy.setText("Sorted By " + sortAttribute + " (" + sortOrder + ")");

        binding.buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.clearAllBills();
                mBills.clear();
                billAdapter.notifyDataSetChanged();
            }
        });

        binding.buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoCreateBill();
            }
        });

        binding.buttonSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoSortSelection();
            }
        });
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.goToBillSummary(mBills.get(position));
            }
        });
    }

    BillsListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BillsListener) {
            mListener = (BillsListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement BillsListener");
        }
    }

    interface BillsListener {
        void goToBillSummary(Bill bill);
        ArrayList<Bill> getAllBills();
        void gotoCreateBill();
        void gotoSortSelection();
        void clearAllBills();
    }

    public class BillAdapter extends ArrayAdapter<Bill>{

        public BillAdapter(@NonNull Context context, @NonNull List<Bill> objects) {
            super(context, R.layout.bill_row_item, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView==null){
                convertView=LayoutInflater.from(getContext()).inflate(R.layout.bill_row_item,parent,false);
            }
            TextView textViewBillTitleRow=convertView.findViewById(R.id.textViewBillTitleRow);
            TextView textViewBillAmountRow= convertView.findViewById(R.id.textViewBillAmountRow);
            TextView textViewDiscountRow=convertView.findViewById(R.id.textViewDiscountRow);
            TextView textViewTotalBillAmountRow =convertView.findViewById(R.id.textViewTotalBillAmountRow);
            TextView textViewDateRow=convertView.findViewById(R.id.textViewDateRow);
            TextView textViewCategoryRow=convertView.findViewById(R.id.textViewCategoryRow);
            Bill bill=mBills.get(position);
            textViewBillTitleRow.setText(bill.getName());
            textViewBillAmountRow.setText(String.valueOf(bill.getAmount()));
            textViewDiscountRow.setText(String.valueOf(bill.getDiscount()));
            double totalAmount=bill.getAmount()*((100-bill.getDiscount())/100);
            textViewTotalBillAmountRow.setText(String.valueOf(totalAmount));
            textViewDateRow.setText(String.valueOf(bill.getBillDate()));
            textViewCategoryRow.setText(bill.getCategory());
            return convertView;

        }

    }

}