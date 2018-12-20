package com.qcy.service;

import java.util.List;

/**
 * @ClassName ServiceMultiResult
 * @Description TODO
 * @Author qiancy
 * @Date 2018/12/18 15:48
 * @Version 1.0
 **/
public class ServiceMultiResult<T> {
    private long total;

    public ServiceMultiResult(long total, List<T> result) {
        this.total = total;
        this.result = result;
    }

    private List<T> result;



    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public int getResultSize(){
        if(this.result == null){
            return 0;
        }
        return this.result.size();
    }
}
