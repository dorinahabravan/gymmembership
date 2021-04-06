package com.habravanEnterprise.fitnessForLife.services;

import com.habravanEnterprise.fitnessForLife.models.RegisterMesage;
import java.io.IOException;

public interface FileInputOutputMsgIntf {

    //Exportul datelor.(unde,ce).
    void save(String fileName, RegisterMesage msg);

    //Importarea datelor(ce,de unde)
    RegisterMesage readFile(String fileName) throws IOException;

}
