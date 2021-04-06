package com.habravanEnterprise.fitnessForLife.services;

import com.habravanEnterprise.fitnessForLife.models.Client;
import java.util.List;

public interface FileInputOutputIntf {

    //Exportul datelor.(unde,ce).
    void save(String fileName, List<Client> listaClienti);

    //Importarea datelor(ce,de unde)
    List<Client> readFile(String fileName);

}
