package nl.ktmc.netfluxexample.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Movie {

    String id;

    @NonNull
    String title;
}
