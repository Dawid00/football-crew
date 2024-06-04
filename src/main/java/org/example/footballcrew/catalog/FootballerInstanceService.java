package org.example.footballcrew.catalog;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class FootballerInstanceService {
  private final FootballerInstanceRepository repository;

  public void createFootballer() {
    repository.save(new FootballerInstance());
  }

  public FootballerInstance getFootballer(UUID id) {
    return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found footballer with id:" + id));
  }

  @Transactional
  public void injured(InjuryReportDto injuryReportDto) {
    var footballer = repository.findById(injuryReportDto.footballerId()).orElseThrow(() -> new RuntimeException("Not found footballer with id:" + injuryReportDto.footballerId()));
    footballer.getInjured(injuryReportDto.till(), injuryReportDto.name(), injuryReportDto.reason());
  }

}
