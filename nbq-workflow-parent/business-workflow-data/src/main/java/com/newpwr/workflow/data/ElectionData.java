package com.newpwr.workflow.data;

import com.newpwr.workflow.data.enums.ElectionDataTypeEnum;
import com.newpwr.workflow.data.register.SlaveRegister;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author niubq
 * @date 2020/6/30 11:10
 * @description
 */
public class ElectionData implements Serializable {
    private ElectionDataTypeEnum type;
    private ConcurrentHashMap<String, SlaveRegister> slaveMap;
    private int slaveCount;
}
