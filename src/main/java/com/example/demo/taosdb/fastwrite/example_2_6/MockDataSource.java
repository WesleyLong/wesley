package com.example.demo.taosdb.fastwrite.example_2_6;

import java.util.Iterator;

/**
 * Generate test data
 */
class MockDataSource implements Iterator<String> {
    private final String tbNamePrefix;
    private final int tableCount;
    private final long maxRowsPerTable = 1000000000L;

    // 100 milliseconds between two neighbouring rows.
    long startMs = System.currentTimeMillis() - maxRowsPerTable * 100;
    private int currentRow = 0;
    private int currentTbId = -1;

    // mock values
    String[] location = {"California.LosAngeles", "California.SanDiego", "California.SanJose", "California.Campbell", "California.SanFrancisco"};
    float[] current = {8.8f, 10.7f, 9.9f, 8.9f, 9.4f};
    int[] voltage = {119, 116, 111, 113, 118};
    float[] phase = {0.32f, 0.34f, 0.33f, 0.329f, 0.141f};

    public MockDataSource(String tbNamePrefix, int tableCount) {
        this.tbNamePrefix = tbNamePrefix;
        this.tableCount = tableCount;
    }

    @Override
    public boolean hasNext() {
        currentTbId += 1;
        if (currentTbId == tableCount) {
            currentTbId = 0;
            currentRow += 1;
        }
        return currentRow < maxRowsPerTable;
    }

    @Override
    public String next() {
        long ts = startMs + 100L * currentRow;
        int groupId = currentTbId % 5 == 0 ? currentTbId / 5 : currentTbId / 5 + 1;
        return tbNamePrefix + "_" + currentTbId + "," + // tbName
                ts + ',' + // ts
                current[currentRow % 5] + ',' + // current
                voltage[currentRow % 5] + ',' + // voltage
                phase[currentRow % 5] + ',' + // phase
                location[currentRow % 5] + ',' + // location
                groupId;
    }
}

