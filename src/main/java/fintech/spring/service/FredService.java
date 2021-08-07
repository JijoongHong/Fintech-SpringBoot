package fintech.spring.service;

import fintech.spring.dto.Observation;
import fintech.spring.entity.FredData;
import fintech.spring.repository.FredRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FredService {

    @Value("${fred.apikey}")//spring, not lombok
    String fredApiKey;

    //thread-safe values
    final String baseUrl = "https://api.stlouisfed.org";
    final public static ParameterizedTypeReference<Map<String,Object>> mapTypeReference = new ParameterizedTypeReference<>(){};
    final public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM=dd");
    //여러 메소드에서 사용가능

    final FredRepository fredRepository;

    @Autowired
    public FredService(FredRepository fredRepository) {
        this.fredRepository = fredRepository;
    }

    private Double safeParseDouble(String str){
        try {
            return Double.parseDouble(str);
        }catch (Throwable t){
            return -999d;
        }
    }

    private Mono<Map<String, Object>> callUsbond10Y(String from, String to){
        return WebClient.create(baseUrl)//observation을 계속 흘려주는 객체 생성
                .method(HttpMethod.GET)
                .uri(it->it.path("/fred/series/observations")
                        .queryParam("series_id", "DGS10")
                        .queryParam("units", "lin")
                        .queryParam("file_type", "json")
                        .queryParam("api_key", fredApiKey)
                        .queryParam("observation_start", "2020-12-01")
                        .queryParam("observation_end", "2020-12-31")
                        .build())
                .retrieve() //응답 받아서
                .bodyToMono(mapTypeReference)
    }

    public Flux<Observation> getUsGovernmentBond10Y(){
        Flux<Observation> data = WebClient.create(baseUrl)//observation을 계속 흘려주는 객체 생성
                .method(HttpMethod.GET)
                .uri(it->it.path("/fred/series/observations")
                        .queryParam("series_id", "DGS10")
                        .queryParam("units", "lin")
                        .queryParam("file_type", "json")
                        .queryParam("api_key", fredApiKey)
                        .queryParam("observation_start", "2020-12-01")
                        .queryParam("observation_end", "2020-12-31")
                        .build())
                .retrieve() //응답 받아서
                .bodyToMono(Map.class)//.block() ->async // 모노맵으로 바꾸고
                .flatMapMany(it ->{ // 플럭스로 변경
                    List<Map<String,String>> list = (List<Map<String,String>>) it.get("obervations");
                    return Flux.fromStream( // 스트림에서 플럭스 빌드
                            list.stream().map(m -> new Observation(m.get("date"), safeParseDouble(m.get("value"))))
                    );
                });

        return  data;
    }

    @Scheduled(fixedDelay = 5000)
    public void updateFredDataRegularly(){
        log.error("now updating freddata");
        String from = formatter.format(LocalDateTime.now().minusMonths(3));
        String to = formatter.format(LocalDateTime.now());
        Map<String, Object> data = callUsbond10Y(from, to).block(Duration.ofMinutes(5));
        List<Map<String, String>> list = (List<Map<String, String>>) data.get("observations");
        fredRepository.saveAll(
                list.stream()
                .map(m -> new FredData("DGS10", m.get("date"), safeParseDouble(m.get("value"))))
                .collect(Collectors.toList()));
        }

    public List<Observation> getStoredFredData(String seriesId, String from, String to){
        return fredRepository.findAllBySeriesIdAndObservationDateAfterObservationDateBefore(seriesId, from, to)
                .stream().map(Observation::fromFredData).collect(Collectors.toList());
    }

}
