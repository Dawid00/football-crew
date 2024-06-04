//package org.example.footballcrew.infrastructure;
//
//import org.example.footballcrew.common.dto.CreateFootballerDto;
//import org.example.footballcrew.common.dto.ExclusionDto;
//import org.example.footballcrew.common.dto.FootballerDto;
//import org.example.footballcrew.common.dto.TeamDto;
//import org.example.footballcrew.common.dto.YellowCardDto;
//import org.example.footballcrew.domain.footballer.Footballer;
//import org.example.footballcrew.domain.footballer.FootballerDatabase;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping
//public class Controller {
//
//  private final FootballerDatabase footballerDatabase;
//
//  public Controller(FootballerDatabase footballerDatabase) {
//    this.footballerDatabase = footballerDatabase;
//  }
//
//  @PostMapping
//  public ResponseEntity<?> confirm(@RequestBody SelectedTeamRequest request) {
//
//    return ResponseEntity.status(201).build();
//  }
//
//  @GetMapping("/team")
//  public ResponseEntity<TeamDto> getReportBeforeMatch() {
//    return ResponseEntity.ok(new TeamDto());
//  }
//
//  @GetMapping("/footballers/{id}")
//  public ResponseEntity<FootballerDto> getFootballer(@PathVariable String id) {
//    var footballer = footballerDatabase.findFootballerById(UUID.fromString(id));
//    return ResponseEntity.ok(from(footballer));
//  }
//
//  @GetMapping("/footballers")
//  public ResponseEntity<List<FootballerDto>> getFootballer() {
//    return ResponseEntity.ok(footballerDatabase.findFootballers().stream()
//            .map(this::from)
//            .toList());
//  }
//
//  @PostMapping("/footballers")
//  public ResponseEntity<?> saveFootballer(@RequestBody CreateFootballerDto dto) {
//    footballerDatabase.save(new Footballer(UUID.randomUUID(), LocalDateTime.of(2000, 5, 25, 12, 0)));
//    return ResponseEntity.status(201).build();
//  }
//
//  @PutMapping("/yellow-cards")
//  public ResponseEntity<?> yellowCards(@RequestBody YellowCardDto dto) {
//    var footballer = footballerDatabase.findFootballerById(UUID.fromString(dto.id()));
//    footballer.handleYellowCard();
//    footballerDatabase.update(footballer);
//    return ResponseEntity.status(204).build();
//  }
//
//  @PutMapping("/match")
//  public ResponseEntity<?> matchPlayed() {
//    var footballers = footballerDatabase.findFootballers();
//    footballers.forEach(Footballer::handleMatch);
//    footballers.forEach(footballerDatabase::update);
//    return ResponseEntity.status(204).build();
//  }
//
//  private FootballerDto from(Footballer f) {
//    return new FootballerDto(
//            f.getId(), f.getBornDate(), f.getYellowCardsQuantity(), f.getRedCardsQuantity(), f.isYouth(new YouthFootballerSpecification(22)), f.isExcluded(),
//            dtoFromExclusion(f.getExclusions(), f.getId())
//    );
//  }
//
//  private List<ExclusionDto> dtoFromExclusion(List<Exclusion> exclusions, UUID footballerId) {
//    return exclusions.stream().map(
//            ex -> switch (ex) {
//              case QuantitativeExclusion exclusion -> new ExclusionDto(footballerId, null, null, exclusion.getQuantity(), ExclusionType.QUANTITATIVE.name);
//              case TimeExclusion exclusion -> new ExclusionDto(footballerId, exclusion.getTill(), null, null, ExclusionType.TIME.name);
//              case StateExclusion exclusion -> new ExclusionDto(footballerId, null, exclusion.active(), null, ExclusionType.STATE.name);
//              default -> throw new RuntimeException("TODO");
//            }
//    ).toList();
//  }
//}
