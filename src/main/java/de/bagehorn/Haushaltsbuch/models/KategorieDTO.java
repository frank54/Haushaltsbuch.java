package de.bagehorn.Haushaltsbuch.models;

import lombok.*;

import java.time.LocalDateTime;

@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(value = AccessLevel.PACKAGE)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class KategorieDTO {

    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private int position;
    private String typ;
    private String beschreibung;

    private Integer version;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

}
