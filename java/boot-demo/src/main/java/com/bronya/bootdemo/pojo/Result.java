package com.bronya.bootdemo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<DataType> {
    private int code;
    private String msg;
    private DataType data; // human friendly

    public static <DataType> Result<DataType> success(DataType data) {
        return new Result<>(200, "success", data);
    }

    public static <DataType> Result<DataType> fail(DataType data) {
        return new Result<>(500, "fail", data);
    }
}
