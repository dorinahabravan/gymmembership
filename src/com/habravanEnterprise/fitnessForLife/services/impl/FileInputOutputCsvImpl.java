package com.habravanEnterprise.fitnessForLife.services.impl;

import com.habravanEnterprise.fitnessForLife.models.Client;
import com.habravanEnterprise.fitnessForLife.util.CsvUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.habravanEnterprise.fitnessForLife.services.FileInputOutputIntf;

public class FileInputOutputCsvImpl implements FileInputOutputIntf {

    @Override
    public void save(String fileName, List<Client> listaClienti) {
        try (FileWriter filewriter = new FileWriter(fileName)) {
            for (Client client : listaClienti) {
                String line = CsvUtil.clientToLine(client);
                filewriter.write(line);
            }
        } catch (Exception e) {
            System.err.println(e);

        }

    }

    @Override
    public List<Client> readFile(String fileName) {

        List<Client> lista = new ArrayList<>();
        try (
                FileReader filereader = new FileReader(fileName);
                BufferedReader bufferedreader = new BufferedReader(filereader);) {
            String line = null;

            while ((line = bufferedreader.readLine()) != null) {
                Client st = CsvUtil.lineToClient(line);
                lista.add(st);

            }

        } catch (IOException e) {
            System.err.println(e);

        }

        return lista;
    }

}
