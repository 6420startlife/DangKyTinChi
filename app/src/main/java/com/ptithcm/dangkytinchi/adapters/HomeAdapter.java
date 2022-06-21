package com.ptithcm.dangkytinchi.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.ptithcm.dangkytinchi.R;
import com.ptithcm.dangkytinchi.response.ResponseHome;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder>{
    private List<ResponseHome> responseHomeList;
    private MutableLiveData<Integer> countSelected;

    public MutableLiveData<Integer> getCountSelected() {
        return countSelected;
    }

    public List<ResponseHome> getResponseHomeList() {
        return responseHomeList;
    }

    public void setResponseHomeList(List<ResponseHome> responseHomeList) {
        this.responseHomeList = responseHomeList;
    }

    public HomeAdapter(List<ResponseHome> responseHomeList) {
        this.responseHomeList = responseHomeList;
        this.countSelected = new MutableLiveData<>();
        countSelected.postValue(0);
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new HomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        setEvent(holder, position);
    }

    private void setEvent(ViewHolder holder, int position) {
        ResponseHome responseHome = responseHomeList.get(position);
        holder.tvMaLTC.setText(responseHome.getMaLTC());
        holder.tvMonHoc.setText(responseHome.getMaMH());
        holder.ivCheckChoose.setVisibility(responseHome.isCheck() ? View.VISIBLE : View.GONE);
        holder.tvNienKhoa.setText("Niên Khoá : " + responseHome.getNienKhoa());
        holder.tvHocKy.setText("Học kỳ : " + responseHome.getHocKy());
        holder.layoutItemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(responseHome.isCheck()){
                    responseHome.setCheck(false);
                    countSelected.postValue(countSelected.getValue()-1);
                }else {
                    responseHome.setCheck(true);
                    countSelected.postValue(countSelected.getValue()+1);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return responseHomeList == null ? 0 : responseHomeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMonHoc, tvMaLTC, tvNienKhoa, tvHocKy;
        private ImageView ivCheckChoose;
        private ConstraintLayout layoutItemHome;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            setControl(itemView);
        }

        private void setControl(View itemView) {
            tvMaLTC = itemView.findViewById(R.id.tvMaLTC);
            tvMonHoc = itemView.findViewById(R.id.tvMonHoc);
            ivCheckChoose = itemView.findViewById(R.id.ivCheckChoose);
            layoutItemHome = itemView.findViewById(R.id.layoutItemHome);
            tvNienKhoa = itemView.findViewById(R.id.tvNienKhoa);
            tvHocKy = itemView.findViewById(R.id.tvHocKy);
        }
    }
}
