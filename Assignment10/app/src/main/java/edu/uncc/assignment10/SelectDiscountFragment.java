package edu.uncc.assignment10;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;

import edu.uncc.assignment10.databinding.FragmentSelectDiscountBinding;


public class SelectDiscountFragment extends Fragment {
    public SelectDiscountFragment() {
        // Required empty public constructor
    }

    FragmentSelectDiscountBinding binding;
    String[] discounts={"10","15","18","Custom"};
    ArrayAdapter<String> adapter;
    int discProgress;
    double discount;

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
        adapter=new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,android.R.id.text1,discounts);
        binding.listView.setAdapter(adapter);
        discProgress=25;

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
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if("Custom".equals(discounts[position])){
                    discount=discProgress;
                    mListener.onDiscountSelected(discount);
                }
                else {
                    discount= Double.parseDouble(discounts[position]);
                    mListener.onDiscountSelected(discount);
                }
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

    interface SelectDiscountListener {
        void onDiscountSelected(double discount);
        void onCancelSelectDiscount();
    }
}