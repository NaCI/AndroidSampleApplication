package com.naci.mytestapplication.ui.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
//import androidx.databinding.library.baseAdapters.BR;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder> {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public MyViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindListener(BaseItemClickListener listener) {
            binding.setVariable(com.naci.mytestapplication.BR.listener, listener);
            binding.executePendingBindings();
        }

        public void bind(Object obj) {
            binding.setVariable(com.naci.mytestapplication.BR.item, obj);
            binding.executePendingBindings();
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForType(viewType), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(getDataAtPosition(position));
    }

    public abstract Object getDataAtPosition(int position);

    public abstract int getLayoutIdForType(int viewType);

    //public abstract BaseItemClickListener getOnItemClickListener();

}
