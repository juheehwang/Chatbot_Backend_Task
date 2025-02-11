package com.task.backend.service;

import com.opencsv.CSVWriter;
import com.task.backend.entity.MemberActivity;
import com.task.backend.repository.MemberActivityRepository;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final MemberActivityRepository memberActivityRepository;

    /**
     * csv 파일 출력 메소드
     * @return
     * @throws IOException
     */
    public byte[] generateCsvReport() throws IOException {
        LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        LocalDateTime endOfDay = startOfDay.plusDays(1);
        List<MemberActivity> activities = memberActivityRepository.findByTimestampBetween(startOfDay, endOfDay);

        // CSV 파일 생성
        StringWriter stringWriter = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(stringWriter);
        String[] header = {"User ID", "Activity Type", "Timestamp"};
        csvWriter.writeNext(header);

        for (MemberActivity activity : activities) {
            String[] data = {
                String.valueOf(activity.getUserId()),
                activity.getActivityType().name(),
                activity.getTimestamp().toString()
            };
            csvWriter.writeNext(data);
        }

        return stringWriter.toString().getBytes(StandardCharsets.UTF_8);
    }


}
