package th.in.pnnutkung.nihongo.UnitSelectComponents;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import th.in.pnnutkung.nihongo.R;

public class UnitSelectionRecyclerViewHolder extends RecyclerView.ViewHolder {
    private TextView tvUnitNumber;

    public UnitSelectionRecyclerViewHolder(View itemView) {
        super(itemView);
        tvUnitNumber = itemView.findViewById(R.id.tv_unit_selection_unit_number);
    }

    public void setUnitNumber(int unitNumber) {
        tvUnitNumber.setText(String.format("%d", unitNumber));
    }
}
