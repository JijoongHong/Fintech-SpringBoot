package fintech.spring.controller;

import fintech.spring.dto.Observation;
import fintech.spring.service.FredService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/fred")
@RequiredArgsConstructor
@Slf4j
public class FredController {

    final FredService fredServce;

    @GetMapping(path="/interval", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Long> interval(){
        return Flux.interval(Duration.ofMillis(300));
    }

    @GetMapping(path="/usbond10y")
    public Flux<Observation> getUsBond10Y(){
        return fredServce.getUsGovernmentBond10Y();
    }

    @GetMapping(path="/usbond10y_stream")
    public Flux<Observation> getUsBond10YStream(){
        return Flux.interval(Duration.ofMillis(1000))
                .zipWith(fredServce.getUsGovernmentBond10Y())
                .map(t -> t.getT2());
    }

    @GetMapping(path="/query")
    public List<Observation> query(
            @RequestParam(required = false, defaultValue = "DGS10") String seriesId,
            @RequestParam String from, @RequestParam String to){
        return fredServce.getStoredFredData(SeriesId, from, to);
    }
    )

}
