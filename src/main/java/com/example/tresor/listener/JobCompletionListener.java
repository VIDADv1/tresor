package com.example.tresor.listener;

import com.example.tresor.models.Adventurer;
import com.example.tresor.models.Cell;
import com.example.tresor.models.Map;
import com.example.tresor.singleton.TresorSingleton;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.io.BufferedWriter;
import java.io.FileWriter;

@Slf4j
public class JobCompletionListener implements JobExecutionListener {

    @Override
    public void afterJob(@NonNull JobExecution jobExecution) {
        TresorSingleton.simulate();
        Map map = TresorSingleton.getMap();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            if (map == null) {
                throw new RuntimeException("Map is null");
            }

            writer.write(String.format("C - %d - %d\n", map.getWidth(), map.getHeight()));
            for (int y = 0; y < map.getHeight(); y++) {
                for (int x = 0; x < map.getWidth(); x++) {
                    Cell cell = map.getCell(x, y);
                    if (cell.isMountain()) {
                        writer.write(String.format("M - %d - %d\n", x, y));
                    } else if (cell.getTreasures() > 0) {
                        writer.write(String.format("T - %d - %d - %d\n", x, y, cell.getTreasures()));
                    }
                }
            }
            for (Adventurer adventurer : map.getAdventurers()) {
                writer.write(adventurer.toString() + "\n");
            }
        } catch (Exception e) {
            log.error("An error occurred when writing the output file",e);
            jobExecution.setStatus(BatchStatus.FAILED);
        }
    }
}
