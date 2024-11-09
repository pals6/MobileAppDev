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
import android.widget.SeekBar;
import android.widget.TextView;

import edu.uncc.assignment12.databinding.FragmentSelectDiscountBinding;


public class SelectDiscountFragment extends Fragment {
    public SelectDiscountFragment() {
        // Required empty public constructor
    }

    FragmentSelectDiscountBinding binding;
    String[] discounts={"10","15","18","Custom"};
    DiscountAdapter adapter;
    int discProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSelectDiscountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.seekBar.setMax(50);
        binding.seekBar.setProgress(25);
        discProgress=25;
        adapter=new DiscountAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                discProgress=progress;
                binding.textViewSeekBarProgress.setText(progress + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        binding.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCancelSelectDiscount();
            }
        });
    }

    SelectDiscountListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SelectDiscountListener) {
            mListener = (SelectDiscountListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement SelectDiscountListener");
        }
    }
    class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder>{

        @NonNull
        @Override
        public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=getLayoutInflater().inflate(R.layout.discount_row_item,parent,false);
            return new DiscountViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
            String discount=discounts[position];
            holder.setupUI(discount);
        }

        @Override
        public int getItemCount() {
            return discounts.length;
        }

        class DiscountViewHolder extends RecyclerView.ViewHolder{
            TextView textViewDiscountsList;
            String mDiscount;
            public DiscountViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewDiscountsList=itemView.findViewById(R.id.textViewDiscountsList);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if("Custom".equals(mDiscount)){
                            mListener.onDiscountSelected(discProgress);
                        }
                        else {
                            mListener.onDiscountSelected(Double.parseDouble(mDiscount));
                        }
                    }
                });

            }
            public void setupUI(String discount){
                mDiscount=discount;
                if (discount.equals("Custom"))
                {
                    textViewDiscountsList.setText(discount);
                }
                else {
                    textViewDiscountsList.setText(discount+"%");
                }
            }
        }

    }

    interface SelectDiscountListener {
        void onDiscountSelected(double discount);
        void onCancelSelectDiscount();
    }
}