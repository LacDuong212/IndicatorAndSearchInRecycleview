package com.example.indicatoranssearchinrecycleview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.indicatoranssearchinrecycleview.Adapter.IconAdapter;
import com.example.indicatoranssearchinrecycleview.Model.IconModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcIcon;
    private ArrayList<IconModel> arrayList1;
    private IconAdapter iconAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rcIcon = findViewById(R.id.rcIcon);
        arrayList1 = new ArrayList<>();
        arrayList1.add(new IconModel(R.drawable.icon1,"Hàng Chọn Giá Hời"));
        arrayList1.add(new IconModel(R.drawable.icon2,"Mã Giảm Giá"));
        arrayList1.add(new IconModel(R.drawable.icon3,"Miễn Phí Ship"));
        arrayList1.add(new IconModel(R.drawable.icon4,"Shopee Style Voucher 30%"));
        arrayList1.add(new IconModel(R.drawable.icon5,"Vourcher Giảm Đến 1 Triệu"));
        arrayList1.add(new IconModel(R.drawable.icon6,"Khung Giờ Săn Sale"));
        arrayList1.add(new IconModel(R.drawable.icon7,"Hàng Quốc Tế"));
        arrayList1.add(new IconModel(R.drawable.icon8,"Nạp Thẻ, Dịch Vụ & Hóa Đơn"));
        arrayList1.add(new IconModel(R.drawable.icon9,"Shopee Premium"));
        arrayList1.add(new IconModel(R.drawable.icon1,"Hàng Chọn Giá Hời"));
        arrayList1.add(new IconModel(R.drawable.icon2,"Mã Giảm Giá"));
        arrayList1.add(new IconModel(R.drawable.icon3,"Miễn Phí Ship"));
        arrayList1.add(new IconModel(R.drawable.icon4,"Shopee Style Voucher 30%"));
        arrayList1.add(new IconModel(R.drawable.icon5,"Vourcher Giảm Đến 1 Triệu"));
        arrayList1.add(new IconModel(R.drawable.icon6,"Khung Giờ Săn Sale"));
        arrayList1.add(new IconModel(R.drawable.icon7,"Hàng Quốc Tế"));
        arrayList1.add(new IconModel(R.drawable.icon8,"Nạp Thẻ, Dịch Vụ & Hóa Đơn"));
        arrayList1.add(new IconModel(R.drawable.icon9,"Shopee Premium"));

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,2, GridLayoutManager.HORIZONTAL,false);
        rcIcon.setLayoutManager(gridLayoutManager);
        iconAdapter = new IconAdapter(getApplicationContext(), arrayList1);
        rcIcon.setAdapter(iconAdapter);
        rcIcon.addItemDecoration(new LinePagerIndicatorDecoration(getApplicationContext()));

        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListener(newText);
                return true;
            }
        });
    }

    private void filterListener(String text) {
        List<IconModel> list = new ArrayList<>();
        for (IconModel iconModel : arrayList1) {
            if(iconModel.getDeccs().toLowerCase().contains(text.toLowerCase())) {
                list.add(iconModel);
            }
        }

        if(list.isEmpty()) {
            Toast.makeText(this,"Không có dữ liệu", Toast.LENGTH_SHORT).show();
        } else {
            iconAdapter.setListener(list);
        }
    }

    public class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {
        private final float DP;
        private final int colorActive;
        private final int colorInactive;
        private final int indicatorHeight;
        private final int indicatorWidth;
        private final int indicatorMargin;

        public LinePagerIndicatorDecoration(Context context) {
            DP = context.getResources().getDisplayMetrics().density;
            colorActive = 0xFFFFFFFF;
            colorInactive = 0x66FFFFFF;
            indicatorHeight = (int) (2 * DP);
            indicatorWidth = (int) (16 * DP);
            indicatorMargin = (int) (4 * DP);
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            int itemCount = parent.getAdapter().getItemCount();
            if (itemCount <= 1) {
                return;
            }

            // center horizontally, calculate width and subtract half from center
            float totalLength = indicatorWidth * itemCount;
            totalLength += indicatorMargin * (itemCount - 1);
            float paddingStart = (parent.getWidth() - totalLength) / 2f;

            // center vertically in the allotted space
            float paddingTop = parent.getHeight() - indicatorHeight * 2;

            drawIndicators(c, paddingStart, paddingTop, itemCount);
        }

        private void drawIndicators(Canvas c, float paddingStart, float paddingTop, int itemCount) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            for (int i = 0; i < itemCount; i++) {
                // draw inactive indicators
                paint.setColor(colorInactive);
                c.drawRect(
                        paddingStart + i * (indicatorWidth + indicatorMargin),
                        paddingTop,
                        paddingStart + i * (indicatorWidth + indicatorMargin) + indicatorWidth,
                        paddingTop + indicatorHeight,
                        paint);
            }

            paint.setColor(colorActive);
            int currentPosition = 0;
            c.drawRect(
                    paddingStart + currentPosition * (indicatorWidth + indicatorMargin),
                    paddingTop,
                    paddingStart + currentPosition * (indicatorWidth + indicatorMargin) + indicatorWidth,
                    paddingTop + indicatorHeight,
                    paint);
        }
    }
}