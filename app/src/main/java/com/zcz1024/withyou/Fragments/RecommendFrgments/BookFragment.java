package com.zcz1024.withyou.Fragments.RecommendFrgments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zcz1024.withyou.Activity.recommend.BookRcmActivity;
import com.zcz1024.withyou.Adapter.RecommendAdapter.BookAdapter;
import com.zcz1024.withyou.Fragments.BaseFragment;
import com.zcz1024.withyou.Presenter.NetWorkData.RetrofitFactory;
import com.zcz1024.withyou.Presenter.ResultData;
import com.zcz1024.withyou.Presenter.Service.RecommendDataService;
import com.zcz1024.withyou.Presenter.Service.SearchDataService;
import com.zcz1024.withyou.R;
import com.zcz1024.withyou.pojoVO.BookTjVo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class BookFragment extends BaseFragment{
    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<BookTjVo> bookTjVos;
    private OnItemClickListener listener;
    private View.OnClickListener clickListener;
    private EditText et_search;
    private ImageView img_search;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tj_fragment, container, false);

        recyclerView = view.findViewById(R.id.recyclerview_tj);

        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(),2));

        //设置适配器
        bookAdapter = new BookAdapter(null);
        listener = new OnItemClickListener(){

            @Override
            public void itemClick(int position, View view) {
                switch (view.getId()){
                    case R.id.img_tj_book_firstpic:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("bookInfo",bookTjVos.get(position));
                        Intent intent = new Intent(getContext(), BookRcmActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        };

        bookAdapter.setOnItemClickListener(listener);

        //设置下划线
        DividerItemDecoration itemDecoration = new DividerItemDecoration
                (getContext(),DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.item_divider));
        recyclerView.addItemDecoration(itemDecoration);

        /*//全屏水滴刷新
        RefreshUtil.refresh(getContext(), view, R.id.refresh_allteam,
                new OnRefreshListener() {
                    @Override
                    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                        loadData();
                        refreshLayout.finishRefresh(1000);
                    }
                });*/

        recyclerView.setAdapter(bookAdapter);

        et_search = view.findViewById(R.id.et_search_tj);
        img_search = view.findViewById(R.id.img_search_tj);

        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchData();
            }
        });

        searchData();
        return view;
    }

       /* private void  initData(){
            bookTjVos = new ArrayList<>();
            for (int i=0;i<8;i++){
                BookTjVo book = new BookTjVo();
                bookTjVos.add(book);
            }
            initData(bookTjVos);
    }*/

    private void searchData(){
        String keyword = et_search.getText().toString();
        addDisposable(
                RetrofitFactory.getRetrofit()
                .create(SearchDataService.class)
                .searchrcmbook(1,keyword,"book")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ResultData<BookTjVo>>() {
                    @Override
                    public void accept(ResultData<BookTjVo> resultData) throws Exception {
                        if (resultData.isSuccess()){
                            bookTjVos = resultData.getDatalist();
                            initData(bookTjVos);
                        }
                        Toast.makeText(getContext(),resultData.getMsg(),Toast.LENGTH_SHORT).show();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                })

        );
    }

    private void getBookData(){
        addDisposable(
                RetrofitFactory.getRetrofit()
                        .create(RecommendDataService.class)
                        .getBook(1,"book")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ResultData<BookTjVo>>() {
                            @Override
                            public void accept(ResultData<BookTjVo> books) throws Exception {
                                if (books.isSuccess()){
                                    bookTjVos = books.getDatalist();
                                    initData(bookTjVos);
                                }
                                Toast.makeText(getContext(),books.getMsg(),Toast.LENGTH_SHORT).show();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Toast.makeText(getContext(),"数据获取失败,错误原因："+throwable.getMessage(),Toast.LENGTH_LONG).show();
                            }
                        })

        );
    }

    private void initData(List bookTjVos){
        bookAdapter.setbooksData(bookTjVos);
    }
}
