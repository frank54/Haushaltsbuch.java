package de.bagehorn.Haushaltsbuch.services;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import de.bagehorn.Haushaltsbuch.entities.Buchung;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BuchungSerializer extends StdSerializer<Buchung> {

    private static final DateFormat df = new SimpleDateFormat("dd.MM.yyyy");

    public BuchungSerializer() {
        super(Buchung.class);
    }

    @Override
    public void serialize(Buchung buchung, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("beschreibung", buchung.getBeschreibung());
        jsonGenerator.writeStringField("betrag", buchung.getBetrag() + " SFr");
        jsonGenerator.writeStringField("datum", df.format(buchung.getDatum()));
        jsonGenerator.writeStringField("kategorie", buchung.getKategorie().getName());
        jsonGenerator.writeStringField("typ", buchung.getKategorie().getTyp());
        jsonGenerator.writeEndObject();
    }
}
