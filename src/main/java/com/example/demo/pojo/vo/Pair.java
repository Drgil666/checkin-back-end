package com.example.demo.pojo.vo;

import lombok.Data;

/**
 * @author DrGilbert
 * Pair工具类、存一对键值
 */
@Data
public class Pair<S, T> {
    private S first;
    private T second;
}
