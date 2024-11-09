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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import edu.uncc.assignment12.databinding.FragmentBillsBinding;

public class BillsFragment extends Fragment {
    public BillsFragment() {
        // Required empty public constructor
    }

    FragmentBillsBinding binding;

    private ArrayList<Bill> mBills = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    BillAdapter billAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentBillsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        billAdapter=new BillAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(billAdapter);

        mBills.clear();
        mBills.addAll(mListener.getAllBills());

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

    class BillAdapter extends RecyclerView.Adapter<BillAdapter.BillViewHolder>{

        @NonNull
        @Override
        public BillAdapter.BillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.bill_row_item,parent,false);
            BillViewHolder billViewHolder=new BillViewHolder(view);
            return billViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull BillAdapter.BillViewHolder holder, int position) {
            Bill bill=mBills.get(position);
            holder.setupUI(bill);
        }

        @Override
        public int getItemCount() {
            return mBills.size();
        }
        class BillViewHolder extends RecyclerView.ViewHolder{
            TextView textViewBillTitleRow,textViewBillAmountRow,textViewDiscountRow,textViewTotalBillAmountRow,textViewDateRow,textViewCategoryRow;
            ImageView imageViewDelete,imageViewEdit;
            Bill mBill;
            public BillViewHolder(@NonNull View itemView) {
                super(itemView);

                textViewBillTitleRow=itemView.findViewById(R.id.textViewBillTitleRow);
                textViewBillAmountRow=itemView.findViewById(R.id.textViewBillAmountRow);
                textViewDiscountRow=itemView.findViewById(R.id.textViewDiscountRow);
                textViewTotalBillAmountRow=itemView.findViewById(R.id.textViewTotalBillAmountRow);
                textViewDateRow=itemView.findViewById(R.id.textViewDateRow);
                textViewCategoryRow=itemView.findViewById(R.id.textViewCategoryRow);
                imageViewEdit=itemView.findViewById(R.id.imageViewEdit);
                imageViewDelete=itemView.findViewById(R.id.imageViewDelete);
                imageViewEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.goToEditBill(mBill);
                    }
                });
                imageViewDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.deleteSelectedBill(mBill);
                        mBills.remove(mBill);
                        notifyDataSetChanged();
                    }
                });




                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.goToBillSummary(mBill);
                    }
                });

            }
            public void setupUI(Bill bill){
                mBill=bill;
                textViewBillTitleRow.setText(bill.getName());
                textViewBillAmountRow.setText(String.valueOf(bill.getAmount()));
                textViewDiscountRow.setText(String.valueOf(bill.getDiscount()));
                double totalAmount=bill.getAmount()*((100-bill.getDiscount())/100);
                textViewTotalBillAmountRow.setText(String.valueOf(totalAmount));
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                textViewDateRow.setText(sdf.format(bill.getBillDate()));
                textViewCategoryRow.setText(bill.getCategory());
            }
        }
    }


    interface BillsListener {
        void goToBillSummary(Bill bill);
        void goToEditBill(Bill bill);
        ArrayList<Bill> getAllBills();
        void gotoCreateBill();
        void clearAllBills();
        void deleteSelectedBill(Bill bill);
    }
}