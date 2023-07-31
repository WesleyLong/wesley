package com.example.demo.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MyTask {

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 任务执行规则时间
     */
    private String expression;
}