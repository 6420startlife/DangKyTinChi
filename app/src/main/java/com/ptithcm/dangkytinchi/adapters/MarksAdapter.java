package com.ptithcm.dangkytinchi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ptithcm.dangkytinchi.R;
import com.ptithcm.dangkytinchi.response.ResponseMarks;

import java.util.List;

public class MarksAdapter extends RecyclerView.Adapter<MarksAdapter.ViewHolder> {
    private List<ResponseMarks> responseMarksList;

    public MarksAdapter(List<ResponseMarks> responseMarksList) {
        this.responseMarksList = responseMarksList;
    }

    @NonNull
    @Override
    public MarksAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marks, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MarksAdapter.ViewHolder holder, int position) {
        setEvent(holder, position);
    }

    private void setEvent(ViewHolder holder, int position) {
        ResponseMarks responseMarks = responseMarksList.get(position);
        holder.tvTenMonHoc.setText(responseMarks.getTenMH());
        holder.tvDiemTrungBinh.setText(String.valueOf(responseMarks.averageDiem()));
    }

    @Override
    public int getItemCount() {
        return responseMarksList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenMonHoc, tvDiemTrungBinh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            setControl(itemView);
        }

        private void setControl(View itemView) {
            tvTenMonHoc = itemView.findViewById(R.id.tvTenMonHoc);
            tvDiemTrungBinh = itemView.findViewById(R.id.tvDiemTrungBinh);
        }
    }
}
