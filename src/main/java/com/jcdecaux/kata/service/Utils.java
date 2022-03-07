package com.jcdecaux.kata.service;

import com.jcdecaux.kata.model.Poi;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.ApplicationArguments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

public class Utils {

    public static List<Poi> readFile() {

        Reader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ApplicationArguments.class.getResourceAsStream("/" + "data.csv"))));

        CsvToBean<Poi> csvToBean = new CsvToBeanBuilder(reader)
                .withType(Poi.class).withIgnoreLeadingWhiteSpace(true)
                .build();

        return csvToBean.parse();
    }
}
