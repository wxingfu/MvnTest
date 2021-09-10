package com.wxf.wordexport.word;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.wxf.wordexport.entity.PriceData;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.List;

public class PriceTablePolicy extends DynamicTableRenderPolicy {

    @Override
    public void render(XWPFTable table, Object data) throws Exception {

        if (null == data) {
            return;
        }
        PriceData detailData = (PriceData) data;
        List<RowRenderData> dataPrice = detailData.getPrice();

        if (null != dataPrice) {
            for (int i = 0; i < dataPrice.size(); i++) {
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(0);
                for (int j = 0; j < 1; j++) {
                    insertNewTableRow.createCell();
                }
                TableRenderPolicy.Helper.renderRow(table.getRow(0), dataPrice.get(i));
            }
        }
    }
}
