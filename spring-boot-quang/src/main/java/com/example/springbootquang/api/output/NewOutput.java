package com.example.springbootquang.api.output;

import com.example.springbootquang.dto.NewDTO;

import java.util.ArrayList;
import java.util.List;

public class NewOutput {// API trả về client
    //- totalPage: tổng số trang; VD: 40 item -> 1 page 10 item ->> 4 trang
    //- page: trang hiện tại
    //List<NewDTO>
    private int page;
    private int totalPage;
    private List<NewDTO> listResult = new ArrayList<>();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<NewDTO> getListResult() {
        return listResult;
    }

    public void setListResult(List<NewDTO> listResult) {
        this.listResult = listResult;
    }
}

