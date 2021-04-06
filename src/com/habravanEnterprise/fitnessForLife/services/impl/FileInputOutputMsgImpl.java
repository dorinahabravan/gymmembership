/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.habravanEnterprise.fitnessForLife.services.impl;

import com.habravanEnterprise.fitnessForLife.models.RegisterMesage;
import com.habravanEnterprise.fitnessForLife.services.FileInputOutputMsgIntf;
import com.habravanEnterprise.fitnessForLife.util.CsvUtil;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

/**
 *
 * @author Dorina
 */
public class FileInputOutputMsgImpl implements FileInputOutputMsgIntf {

    private static final Logger LOG = Logger.getLogger(FileInputOutputMsgImpl.class.getName());

    @Override
    public void save(String fileName, RegisterMesage msg) {

        try (FileWriter filewriter = new FileWriter(fileName)) {
            filewriter.write(msg.toString());
        } catch (Exception e) {

            LOG.severe(e.toString());

        }

    }

    @Override
    public RegisterMesage readFile(String fileName) {
        RegisterMesage msg = new RegisterMesage();

        try (
                FileReader filereader = new FileReader(fileName);
                BufferedReader bufferedreader = new BufferedReader(filereader);) {
            String line = null;

            while ((line = bufferedreader.readLine()) != null) {
                msg = CsvUtil.lineToMesage(line);

            }

        } catch (IOException e) {
            LOG.severe(e.toString());

        }

        return msg;

    }

}
